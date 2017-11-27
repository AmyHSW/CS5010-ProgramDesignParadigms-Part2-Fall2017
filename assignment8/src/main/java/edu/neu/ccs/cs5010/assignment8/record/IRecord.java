package edu.neu.ccs.cs5010.assignment8.record;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * The IRecord interface represents a record.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public interface IRecord {

  /**
   * Reads from the dat file to update the fields of this record.
   * @param file the dat file
   * @throws IOException if there is an I/O failure
   */
  void readFromFile(RandomAccessFile file) throws IOException;

  /**
   * Writes the information of this record to the file.
   * @param file the dat file.
   * @throws IOException if there is an I/O failure.
   */
  void writeToFile(RandomAccessFile file) throws IOException;

  /**
   * Returns the parameter of this record.
   * @return the parameter of this record.
   */
  int getParameter();
}
