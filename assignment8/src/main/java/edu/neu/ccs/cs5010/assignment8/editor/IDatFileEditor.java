package edu.neu.ccs.cs5010.assignment8.editor;

import edu.neu.ccs.cs5010.assignment8.record.IRecord;

import java.io.IOException;
import java.util.List;

/**
 * The IDataFileEditor interface represents a dat file editor.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public interface IDatFileEditor {

  /**
   * Close the file at the end of editing.
   * @throws IOException if there is an I/O failure.
   */
  void close() throws IOException;

  /**
   * Writes the records to the dat file one by one.
   * @param recordList the record list
   * @throws IOException if there is an I/O failure
   */
  void writeRecordsToFile(List<IRecord> recordList) throws IOException;
}
