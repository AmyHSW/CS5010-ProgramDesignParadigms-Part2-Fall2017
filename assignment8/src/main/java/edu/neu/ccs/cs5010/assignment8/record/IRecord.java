package edu.neu.ccs.cs5010.assignment8.record;

import java.io.IOException;
import java.io.RandomAccessFile;

public interface IRecord {

  void readFromFile(RandomAccessFile file) throws IOException;

  void writeToFile(RandomAccessFile file) throws IOException;

  int getParameter();
}
