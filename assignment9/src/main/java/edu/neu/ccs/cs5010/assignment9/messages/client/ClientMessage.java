package edu.neu.ccs.cs5010.assignment9.messages.client;

/**
 * The ClientMessage class represents the client's massage, composed with frame and payload.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class ClientMessage implements IMessage {

  private static final String MSG_SPLIT_REGEX = ":";
  private String message;

  /**
   * The constructor of ClientMessage.
   * @param frame the client's frame.
   * @param payload the client's payload.
   */
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

  @Override
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
