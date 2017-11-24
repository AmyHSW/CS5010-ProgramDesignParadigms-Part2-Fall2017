package edu.neu.ccs.cs5010;

import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

public class SkiDataProcessorTest {

  @Test
  public void main() throws Exception {
    String[] args = new String[0];
    SkiDataProcessor.main(args);
    Assert.assertEquals(new String(Files.readAllBytes(Paths.get
        ("hours0.csv"))), new String(Files.readAllBytes(Paths.get
        ("hours.csv"))));
    Assert.assertEquals(new String(Files.readAllBytes(Paths.get
        ("lifts0.csv"))), new String(Files.readAllBytes(Paths.get
        ("lifts.csv"))));
    Assert.assertEquals(new String(Files.readAllBytes(Paths.get
        ("skiers0.csv"))), new String(Files.readAllBytes(Paths.get
        ("skier.csv"))));
  }

}