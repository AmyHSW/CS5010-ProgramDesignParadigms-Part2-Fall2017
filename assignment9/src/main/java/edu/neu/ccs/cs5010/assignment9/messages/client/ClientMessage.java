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

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    ClientMessage that = (ClientMessage) other;

    return message.equals(that.message);
  }

  @Override
  public int hashCode() {
    return message.hashCode();
  }
}
