package edu.neu.ccs.cs5010;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Represent a network interface.
 */
public interface INetwork {
  /**
   * When one new user is generated, adds one user in the network.
   *
   * @param user the user to be added to the network
   */
  void addUser(IUser user);

  /**
   * When one new edge is generated, adds one edge in the network.
   *
   * @param edge the edge to be added to the network
   */
  void addEdge(IEdge edge);

  /**
   * Gets all friends/followings of one user.
   *
   * @param userId the user id to be analyze
   * @return set of the given user's all friends' ids
   */
  Set<Integer> getFriendsOfUser(int userId);

  /**
   * Gets all influencers in this network.
   *
   * @return list of all influencers' ids in this network
   */
  List<Integer> getInfluencers();

  /**
   * Gets all users in this network.
   *
   * @return collection of all users in this network
   */
  Collection<IUser> getAllUsers();

}
