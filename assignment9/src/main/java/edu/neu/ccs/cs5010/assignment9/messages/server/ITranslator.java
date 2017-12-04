package edu.neu.ccs.cs5010.assignment9.messages.server;

/**
 * The ITranslator interface represents a ServerMessageTranslator.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public interface ITranslator {
  /**
   * Returns the next step instruction for the client.
   * @param message the string message from the server
   * @return the next step instruction for the client.
   */
  String translate(String message);
}
