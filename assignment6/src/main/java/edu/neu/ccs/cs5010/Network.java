package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Network implements INetwork {

  private final Map<Integer, Set<Integer>> connectionsMap;
  private final Map<Integer, IUser> usersMap;
  private final int influencerBound;

  public Network(int influencerBound) {
    connectionsMap = new HashMap<>();
    usersMap = new HashMap<>();
    this.influencerBound = influencerBound;
  }

  @Override
  public void addUser(IUser user) {
    int userId = user.getUserId();
    usersMap.put(userId, user);
    connectionsMap.put(userId, new HashSet<>());
  }

  @Override
  public void addEdge(IEdge edge) {
    int fromId = edge.getFromId();
    int toId = edge.getToId();
    if (usersMap.containsKey(fromId) && usersMap.containsKey(toId)
        && !connectionsMap.get(fromId).contains(toId)) {
      IUser destination = usersMap.get(toId);
      destination.addOneFollower();
      connectionsMap.get(fromId).add(toId);
    }
  }

  @Override
  public Set<Integer> getFriendsOfUser(int userId) {
    if (!connectionsMap.containsKey(userId)) {
      throw new InvalidUserException("No such user in the connection");
    }
    return connectionsMap.get(userId);
  }

  @Override
  public List<Integer> getInfluencers() {
    List<Integer> influencers = new ArrayList<>();
    for (IUser user: usersMap.values()) {
      if (user.getNumFollowers() > influencerBound) {
        influencers.add(user.getUserId());
      }
    }
    return influencers;
  }

  @Override
  public Collection<IUser> getAllUsers() {
    return usersMap.values();
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    Network network = (Network) other;

    if (influencerBound != network.influencerBound) {
      return false;
    }
    if (!usersMap.equals(network.usersMap)) {
      return false;
    }
    return connectionsMap.equals(network.connectionsMap);
  }

  @Override
  public int hashCode() {
    int result = connectionsMap.hashCode();
    result = 31 * result + influencerBound;
    result = 31 * result + usersMap.hashCode();
    return result;
  }
}
