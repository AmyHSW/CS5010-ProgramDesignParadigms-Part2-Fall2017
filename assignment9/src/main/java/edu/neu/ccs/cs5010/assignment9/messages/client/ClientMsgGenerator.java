package edu.neu.ccs.cs5010.assignment9.messages.client;

import edu.neu.ccs.cs5010.assignment9.frames.ClientFrame;
import edu.neu.ccs.cs5010.assignment9.frames.ServerFrame;
import edu.neu.ccs.cs5010.assignment9.messages.server.IMessageParser;
import edu.neu.ccs.cs5010.assignment9.messages.server.ServerMessageParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ClientMsgGenerator implements IClientMsgGenerator {

  private static final String EMPTY_MESSAGE = "";
  private static final String MSG_SPLIT_REGEX = ":";
  private String frame = "";
  private String payload = "";

  public ClientMsgGenerator(String fromServer) {
    generateClientMsg(fromServer);
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
        break;
    }
  }

  private void chooseDice(String serverPayload) {
    boolean valid = false;
    try {
      while (!valid) {
        System.out.println(
                "Please give 5 integers separated by space, 1 for keep and 0 for not:");
        BufferedReader stdin = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8));
        String fromUser = stdin.readLine();
        assert (fromUser != null);
        fromUser = fromUser.trim();
        if (fromUser.contains(" ")) {
          String[] output = fromUser.split("\\s");
          if (output.length != 5) {
            continue;
          }
          int index = 0;
          while (index < 5 && (output[index].equals("0") || output[index].equals("1"))) {
            index++;
          }
          if (index == 5) {
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
        BufferedReader stdin = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8));
        String fromUser = stdin.readLine();
        for (int i = 5; i < categories.length; i++) {
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

  @Override
  public String getClientMsg() {
    if (frame.equals("")) {
      return EMPTY_MESSAGE;
    }
    return frame + MSG_SPLIT_REGEX + payload;
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

    return frame.equals(that.frame) && payload.equals(that.payload);
  }

  @Override
  public int hashCode() {
    return 31 * frame.hashCode() + payload.hashCode();
  }
}
