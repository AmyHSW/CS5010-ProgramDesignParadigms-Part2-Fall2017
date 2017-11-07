package edu.neu.ccs.cs5010;

import edu.neu.ccs.cs5010.INodesCsvParser;
import edu.neu.ccs.cs5010.IUser;

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
