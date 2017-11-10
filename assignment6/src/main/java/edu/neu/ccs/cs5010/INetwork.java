package edu.neu.ccs.cs5010;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface INetwork {

  void addUser(IUser user);

  void addEdge(IEdge edge);

  Set<Integer> getFriendsOfUser(int userId);

  List<Integer> getInfluencers();

  Collection<IUser> getAllUsers();

}
