package edu.neu.ccs.cs5010.assignment8.editor;

import edu.neu.ccs.cs5010.assignment8.record.IRecord;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

/**
 * The DataFileEditor class is responsible for writing the records to the dat files.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class DatFileEditor implements IDatFileEditor {

  protected RandomAccessFile file;

  /**
   * Constructs a new DatFileEditor with the file name.
   * @param fileString the file name of dat file
   * @throws IOException if there is an I/O failure
   */
  public DatFileEditor(String fileString) throws IOException {
    file = new RandomAccessFile(fileString, "rw");
  }

  /**
   * Close the file at the end of editing.
   * @throws IOException if there is an I/O failure.
   */
  @Override
  public void close() throws IOException {
    if (file != null) {
      file.close();
    }
  }

  /**
   * Writes the records to the dat file one by one.
   * @param recordList the record list
   * @throws IOException if there is an I/O failure
   */
  @Override
  public void writeRecordsToFile(List<IRecord> recordList) throws IOException {
    for (IRecord record: recordList) {
      record.writeToFile(file);
    }
  }

}
