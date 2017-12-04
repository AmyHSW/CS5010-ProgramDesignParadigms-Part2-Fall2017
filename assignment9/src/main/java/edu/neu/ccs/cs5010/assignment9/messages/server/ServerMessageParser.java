package edu.neu.ccs.cs5010.assignment9.messages.server;

/**
 * The ServerMessageParser is a class to parse the server's message.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class ServerMessageParser implements IMessageParser {

  private static final String MSG_SPLIT_REGEX = ":";
  private String frame;
  private String payload;

  /**
   * The constructor of ServerMessageParser.
   * @param message the server's message.
   */
  public ServerMessageParser(String message) {
    parse(message);
  }

  private void parse(String message) {
    int splitIndex = message.indexOf(MSG_SPLIT_REGEX);
    frame = message.substring(0, splitIndex);
    payload = message.substring(splitIndex + 1).trim();
  }

  @Override
  public String getFrame() {
    return frame;
  }

  @Override
  public String getPayload() {
    return payload;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    ServerMessageParser that = (ServerMessageParser) other;

    return frame.equals(that.frame) && payload.equals(that.payload);
  }

  @Override
  public int hashCode() {
    return 31 * frame.hashCode() + payload.hashCode();
  }
}
