package edu.neu.ccs.cs5010.assignment8.editor;

import edu.neu.ccs.cs5010.assignment8.record.IRecord;

import java.io.IOException;
import java.util.List;

public interface IDatFileEditor {

  void close() throws IOException;
  void writeRecordsToFile(List<IRecord> recordList) throws IOException;
}
