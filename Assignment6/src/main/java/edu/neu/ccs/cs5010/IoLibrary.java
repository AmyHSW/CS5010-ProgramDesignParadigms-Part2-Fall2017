package edu.neu.ccs.cs5010;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class IoLibrary implements IIoLibrary {
  @Override
  public List<String> parseToLines(String filename) {
    List<String> lines = new ArrayList<>();
    try (BufferedReader inputFile =
                 new BufferedReader(
                         new InputStreamReader(new FileInputStream(filename),
                                               StandardCharsets.UTF_8))) {
      String line;
      while ((line = inputFile.readLine()) != null) {
        lines.add(line);
      }
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
    }
    return lines;
  }
}
