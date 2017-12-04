package edu.neu.ccs.cs5010.assignment9;

import edu.neu.ccs.cs5010.assignment9.cmdhandler.CmdHandler;
import edu.neu.ccs.cs5010.assignment9.cmdhandler.ICmdHandler;
import edu.neu.ccs.cs5010.assignment9.controller.IPlayer;
import edu.neu.ccs.cs5010.assignment9.controller.YahtzeePlayer;
import edu.neu.ccs.cs5010.assignment9.exceptions.InvalidInputArgumentException;

public class PlayYahtzee {
  public static void main(String[] args) {
    // parses the command-line arguments
    ICmdHandler cmdHandler = new CmdHandler(args);
    if (!cmdHandler.isValid()) {
      throw new InvalidInputArgumentException(cmdHandler.getErrorMessage());
    }
    String hostname = cmdHandler.getHostname();
    int portNumber = cmdHandler.getPortNumber();

    // initializes a new player and starts the game
    IPlayer player = new YahtzeePlayer(hostname, portNumber);
    player.playGame();
  }
}
