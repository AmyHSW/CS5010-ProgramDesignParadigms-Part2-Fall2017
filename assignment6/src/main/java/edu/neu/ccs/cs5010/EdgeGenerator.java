package edu.neu.ccs.cs5010;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Represent a concrete edge generator, generating edge one by one.
 */
public class EdgeGenerator implements IEdgeGenerator {

  private ICsvParser csvParser;
  private static final int SOURCE_INDEX = 0;
  private static final int DESTINATION_INDEX = 1;

  /**
   * Constructor of EdgeGenerator object.
   *
   * @param csvParser that provides edge information
   */
  public EdgeGenerator(ICsvParser csvParser) {
    this.csvParser = csvParser;
  }

  @Override
  public boolean hasNextEdge() {
    return csvParser.hasNextLine();
  }

  @Override
  public IEdge getNextEdge() {
    if (!hasNextEdge()) {
      throw new NoSuchElementException("No more edges can be generated");
    }
    List<String> elements = csvParser.getNextLine();
    int fromId = Integer.parseInt(elements.get(SOURCE_INDEX));
    int toId = Integer.parseInt(elements.get(DESTINATION_INDEX));
    return IEdge.createEdge(fromId, toId);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    EdgeGenerator that = (EdgeGenerator) other;

    return csvParser != null ? csvParser.equals(that.csvParser) : that.csvParser == null;
  }

  @Override
  public int hashCode() {
    return csvParser != null ? csvParser.hashCode() : 0;
  }
}
