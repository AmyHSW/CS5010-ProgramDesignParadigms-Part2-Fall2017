package edu.neu.ccs.cs5010;

import java.util.List;
import java.util.Set;

public interface INetwork {

  Void addUser(IUser user);

  Void addEdge(IEdge edge);

  List<IUser> getFriendList(IUser user);

  List<IUser> getInfluencers();

  IUser getRandomUser();

  Set<Integer> getUserIdSet();

}
