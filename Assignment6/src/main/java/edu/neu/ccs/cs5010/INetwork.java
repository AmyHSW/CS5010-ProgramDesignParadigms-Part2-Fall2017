package edu.neu.ccs.cs5010;

import java.util.List;
import java.util.Set;

public interface INetwork {

  void addUser(IUser user);

  void addEdge(IEdge edge);

  List<IUser> getFriendList(IUser user);

  List<IUser> getInfluencers();

  IUser getRandomUser();

  Set<Integer> getUserIdSet();

}
