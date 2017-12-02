package edu.neu.ccs.cs5010.assignment9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class YahtzeeGame {

  private Socket socket;

  public YahtzeeGame(String hostname, int portNumebr) {
    setupSocket(hostname, portNumebr);
  }

  private void setupSocket(String hostname, int portNumber) {
    try {
      socket = new Socket(hostname, portNumber);
    } catch (IOException ioe) {
      System.err.println("Couldn't get I/O for the connection to " + hostname);
    }
  }

  public void start() {
    try (
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
    ) {
      BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
      String fromServer;
      String fromUser;

      while ((fromServer = in.readLine()) != null) {
        System.out.println("Server: " + fromServer);

        fromUser = stdin.readLine();
        if (fromUser != null) {
          System.out.println("Client: " + fromUser);
          out.println(fromUser);
        }
      }
    } catch (UnknownHostException e) {
      System.err.println("Don't know about host");
      System.exit(1);
    } catch (IOException e) {
      System.err.println("Couldn't get I/O for the connection to the host");
    }
  }
}
