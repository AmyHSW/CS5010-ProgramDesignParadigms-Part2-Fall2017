package edu.neu.ccs.cs5010;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class NodesCsvParser implements INodesCsvParser {
  private List<List<String>> userInfoLines;
  private static final int NODE_ID_INDEX = 0;
  private static final int CREATED_DATE_INDEX = 1;
  private static final int GENDER_INDEX = 2;
  private static final int AGE_INDEX = 3;
  private static final int CITY_INDEX = 4;

  public NodesCsvParser(List<String> stringLines) {
    userInfoLines = new ArrayList<>();
    for (int i = 1; i < stringLines.size(); i++) {
      String currentUserLine = stringLines.get(i);
      String[] elements = currentUserLine.split(",");
      userInfoLines.add(Arrays.asList(elements));
    }
  }

  @Override
  public Iterator<IUser> iterator() {
    return new UserIterator();
  }

  private class UserIterator implements Iterator<IUser> {
    int index = 0;

    @Override
    public boolean hasNext() {
      return index < userInfoLines.size();
    }

    @Override
    public IUser next() {
      List<String> elements = userInfoLines.get(index);
      int userId = Integer.parseInt(elements.get(NODE_ID_INDEX));
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
      LocalDate createdDate = LocalDate.parse(elements.get(CREATED_DATE_INDEX), formatter);
      char gender = elements.get(GENDER_INDEX).charAt(0);
      int age = Integer.parseInt(elements.get(AGE_INDEX));
      index++;
      return new User(userId, createdDate, gender, age, elements.get(CITY_INDEX));
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
