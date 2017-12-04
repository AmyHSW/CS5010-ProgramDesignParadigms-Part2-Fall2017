package edu.neu.ccs.cs5010.assignment9.messages.client;

public class ClientMessage implements IMessage {

  private static final String MSG_SPLIT_REGEX = ":";
  private String message;

  public ClientMessage(String frame, String payload) {
    createMsg(frame, payload);
  }

  private void createMsg(String frame, String payload) {
    if (frame.equals("")) {
      message = "";
    } else {
      message = frame + MSG_SPLIT_REGEX + payload;
    }
  }

  public String toString() {
    return message;
  }
}
