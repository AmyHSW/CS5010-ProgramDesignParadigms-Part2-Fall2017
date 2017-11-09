package edu.neu.ccs.cs5010;

import org.omg.CORBA.DynAnyPackage.Invalid;

import java.util.NoSuchElementException;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Random;

public class Network implements INetwork {

  private static final int INFLUENCER_BOUND = 25;

  private final Map<IUser, Set<IUser>> connectionsMap;
  private final Map<Integer, IUser> usersMap;
  private final List<IUser> influencers;

  public Network() {
    connectionsMap = new HashMap<>();
    usersMap = new HashMap<>();
    influencers = new ArrayList<>();
  }

  @Override
  public void addUser(IUser user) {
    usersMap.put(user.getUserId(), user);
  }

  @Override
  public void addEdge(IEdge edge) {
    int fromId = edge.getFromId();
    int toId = edge.getToId();
    if (usersMap.containsKey(fromId) && usersMap.containsKey(toId)) {
      IUser source = usersMap.get(fromId);
      IUser destination = usersMap.get(toId);
      source.addOneFollowee();
      destination.addOneFollower();
      connectionsMap.put(source, connectionsMap.getOrDefault(source, new HashSet<>()))
          .add(destination);
    }
  }

  @Override
  public Set<IUser> getFriendList(IUser user) {
    if (!connectionsMap.containsKey(user)) {
      throw new InvalidUserException("No such user in the connection");
    }
    return connectionsMap.get(user);
  }

  @Override
  public List<IUser> getInfluencers() {
    for (IUser user: usersMap.values()) {
      if (user.getNumFollowers() > INFLUENCER_BOUND) {
        influencers.add(user);
      }
    }
    return influencers;
  }

  @Override
  public IUser getRandomUser() {
    Random rand = new Random();
    int randomId = rand.nextInt(usersMap.size());
    return usersMap.get(randomId);
  }

  @Override
  public Set<Integer> getUserIdSet() {
    return usersMap.keySet();
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

    if (connectionsMap != null ? !connectionsMap.equals(network.connectionsMap)
            : network.connectionsMap != null)
      return false;
    if (usersMap != null ? !usersMap.equals(network.usersMap) : network.usersMap != null) {
      return false;
    }
    return influencers != null ? influencers.equals(network.influencers)
            : network.influencers == null;
  }

  @Override
  public int hashCode() {
    int result = connectionsMap != null ? connectionsMap.hashCode() : 0;
    result = 31 * result + (usersMap != null ? usersMap.hashCode() : 0);
    result = 31 * result + (influencers != null ? influencers.hashCode() : 0);
    return result;
  }
}
