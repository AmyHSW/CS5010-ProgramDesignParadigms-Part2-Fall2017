package edu.neu.ccs.cs5010.processors;

import java.util.List;

public interface IProcessor {
  void processInput() throws InterruptedException;
  List<String> getSkierOutput();
  List<String> getLiftOutput();
  List<String> getHourOutput();
}
