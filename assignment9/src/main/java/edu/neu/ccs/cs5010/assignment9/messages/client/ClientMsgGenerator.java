package edu.neu.ccs.cs5010.assignment9.messages.client;

import edu.neu.ccs.cs5010.assignment9.frames.ClientFrame;
import edu.neu.ccs.cs5010.assignment9.frames.ServerFrame;
import edu.neu.ccs.cs5010.assignment9.messages.server.IMessageParser;
import edu.neu.ccs.cs5010.assignment9.messages.server.ServerMessageParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * The ClientMsgGenerator is a class to generate client's message with the server's message.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class ClientMsgGenerator implements IClientMsgGenerator {

  private static final int CATEGORIES_START_INDEX = 5;
  private static final int NUM_DICE = 5;
  private static final String KEEP = "1";
  private static final String NOT_KEEP = "0";
  private final BufferedReader stdin;
  private String frame = "";
  private String payload = "";

  /**
   * The constructor of ClientMsgGenerator.
   */
  public ClientMsgGenerator() {
    stdin = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
  }

  @Override
  public IMessage getClientMsg(String fromServer) {
    generateClientMsg(fromServer);
    return new ClientMessage(frame, payload);
  }

  private void generateClientMsg(String fromServer) {
    IMessageParser parser = new ServerMessageParser(fromServer);
    String serverFrame = parser.getFrame();
    String serverPayload = parser.getPayload();
    switch (serverFrame) {
      case ServerFrame.CHOOSE_DICE:
        chooseDice(serverPayload);
        break;
      case ServerFrame.CHOOSE_SCORE:
        chooseScore(serverPayload);
        break;
      default:
        resetClientFrame();
        break;
    }
  }

  private void chooseDice(String serverPayload) {
    boolean valid = false;
    try {
      while (!valid) {
        System.out.println(
                "Please give 5 integers separated by space, 1 for keep and 0 for not:");
        String fromUser = stdin.readLine();
        assert (fromUser != null);
        fromUser = fromUser.trim();
        if (fromUser.contains(" ")) {
          String[] output = fromUser.split("\\s");
          if (output.length != NUM_DICE) {
            continue;
          }
          int index = 0;
          while (index < NUM_DICE && (output[index].equals(NOT_KEEP)
                  || output[index].equals(KEEP))) {
            index++;
          }
          if (index == NUM_DICE) {
            valid = true;
            frame = ClientFrame.KEEP_DICE;
            payload = serverPayload + " " + fromUser;
          }
        }
      }
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
    }

  }

  private void chooseScore(String serverPayload) {
    boolean valid = false;
    String[] categories = serverPayload.split("\\s");
    try {
      while (!valid) {
        System.out.println(
                "Please give the name of one unused score slot in the list above:");
        String fromUser = stdin.readLine();
        for (int i = CATEGORIES_START_INDEX; i < categories.length; i++) {
          if (fromUser.equals(categories[i])) {
            valid = true;
            frame = ClientFrame.SCORE_CHOICE;
            payload = fromUser;
            break;
          }
        }
      }
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
    }
  }

  private void resetClientFrame() {
    frame = "";
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    ClientMsgGenerator that = (ClientMsgGenerator) other;

    return stdin.equals(that.stdin);
  }

  @Override
  public int hashCode() {
    return stdin.hashCode();
  }
}
