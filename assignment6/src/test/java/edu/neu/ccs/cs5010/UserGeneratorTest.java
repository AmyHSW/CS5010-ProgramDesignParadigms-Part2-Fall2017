package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserGeneratorTest {
  private IUserGenerator userGenerator1;
  private IUserGenerator userGenerator2;
  private IUserGenerator userGenerator3;
  private IUserGenerator userGenerator4;
  private IUserGenerator userGenerator5;

  @Before
  public void setUp() throws Exception {
    List<String> lines1 = new ArrayList<>();
    lines1.add("Node ID,Date created,Gender,Age,City");
    lines1.add("1,1/1/09,F,22,Seattle");
    List<String> lines2 = new ArrayList<>(lines1);
    lines2.add("2,1/15/09,F,18,Melbourne");
    ICsvParser csvParser1 = new CsvParser(lines1);
    ICsvParser csvParser2 = new CsvParser(lines2);
    userGenerator1 = new UserGenerator(csvParser1);
    userGenerator2 = new UserGenerator(csvParser1);
    userGenerator3 = new UserGenerator(csvParser2);
    userGenerator4 = new UserGenerator(null);
    userGenerator5 = new UserGenerator(null);
  }

  @Test
  public void testEquals() throws Exception {
    assertTrue("UserGenerator failed to provide the correct equal.",
            userGenerator1.equals(userGenerator1));
    assertFalse("UserGenerator failed to provide the correct equal.",
            userGenerator1.equals(null));
    assertFalse("UserGenerator failed to provide the correct equal.",
            userGenerator1.equals("1"));
    assertFalse("UserGenerator failed to provide the correct equal.",
            userGenerator1.equals(userGenerator3));
    assertFalse("UserGenerator failed to provide the correct equal.",
            userGenerator1.equals(userGenerator4));
    assertFalse("UserGenerator failed to provide the correct equal.",
            userGenerator4.equals(userGenerator2));
    assertTrue("UserGenerator failed to provide the correct equal.",
            userGenerator1.equals(userGenerator2));
    assertTrue("UserGenerator failed to provide the correct equal.",
            userGenerator4.equals(userGenerator5));
    assertFalse("UserGenerator failed to provide the correct hashcode.",
            userGenerator1.hashCode() == userGenerator3.hashCode());
    assertFalse("UserGenerator failed to provide the correct hashcode.",
            userGenerator1.hashCode() == userGenerator4.hashCode());
    assertTrue("UserGenerator failed to provide the correct hashcode.",
            userGenerator1.hashCode() == userGenerator2.hashCode());
  }

}