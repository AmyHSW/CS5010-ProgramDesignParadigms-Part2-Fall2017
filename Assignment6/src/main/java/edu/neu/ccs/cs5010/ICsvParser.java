package edu.neu.ccs.cs5010;

import java.util.List;

public interface ICsvParser {
  boolean hasNextLine();
  List<String> getNextLine();
}