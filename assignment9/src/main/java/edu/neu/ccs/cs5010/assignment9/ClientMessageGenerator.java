package edu.neu.ccs.cs5010.assignment9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientMessageGenerator {

  private static final String MSG_SPLIT_REGEX = ":";
  private String frame;
  private String payload;

  public ClientMessageGenerator(ServerMessageParser parser) {
    String serverFrame = parser.getFrame();
    String serverPayload = parser.getPayload();
    switch (serverFrame) {
      case "CHOOSE_DICE" :
        chooseDice(serverPayload);
        break;
      case "CHOOSE_SCORE" :
        chooseScore(serverPayload);
        break;
      default:
        break;
    }
  }

  private void chooseDice(String serverPayload) {
    System.out.println(serverPayload + " are the current dice rolls, which ones would you like to keep?");
    boolean valid = false;
    try {
      while (!valid) {
        System.out.println("Please give 5 integers separated by space, 1 for keep and 0 for not");
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String fromUser = stdin.readLine();
        if (fromUser.contains(" ")) {
          String[] output = fromUser.split("\\s");
          if (output.length != 5) {
            continue;
          }
          int i = 0;
          while (i < 5 && (output[i].equals("0") || output[i].equals("1"))) {
            i++;
          }
          if (i == 5) {
            valid = true;
            frame = "KEEP_DICE";
            payload = serverPayload + " " + fromUser;
          }
        }
      }
    } catch (IOException e) {
      System.err.println("Couldn't get I/O for the connection to the host");
    }

  }

  private void chooseScore(String serverPayload) {
    System.out.println(serverPayload
            + " are the current dice rolls and score slots that you can choose from");
    boolean valid = false;
    String[] serverInfo = serverPayload.split("\\s");
    try {
      while (!valid) {
        System.out.println("Please give the name of one unused score in the list above");
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String fromUser = stdin.readLine();
        for (int i = 5; i < serverInfo.length; i++) {
          if (fromUser.equals(serverInfo[i])) {
            valid = true;
            break;
          }
        }
      }
    } catch (IOException e) {
      System.err.println("Couldn't get I/O for the connection to the host");
    }
  }

}
