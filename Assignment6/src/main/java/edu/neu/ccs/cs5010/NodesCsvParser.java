package edu.neu.ccs.cs5010;

import java.util.List;

public class NodesCsvParser implements INodesCsvParser {

  public NodesCsvParser(List<String> strings) {

  }

  @Override
  public IUser getNextUser() {
    return null;
  }

  @Override
  public boolean hasNextUser() {
    return false;
  }
}
