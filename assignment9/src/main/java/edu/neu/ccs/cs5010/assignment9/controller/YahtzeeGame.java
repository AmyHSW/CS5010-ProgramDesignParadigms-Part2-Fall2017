package edu.neu.ccs.cs5010.assignment9.controller;

import edu.neu.ccs.cs5010.assignment9.messages.ClientMessageGenerator;
import edu.neu.ccs.cs5010.assignment9.parser.ServerMessageParser;
import edu.neu.ccs.cs5010.assignment9.frames.ServerFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class YahtzeeGame {

  private final String hostname;
  private final int portNumber;

  public YahtzeeGame(String hostname, int portNumebr) {
    this.hostname = hostname;
    this.portNumber = portNumebr;
  }

  public void start() {
    try (
        Socket socket = new Socket(hostname, portNumber);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
    ) {
      String fromServer;

      while ((fromServer = in.readLine()) != null) {
        System.out.println("Server: " + fromServer);
        ServerMessageParser parser = new ServerMessageParser(fromServer);
        if (parser.getFrame().endsWith(ServerFrame.GAME_OVER)) {
          break;
        }
        ClientMessageGenerator messageGenerator = new ClientMessageGenerator(parser);
        String fromUser = messageGenerator.getClientMessage();
        if (fromUser != null) {
          System.out.println("Client: " + fromUser);
          out.println(fromUser);
        }
      }
      socket.close();
    } catch (UnknownHostException e) {
      System.err.println("Don't know about host " + hostname);
      System.exit(1);
    } catch (IOException e) {
      System.err.println("Couldn't get I/O for the connection to the host" + hostname);
    }
  }
}
