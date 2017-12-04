package edu.neu.ccs.cs5010.assignment9.controller;

import edu.neu.ccs.cs5010.assignment9.frames.ServerFrame;
import edu.neu.ccs.cs5010.assignment9.messages.client.ClientMsgGenerator;
import edu.neu.ccs.cs5010.assignment9.messages.client.IClientMsgGenerator;
import edu.neu.ccs.cs5010.assignment9.messages.client.IMessage;
import edu.neu.ccs.cs5010.assignment9.messages.server.ITranslator;
import edu.neu.ccs.cs5010.assignment9.messages.server.ServerMessageTranslator;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class YahtzeePlayer implements IPlayer {

  private final Socket socket;

  public YahtzeePlayer(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void playGame() {
    try (PrintWriter out = new PrintWriter(new OutputStreamWriter(
                  socket.getOutputStream(),StandardCharsets.UTF_8), true);
         BufferedReader stdin = new BufferedReader(
              new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))) {
      String fromServer;
      ITranslator translator = new ServerMessageTranslator();
      IClientMsgGenerator messageGenerator = new ClientMsgGenerator();
      while ((fromServer = stdin.readLine()) != null) {
        System.out.println(translator.translate(fromServer));
        if (fromServer.startsWith(ServerFrame.GAME_OVER)) {
          break;
        }
        IMessage fromUser = messageGenerator.getClientMsg(fromServer);
        out.println(fromUser);
      }
      socket.close();
    } catch (IOException e) {
      System.err.println("Couldn't get I/O for the connection to the host");
    }
  }
}
