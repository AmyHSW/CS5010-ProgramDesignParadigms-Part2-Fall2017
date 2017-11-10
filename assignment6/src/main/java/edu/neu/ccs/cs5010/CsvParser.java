package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CsvParser implements ICsvParser {

  private Iterator<List<String>> iterator;

  public CsvParser(List<String> stringLines) {
    List<List<String>> infoLines = new ArrayList<>();
    for (int i = 1; i < stringLines.size(); i++) {
      String currentEdgeLine = stringLines.get(i);
      String[] elements = currentEdgeLine.split(",");
      infoLines.add(Arrays.asList(elements));
    }
    iterator = infoLines.iterator();
  }

  @Override
  public boolean hasNextLine() {
    return iterator.hasNext();
  }

  @Override
  public List<String> getNextLine() {
    return iterator.next();
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    CsvParser csvParser = (CsvParser) other;

    return iterator != null ? iterator.equals(csvParser.iterator) : csvParser.iterator == null;
  }

  @Override
  public int hashCode() {
    return iterator != null ? iterator.hashCode() : 0;
  }
}