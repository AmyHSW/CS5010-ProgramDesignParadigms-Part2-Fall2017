package edu.neu.ccs.cs5010.assignment9.frames;

public interface ServerFrame {

  String START_GAME = "START_GAME";

  String START_ROUND = "START_ROUND";

  String START_TURN = "START_TURN";

  String CHOOSE_DICE = "CHOOSE_DICE";

  String INVALID_DICE_CHOICE = "INVALID_DICE_CHOICE";

  String CHOOSE_SCORE = "CHOOSE_SCORE";

  String SCORE_CHOICE_INVALID = "SCORE_CHOICE_INVALID";

  String SCORE_CHOICE_VALID = "SCORE_CHOICE_VALID";

  String TURN_OVER = "TURN_OVER";

  String ROUND_OVER = "ROUND_OVER";

  String GAME_OVER = "GAME_OVER";

  String ACK = "ACK";

  String INFO = "INFO";
}
