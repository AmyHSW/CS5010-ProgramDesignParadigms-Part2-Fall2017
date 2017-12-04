package edu.neu.ccs.cs5010.assignment9.controller;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class YahtzeePlayerTest {
  private Socket mockServiceSocket;
  private ByteArrayOutputStream outputStream;
  private ByteArrayInputStream inputStream;
  private String serverMsg = "INFO: joinning the game\n"
    + "START_GAME: \n" + "START_ROUND: 1\n" + "START_TURN: \n"
      + "CHOOSE_DICE: 1 2 3 4 5\n" + "INVALID_DICE_CHOICE: \n"
      + "CHOOSE_SCORE: 1 2 3 4 5 LargeStraight FullHouse Yahtzee\n"
      + "SCORE_CHOICE_VALID: Aces -1 Twos -1 LargeStraight 40 Total 40\n"
      + "TURN_OVER: \n"
      + "ROUND_OVER: 1\n"
      + "GAME_OVER: Player1 40\n"
      + "ACK: Thank you\n";

  @Before
  public void setUp() throws Exception {
    mockServiceSocket = mock(Socket.class);
    outputStream = new ByteArrayOutputStream();
    inputStream = new ByteArrayInputStream(serverMsg.getBytes(StandardCharsets.UTF_8.name()));
    try {
      when(mockServiceSocket.getOutputStream()).thenReturn(outputStream);
      when(mockServiceSocket.getInputStream()).thenReturn(inputStream);
      when(mockServiceSocket.isClosed()).thenReturn(false);
    } catch (IOException ioe) {
      fail(ioe.getMessage());
    }
  }

  @Test
  public void playGame() throws Exception {
    IPlayer player = new YahtzeePlayer(mockServiceSocket);
    player.playGame();
    //outputStream.
  }
}