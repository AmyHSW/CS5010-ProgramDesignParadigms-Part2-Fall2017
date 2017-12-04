package edu.neu.ccs.cs5010.assignment9.messages.server;

import edu.neu.ccs.cs5010.assignment9.exceptions.InvalidServerFrameException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServerMessageTranslatorTest {

  private ITranslator translator;

  @Before
  public void setUp() throws Exception {
    translator = new ServerMessageTranslator();
  }

  @Test(expected = InvalidServerFrameException.class)
  public void expectedInvalidServerFrameException() throws Exception {
    translator.translate("WRONG_FRAME: ");
  }

}