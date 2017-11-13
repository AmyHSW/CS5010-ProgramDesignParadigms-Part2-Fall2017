package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The RecommendationSystemBonus represents a recommendation system.
 * @author Shuwan Huang, Jingyu Shen
 */
public class RecommendationSystemBonus extends RecommendationSystem{
  private static final int AGE_RANGE = 3;

  /**
   * Constructs a new RecommendationSystemBonus with a CmdHandler.
   * @param cmdHandler a CmdHandler
   */
  public RecommendationSystemBonus(ICmdHandler cmdHandler) {
    super(cmdHandler);
  }

  /**
   * Starts recommending friends for the selected users.
   */
  @Override
  public void startRecommendation() {
    for (IUser user : userRecomMap.keySet()) {
      recommendSameCitySimilarAgeFriends(user);
      recommendOldUsers(user);
    }
  }

  private void recommendSameCitySimilarAgeFriends(IUser user) {
    List<IUser> allUsers = new ArrayList<>(network.getAllUsers());
    Set<Integer> candidates = new HashSet<>();
    String city = user.getCity();
    int age = user.getAge();
    for (IUser u: allUsers) {
      if (u.getCity().equals(city) && Math.abs(u.getAge() - age) <= AGE_RANGE) {
        candidates.add(u.getUserId());
      }
    }
    addRecommendations(user, new ArrayList<>(candidates));
  }

  private void recommendOldUsers(IUser user) {
    if (userRecomMap.get(user).size() == numRecommendations) {
      return;
    }
    List<IUser> allUsers = new ArrayList<>(network.getAllUsers());
    allUsers.sort(Comparator.comparing(IUser::getCreatedDate));
    Set<Integer> recommendations = userRecomMap.get(user);
    Set<Integer> friendsOfUser = network.getFriendsOfUser(user.getUserId());
    for (IUser u: allUsers) {
      if (friendsOfUser.contains(u.getUserId()) || u.getUserId() == user.getUserId()) {
        continue;
      }
      recommendations.add(u.getUserId());
      recomFrequencyMap.put(u.getUserId(), recomFrequencyMap.getOrDefault(u.getUserId(), 0) + 1);
      if (recommendations.size() == numRecommendations) {
        break;
      }
    }
  }

  /**
   * Accepts command-line arguments from user. Constructs a new CmdHandler with the arguments.
   * If the arguments are invalid, throws an exception; otherwise, constructs a new
   * RecommendationSystemBonus with the CmdHandler.
   * @param args command-line arguments
   * @throws InvalidInputException if the arguments are invalid
   */
  public static void main(String[] args) {
    ICmdHandler cmdHandler = new CmdHandler(args);
    if (!cmdHandler.isValid()) {
      throw new InvalidInputException(cmdHandler.getErrorMessage());
    }
    IRecommendationSystem recommendationSystem = new RecommendationSystemBonus(cmdHandler);
    recommendationSystem.startRecommendation();
    recommendationSystem.outputResults();
    recommendationSystem.printTopTen();
  }
}
