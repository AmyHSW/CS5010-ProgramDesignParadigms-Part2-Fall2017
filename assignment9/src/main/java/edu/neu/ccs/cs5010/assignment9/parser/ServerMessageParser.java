package edu.neu.ccs.cs5010.assignment9.parser;

public class ServerMessageParser {

  private static final String MSG_SPLIT_REGEX = ":";
  private String frame;
  private String payload;

  public ServerMessageParser(String message) {
    parse(message);
  }

  private void parse(String message) {
    int splitIndex = message.indexOf(MSG_SPLIT_REGEX);
    frame = message.substring(0, splitIndex);
    payload = message.substring(splitIndex + 1).trim();
  }

  public String getFrame() {
    return frame;
  }

  public String getPayload() {
    return payload;
  }
}
