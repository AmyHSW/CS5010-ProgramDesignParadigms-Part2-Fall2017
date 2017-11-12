package edu.neu.ccs.cs5010;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

public class UserGenerator implements IUserGenerator {

  private ICsvParser csvParser;
  private static final String DATE_FORMAT = "M/d/yy";
  private static final int NODE_ID_INDEX = 0;
  private static final int CREATED_DATE_INDEX = 1;
  private static final int GENDER_INDEX = 2;
  private static final int AGE_INDEX = 3;
  private static final int CITY_INDEX = 4;

  public UserGenerator(ICsvParser csvParser) {
    this.csvParser = csvParser;
  }

  @Override
  public boolean hasNextUser() {
    return csvParser.hasNextLine();
  }

  @Override
  public IUser getNextUser() {
    if (!hasNextUser()) {
      throw new NoSuchElementException("No more users can be generated");
    }
    List<String> elements = csvParser.getNextLine();
    int userId = Integer.parseInt(elements.get(NODE_ID_INDEX));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    LocalDate createdDate = LocalDate.parse(elements.get(CREATED_DATE_INDEX), formatter);
    char gender = elements.get(GENDER_INDEX).charAt(0);
    int age = Integer.parseInt(elements.get(AGE_INDEX));
    return IUser.createUser(userId, createdDate, gender, age, elements.get(CITY_INDEX));
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    UserGenerator that = (UserGenerator) other;

    return csvParser != null ? csvParser.equals(that.csvParser) : that.csvParser == null;
  }

  @Override
  public int hashCode() {
    return csvParser != null ? csvParser.hashCode() : 0;
  }
}
