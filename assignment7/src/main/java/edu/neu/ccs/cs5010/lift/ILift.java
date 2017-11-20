package edu.neu.ccs.cs5010.lift;

public interface ILift extends Comparable<ILift> {

  void incrementNumber();
  String getLiftId();
  int getNumber();
}
