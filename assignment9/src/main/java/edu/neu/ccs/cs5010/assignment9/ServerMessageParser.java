package edu.neu.ccs.cs5010.assignment9;

public class ServerMessageParser {

  private static final String MSG_SPLIT_REGEX = ":";
  private String frame;
  private String payload;

  public ServerMessageParser(String message) {
    parse(message);
  }

  private void parse(String message) {
    String[] strings = message.split(MSG_SPLIT_REGEX);
    frame = strings[0];
    payload = strings[1];
  }

  public String getFrame() {
    return frame;
  }

  public String getPayload() {
    return payload.trim();
  }
}
