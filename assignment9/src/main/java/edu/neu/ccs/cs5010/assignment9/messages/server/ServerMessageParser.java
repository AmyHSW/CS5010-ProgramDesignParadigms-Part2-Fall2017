package edu.neu.ccs.cs5010.assignment9.messages.server;

public class ServerMessageParser implements IMessageParser {

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

  @Override
  public String getFrame() {
    return frame;
  }

  @Override
  public String getPayload() {
    return payload;
  }
}
