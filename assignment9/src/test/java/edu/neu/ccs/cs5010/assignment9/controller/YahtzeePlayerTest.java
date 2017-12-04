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
  private static final String LINE_SEPARATOR = System.lineSeparator();
  private static final String SERVER_MSG = "INFO: joining the game" + LINE_SEPARATOR
      + "START_GAME: "  + LINE_SEPARATOR + "START_ROUND: 1" + LINE_SEPARATOR
      + "START_TURN: " + LINE_SEPARATOR + "CHOOSE_DICE: 1 2 3 4 5" + LINE_SEPARATOR
      + "INVALID_DICE_CHOICE: " + LINE_SEPARATOR
      + "CHOOSE_SCORE: 1 2 3 4 5 LargeStraight FullHouse Yahtzee" + LINE_SEPARATOR
      + "SCORE_CHOICE_VALID: Aces -1 Twos -1 LargeStraight 40 Total 40" + LINE_SEPARATOR
      + "SCORE_CHOICE_INVALID: 1 2 3 4 5 LargeStraight FullHouse Yahtzee" + LINE_SEPARATOR
      + "TURN_OVER: " + LINE_SEPARATOR
      + "ROUND_OVER: 1" + LINE_SEPARATOR
      + "ACK: Thank you" + LINE_SEPARATOR
      + "GAME_OVER: Player1 40" + LINE_SEPARATOR;

  @Before
  public void setUp() throws Exception {
    mockServiceSocket = mock(Socket.class);
    outputStream = new ByteArrayOutputStream();
    inputStream = new ByteArrayInputStream(SERVER_MSG.getBytes(StandardCharsets.UTF_8.name()));
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
    String input = "1 1 1 1 1 1" + LINE_SEPARATOR + "1 1 1 1 0" + LINE_SEPARATOR
        + "Yahtzee" + LINE_SEPARATOR;
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    IPlayer player = new YahtzeePlayer(mockServiceSocket);
    player.playGame();
    String fromUser = LINE_SEPARATOR + LINE_SEPARATOR + LINE_SEPARATOR + LINE_SEPARATOR
    + "KEEP_DICE:1 2 3 4 5 1 1 1 1 0" + LINE_SEPARATOR + LINE_SEPARATOR
    + "SCORE_CHOICE:Yahtzee" + LINE_SEPARATOR + LINE_SEPARATOR + LINE_SEPARATOR
        + LINE_SEPARATOR + LINE_SEPARATOR + LINE_SEPARATOR;
    assertEquals(outputStream.toString(), fromUser);
  }
}