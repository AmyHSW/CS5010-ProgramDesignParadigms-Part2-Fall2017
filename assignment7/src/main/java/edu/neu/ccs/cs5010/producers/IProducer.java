package edu.neu.ccs.cs5010.producers;

public interface IProducer<T> extends Runnable {

  T produce(String[] record);
}
