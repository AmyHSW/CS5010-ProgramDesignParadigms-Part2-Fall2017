package edu.neu.ccs.cs5010.assignment9.controller;

import edu.neu.ccs.cs5010.assignment9.frames.ServerFrame;
import edu.neu.ccs.cs5010.assignment9.messages.client.ClientMsgGenerator;
import edu.neu.ccs.cs5010.assignment9.messages.client.IClientMsgGenerator;
import edu.neu.ccs.cs5010.assignment9.messages.server.ITranslator;
import edu.neu.ccs.cs5010.assignment9.messages.server.ServerMessageTranslator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class YahtzeeGame implements IGame {

  private final String hostname;
  private final int portNumber;

  public YahtzeeGame(String hostname, int portNumebr) {
    this.hostname = hostname;
    this.portNumber = portNumebr;
  }

  @Override
  public void start() {
    try (
        Socket socket = new Socket(hostname, portNumber);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
    ) {
      String fromServer;
      ITranslator translator = new ServerMessageTranslator();
      while ((fromServer = in.readLine()) != null) {
        System.out.println(translator.translate(fromServer));
        if (fromServer.startsWith(ServerFrame.GAME_OVER)) {
          break;
        }
        IClientMsgGenerator messageGenerator = new ClientMsgGenerator(fromServer);
        String fromUser = messageGenerator.getClientMsg();
        if (fromUser != null) {
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
