package edu.neu.ccs.cs5010.processor;

import java.util.List;

public interface IProcessor {
  void processInput();
  List<String> getSkierOutput();
  List<String> getLiftOutput();
  List<String> getHourOutput();
}
