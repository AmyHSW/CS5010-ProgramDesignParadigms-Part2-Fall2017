package edu.neu.ccs.cs5010.assignment9;

import edu.neu.ccs.cs5010.assignment9.exceptions.InvalidHostnameException;
import edu.neu.ccs.cs5010.assignment9.exceptions.InvalidInputArgumentException;
import org.junit.Before;
import org.junit.Test;

public class PlayYahtzeeTest {

  @Before
  public void setUp() throws Exception {

  }

  @Test
  public void main() throws Exception {

  }

  @Test
  public void createSocket() throws Exception {
  }

  @Test(expected = InvalidInputArgumentException.class)
  public void expectedInvalidInputArgumentException() throws Exception {
    PlayYahtzee.main(new String[] {"host"});
  }

  @Test(expected = InvalidHostnameException.class)
  public void expectedInvalidHostnameException() throws Exception {
    PlayYahtzee.main(new String[] {"host", "1000"});
  }

}