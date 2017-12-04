package edu.neu.ccs.cs5010.assignment9.messages.server;

import edu.neu.ccs.cs5010.assignment9.exceptions.InvalidServerFrameException;
import edu.neu.ccs.cs5010.assignment9.frames.ServerFrame;

public class ServerMessageTranslator implements ITranslator {

  private static final String START_GAME_MSG = "Welcome to Yahtzee and have fun gaming!";
  private static final String START_TURN_MSG = "It's your turn now.";
  private static final String TURN_OVER_MSG = "Your turn is over.\n"
      + "Please wait for the other player or next round to start.";
  private static final String INVALID_DICE_MSG = "Your choice of dice is invalid!";

  @Override
  public String translate(String message) {
    ServerMessageParser parser = new ServerMessageParser(message);
    String frame = parser.getFrame();
    String payload = parser.getPayload();
    switch (frame) {
      case ServerFrame.INFO: return payload;
      case ServerFrame.ACK: return payload;
      case ServerFrame.START_GAME: return START_GAME_MSG;
      case ServerFrame.START_ROUND: return startRoundMsg(payload);
      case ServerFrame.START_TURN: return START_TURN_MSG;
      case ServerFrame.CHOOSE_DICE: return chooseDiceMsg(payload);
      case ServerFrame.INVALID_DICE_CHOICE: return INVALID_DICE_MSG;
      case ServerFrame.CHOOSE_SCORE: return chooseScoreMsg(payload);
      case ServerFrame.TURN_OVER: return TURN_OVER_MSG;
      case ServerFrame.ROUND_OVER: return roundOverMsg(payload);
      case ServerFrame.SCORE_CHOICE_INVALID: return chooseScoreMsg(payload);
      case ServerFrame.SCORE_CHOICE_VALID: return scorecardMsg(payload);
      case ServerFrame.GAME_OVER: return gameOverMsg(payload);
      default: throw new InvalidServerFrameException(frame + " is invalid server frame");
    }
  }

  private static String startRoundMsg(String round) {
    return "Now starts round " + round + ", please wait for your turn:";
  }

  private static String chooseDiceMsg(String dices) {
    return "Current dice rolls: [" + dices + "]";
  }

  private static String chooseScoreMsg(String payload) {
    String dices = payload.substring(0, 10).trim();
    String categories = payload.substring(10).trim();
    return chooseDiceMsg(dices)
        + "\nHere are the score slots that you can choose from:\n"
        + categories;
  }

  private static String scorecardMsg(String scores) {
    return "This is your current scores on the scorecard:\n"
        + "(score is -1 if the slot has not been used yet. Total is at the end.)\n"
        + scores;
  }

  private static String roundOverMsg(String round) {
    return "Round " + round + " is over.";
  }

  private static String gameOverMsg(String payload) {
    return "Game is over!\n"
        + "Here are the final scores:\n"
        + payload;
  }
}
