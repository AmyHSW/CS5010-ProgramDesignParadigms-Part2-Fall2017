package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class EdgesCsvParser implements IEdgesCsvParser {
  private List<List<String>> edgeInfoLines;
  private static final int SOURCE_INDEX = 0;
  private static final int DESTINATION_INDEX = 1;

  public EdgesCsvParser(List<String> stringLines) {
    edgeInfoLines = new ArrayList<>();
    for (int i = 1; i < stringLines.size(); i++) {
      String currentEdgeLine = stringLines.get(i);
      String[] elements = currentEdgeLine.split(",");
      edgeInfoLines.add(Arrays.asList(elements));
    }
  }

  @Override
  public Iterator<IEdge> iterator() {
    return new EdgeIterator();
  }

  private class EdgeIterator implements Iterator<IEdge> {
    int index = 0;

    @Override
    public boolean hasNext() {
      return index < edgeInfoLines.size();
    }

    @Override
    public IEdge next() {
      List<String> elements = edgeInfoLines.get(index);
      int fromId = Integer.parseInt(elements.get(SOURCE_INDEX));
      int toID = Integer.parseInt(elements.get(DESTINATION_INDEX));
      index++;
      return new Edge(fromId, toID);
    }
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    EdgesCsvParser that = (EdgesCsvParser) other;

    return edgeInfoLines != null ? edgeInfoLines.equals(that.edgeInfoLines) : that.edgeInfoLines == null;
  }

  @Override
  public int hashCode() {
    return edgeInfoLines != null ? edgeInfoLines.hashCode() : 0;
  }
}
