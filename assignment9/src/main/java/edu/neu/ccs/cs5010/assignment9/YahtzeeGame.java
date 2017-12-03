package edu.neu.ccs.cs5010.assignment9;

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
      //BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
      String fromServer;
      //String fromUser;

      while ((fromServer = in.readLine()) != null) {
        System.out.println("Server: " + fromServer);
        ServerMessageParser parser = new ServerMessageParser(fromServer);
        ClientMessageGenerator messageGenerator = new ClientMessageGenerator(parser);
        System.out.println("Client: " + messageGenerator.getClientMessage());
        //fromUser = stdin.readLine();
        //if (fromUser != null) {
        if (messageGenerator.getClientMessage() != null) {
          //System.out.println("Client: " + fromUser);
          out.println(messageGenerator.getClientMessage());
          //out.println(fromUser);
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
