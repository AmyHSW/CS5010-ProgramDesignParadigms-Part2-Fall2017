package edu.neu.ccs.cs5010;

import edu.neu.ccs.cs5010.INodesCsvParser;
import edu.neu.ccs.cs5010.IUser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class NodesCsvParser implements INodesCsvParser {
  private List<String> userInfoLines;
  private static final int NODE_ID_INDEX = 0;
  private static int CREATED_DATE_INDEX = 1;
  private static int GENDER_INDEX = 2;
  private static int AGE_INDEX = 3;
  private static int CITY_INDEX = 4;

  public NodesCsvParser(List<String> stringLines) {
    userInfoLines = stringLines;
  }

  @Override
  public Iterator<IUser> iterator() {
    return new UserIterator();
  }

  private class UserIterator implements Iterator<IUser> {
    int index = 1;

    @Override
    public boolean hasNext() {
      return index < userInfoLines.size();
    }

    @Override
    public IUser next() {
      String currentUserLine = userInfoLines.get(index);
      String[] elements = currentUserLine.split(",");
      int userId = Integer.parseInt(elements[NODE_ID_INDEX]);
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
      LocalDate createdDate = LocalDate.parse(elements[CREATED_DATE_INDEX], formatter);
      char gender = elements[GENDER_INDEX].charAt(0);
      int age = Integer.parseInt(elements[AGE_INDEX]);
      index++;
      return new User(userId, createdDate, gender, age, elements[CITY_INDEX]);
    }
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    NodesCsvParser that = (NodesCsvParser) other;

    return userInfoLines != null ? userInfoLines.equals(that.userInfoLines) : that.userInfoLines == null;
  }

  @Override
  public int hashCode() {
    return userInfoLines != null ? userInfoLines.hashCode() : 0;
  }
}
