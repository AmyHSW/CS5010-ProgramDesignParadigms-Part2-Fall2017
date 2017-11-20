package edu.neu.ccs.cs5010.skier;

public interface ISkier extends Comparable<ISkier> {

  void incrementNumRides();
  void increaseVerticalMeters(int delta);
  String getSkierId();
  int getNumRides();
  int getVerticalMeters();
}
