package edu.neu.ccs.cs5010;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * The IoLibrary represents a IO library.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public class IoLibrary {
  /**
   * Generates an output file with the given filename and content.
   *
   * @param filename the filename of the output file
   * @param outputLines the lines of String to be written in the output file
   */
  public static void generateOutput(String filename, List<String> outputLines) {
    try (BufferedWriter outputFile = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(
                    filename), StandardCharsets.UTF_8)); ) {
      for (int i = 0; i < outputLines.size(); i++) {
        outputFile.write(outputLines.get(i) + "\n");
      }
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
    }
  }
}
