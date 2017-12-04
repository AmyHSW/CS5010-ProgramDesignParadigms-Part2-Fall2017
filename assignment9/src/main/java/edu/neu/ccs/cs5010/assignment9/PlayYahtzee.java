package edu.neu.ccs.cs5010.assignment9;

import edu.neu.ccs.cs5010.assignment9.cmdhandler.CmdHandler;
import edu.neu.ccs.cs5010.assignment9.cmdhandler.ICmdHandler;
import edu.neu.ccs.cs5010.assignment9.controller.IPlayer;
import edu.neu.ccs.cs5010.assignment9.controller.YahtzeePlayer;
import edu.neu.ccs.cs5010.assignment9.exceptions.InvalidHostnameException;
import edu.neu.ccs.cs5010.assignment9.exceptions.InvalidInputArgumentException;

import java.io.IOException;
import java.net.Socket;

public class PlayYahtzee {
  public static void main(String[] args) {
    // parses the command-line arguments
    ICmdHandler cmdHandler = new CmdHandler(args);
    if (!cmdHandler.isValid()) {
      throw new InvalidInputArgumentException(cmdHandler.getErrorMessage());
    }

    // creates the socket
    String hostname = cmdHandler.getHostname();
    int portNumber = cmdHandler.getPortNumber();
    Socket socket = createSocket(hostname, portNumber);

    // initializes a new player and starts the game
    IPlayer player = new YahtzeePlayer(socket);
    player.playGame();
  }

  protected static Socket createSocket(String hostname, int portnumber) {
    try {
      return new Socket(hostname, portnumber);
    } catch (IOException ioe) {
      System.err.println("Couldn't get I/O for the connection to the host " + hostname);
      throw new InvalidHostnameException(hostname + " is invalid");
    }
  }
}
