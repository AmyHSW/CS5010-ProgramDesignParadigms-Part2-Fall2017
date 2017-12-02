package edu.neu.ccs.cs5010.assignment9;

import edu.neu.ccs.cs5010.assignment9.exception.InvalidInputArgumentException;

public class PlayYahtzee {
  public static void main(String[] args) {
    // parses the command-line arguments
    ICmdHandler cmdHandler = new CmdHandler(args);
    if (!cmdHandler.isValid()) {
      throw new InvalidInputArgumentException(cmdHandler.getErrorMessage());
    }
    String hostname = cmdHandler.getHostname();
    int portNumber = cmdHandler.getPortNumber();

    // initializes a new game and starts the game
    YahtzeeGame game = new YahtzeeGame(hostname, portNumber);
    game.start();
  }
}
