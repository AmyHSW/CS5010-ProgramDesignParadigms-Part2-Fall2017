package edu.neu.ccs.cs5010;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * The RecommendationSystem represents a recommendation system.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class RecommendationSystem implements IRecommendationSystem {

  private static final char RANDOM_PROCESS = 'r';
  private static final char PROCESS_FROM_BEGINNING = 's';
  private static final char PROCESS_FROM_END = 'e';
  private static final int TOP_TEN = 10;
  protected final INetwork network;
  protected final int numRecommendations;
  protected final Map<IUser, Set<Integer>> userRecomMap;
  protected final Map<Integer, Integer> recomFrequencyMap;
  private final String outputDir;

  /**
   * Constructs a new RecommendationSystem with a CmdHandler.
   * @param cmdHandler a CmdHandler
   */
  public RecommendationSystem(ICmdHandler cmdHandler) {
    network = buildNetwork(cmdHandler.getNodeFile(),
        cmdHandler.getEdgeFile(),
        cmdHandler.getInfluencerBound());
    numRecommendations = cmdHandler.getNumRecommendations();
    outputDir = cmdHandler.getOutputFile();
    userRecomMap = new HashMap<>();
    recomFrequencyMap = new HashMap<>();
    pickUsersToProcess(cmdHandler.getNumUsersToProcess(), cmdHandler.getProcessingFlag());
  }

  private INetwork buildNetwork(String nodeFile, String edgeFile, int influencerBound) {
    INetwork network = new Network(influencerBound);
    ICsvParser nodesCsvParser = new CsvParser(IoLibrary.parseToLines(nodeFile));
    IUserGenerator userGenerator = new UserGenerator(nodesCsvParser);
    while (userGenerator.hasNextUser()) {
      IUser user = userGenerator.getNextUser();
      network.addUser(user);
    }
    ICsvParser edgesCsvParser = new CsvParser(IoLibrary.parseToLines(edgeFile));
    IEdgeGenerator edgeGenerator = new EdgeGenerator(edgesCsvParser);
    while (edgeGenerator.hasNextEdge()) {
      IEdge edge = edgeGenerator.getNextEdge();
      network.addEdge(edge);
    }
    return network;
  }

  private void pickUsersToProcess(int numUsersToProcess, char processingFlag) {
    List<IUser> allUsers = new ArrayList<>(network.getAllUsers());
    if (processingFlag == RANDOM_PROCESS) {
      pickRandomUsers(allUsers, numUsersToProcess);
    } else if (processingFlag == PROCESS_FROM_BEGINNING) {
      pickUsersInOrder(allUsers, 0, numUsersToProcess);
    } else if (processingFlag == PROCESS_FROM_END) {
      pickUsersInOrder(allUsers, allUsers.size() - numUsersToProcess, numUsersToProcess);
    }
  }

  private void pickRandomUsers(List<IUser> allUsers, int numUsersToProcess) {
    Random random = new Random();
    for (int i = 0; i < numUsersToProcess; i++) {
      while (true) {
        IUser user = allUsers.get(random.nextInt(allUsers.size()));
        if (!userRecomMap.containsKey(user)) {
          userRecomMap.put(user, new HashSet<>());
          break;
        }
      }
    }
  }

  private void pickUsersInOrder(List<IUser> allUsers, int start, int numUsersToProcess) {
    Collections.sort(allUsers);
    for (int i = start; i < start + numUsersToProcess; i++) {
      userRecomMap.put(allUsers.get(i), new HashSet<>());
    }
  }

  /**
   * Starts recommending friends for the selected users.
   */
  @Override
  public void startRecommendation() {
    for (IUser user : userRecomMap.keySet()) {
      recommendFriendsForUser(user);
    }
  }

  private void recommendFriendsForUser(IUser user) {
    recommendFriendsForNewbie(user);
    recommendFriendsOfFriends(user);
    followInfluencers(user);
    followRandomUsers(user);
  }

  private boolean isNewbie(IUser user) {
    return user.getCreatedDate().isAfter(LocalDate.now().minusMonths(1));
  }

  private void recommendFriendsForNewbie(IUser user) {
    if (!isNewbie(user) || network.getFriendsOfUser(user.getUserId()).isEmpty()) {
      return;
    }
    int idWithMaxFriends = -1;
    int maxFriends = -1;
    Set<Integer> friendsAlreadyExisted = network.getFriendsOfUser(user.getUserId());
    for (int userId : friendsAlreadyExisted) {
      int numFriends = network.getFriendsOfUser(userId).size();
      if (numFriends > maxFriends) {
        maxFriends = numFriends;
        idWithMaxFriends = userId;
      }
    }
    addRecommendations(user, new ArrayList<>(network.getFriendsOfUser(idWithMaxFriends)));
  }

  protected void addRecommendations(IUser user, List<Integer> candidates) {
    Collections.sort(candidates);
    Set<Integer> friendsOfUser = network.getFriendsOfUser(user.getUserId());
    for (int idFriend : candidates) {
      if (friendsOfUser.contains(idFriend) || idFriend == user.getUserId()) {
        continue;
      }
      userRecomMap.get(user).add(idFriend);
      recomFrequencyMap.put(idFriend, recomFrequencyMap.getOrDefault(idFriend, 0) + 1);
      if (userRecomMap.get(user).size() == numRecommendations) {
        return;
      }
    }
  }

  private void recommendFriendsOfFriends(IUser user) {
    if (userRecomMap.get(user).size() == numRecommendations
        || network.getFriendsOfUser(user.getUserId()).isEmpty()) {
      return;
    }
    Set<Integer> friendsOfFriends = new HashSet<>();
    Set<Integer> friendsOfUser = network.getFriendsOfUser(user.getUserId());
    for (int idFriend : friendsOfUser) {
      friendsOfFriends.addAll(network.getFriendsOfUser(idFriend));
    }
    addRecommendations(user, new ArrayList<>(friendsOfFriends));
  }

  private void followInfluencers(IUser user) {
    if (userRecomMap.get(user).size() == numRecommendations) {
      return;
    }
    List<Integer> influencers = network.getInfluencers();
    addRecommendations(user, influencers);
  }

  private void followRandomUsers(IUser user) {
    List<IUser> allUsers = new ArrayList<>(network.getAllUsers());
    Random random = new Random();
    Set<Integer> friendsOfUser = network.getFriendsOfUser(user.getUserId());
    Set<Integer> recommendations = userRecomMap.get(user);
    int sentinel = Math.min(numRecommendations,
        allUsers.size() - friendsOfUser.size() - 1);
    while (recommendations.size() < sentinel) {
      int idFriend = allUsers.get(random.nextInt(allUsers.size())).getUserId();
      if (!friendsOfUser.contains(idFriend) && !recommendations.contains(idFriend)
              && idFriend != user.getUserId()) {
        recommendations.add(idFriend);
        recomFrequencyMap.put(idFriend, recomFrequencyMap.getOrDefault(idFriend, 0) + 1);
      }
    }
  }

  /**
   * Saves the recommendations results to the output file.
   */
  @Override
  public void outputResults() {
    List<String> outputLines = new ArrayList<>();
    outputLines.add("Node ID,Recommended nodes");
    for (Map.Entry<IUser, Set<Integer>> entry: userRecomMap.entrySet()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(entry.getKey().getUserId()).append(",[");
      for (int idFriend: entry.getValue()) {
        stringBuilder.append(idFriend).append(" ");
      }
      stringBuilder.deleteCharAt(stringBuilder.length() - 1);
      stringBuilder.append("]");
      outputLines.add(stringBuilder.toString());
    }
    IoLibrary.generateOutput(outputDir, outputLines);
  }

  /**
   * Prints to console the top ten most frequently recommended node IDs.
   */
  @Override
  public void printTopTen() {
    List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(recomFrequencyMap.entrySet());
    entries.sort((freq1, freq2) -> freq2.getValue().compareTo(freq1.getValue()));
    System.out.println("Top ten most frequently recommended node IDs");
    for (int i = 0; i < TOP_TEN; i++) {
      Map.Entry<Integer, Integer> userFreq = entries.get(i);
      System.out.println("ID:" + userFreq.getKey() + " Freq:" + userFreq.getValue());
    }
  }

  /**
   * Accepts command-line arguments from user. Constructs a new CmdHandler with the arguments.
   * If the arguments are invalid, throws an exception; otherwise, constructs a new
   * RecommendationSystem with the CmdHandler.
   * @param args command-line arguments
   * @throws InvalidInputException if the arguments are invalid
   */
  public static void main(String[] args) {
    ICmdHandler cmdHandler = new CmdHandler(args);
    if (!cmdHandler.isValid()) {
      throw new InvalidInputException(cmdHandler.getErrorMessage());
    }
    IRecommendationSystem recommendationSystem = new RecommendationSystem(cmdHandler);
    recommendationSystem.startRecommendation();
    recommendationSystem.outputResults();
    recommendationSystem.printTopTen();
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    RecommendationSystem that = (RecommendationSystem) other;

    if (numRecommendations != that.numRecommendations) {
      return false;
    }
    if (!network.equals(that.network)) {
      return false;
    }
    if (!userRecomMap.equals(that.userRecomMap)) {
      return false;
    }
    return outputDir.equals(that.outputDir);
  }

  @Override
  public int hashCode() {
    int result = network.hashCode();
    result = 31 * result + numRecommendations;
    result = 31 * result + userRecomMap.hashCode();
    result = 31 * result + outputDir.hashCode();
    return result;
  }
}
