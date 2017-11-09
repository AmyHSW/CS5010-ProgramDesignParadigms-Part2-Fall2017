package edu.neu.ccs.cs5010;

import java.util.Iterator;

public interface IUserGenerator {

  boolean hasNextUser();
  IUser getNextUser();

}
