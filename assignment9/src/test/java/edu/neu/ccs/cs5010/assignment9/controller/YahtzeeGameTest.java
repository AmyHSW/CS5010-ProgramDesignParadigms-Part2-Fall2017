package edu.neu.ccs.cs5010.assignment9.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class YahtzeeGameTest {
  private ServerSocket mockServiceSocket;
  private Socket mockTestClientSocket;

  @Before
  public void setUp() throws Exception {
    mockServiceSocket = mock(ServerSocket.class);
    mockTestClientSocket = mock(Socket.class);
    try {
      when(mockServiceSocket.accept()).thenReturn(mockTestClientSocket);
    } catch () {

    }
  }
  @Test
  public void start() throws Exception {

  }
}