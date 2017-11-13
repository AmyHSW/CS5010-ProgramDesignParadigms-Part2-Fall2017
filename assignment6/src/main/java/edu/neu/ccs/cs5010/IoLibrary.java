package edu.neu.ccs.cs5010;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent a concrete IO library, to read file and write to file.
 */
public class IoLibrary {

  /**
   * Parses the given file into lines of String.
   *
   * @param filename the filename to be parse to lines of String
   * @return lines of String of the given file
   */
  public static List<String> parseToLines(String filename) {
    List<String> lines = new ArrayList<>();
    try (BufferedReader inputFile = new BufferedReader(
            new InputStreamReader(new FileInputStream(
                    filename), StandardCharsets.UTF_8))) {
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
