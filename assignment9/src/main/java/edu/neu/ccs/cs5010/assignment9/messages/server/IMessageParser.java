package edu.neu.ccs.cs5010.assignment9.messages.server;

/**
 * The IMessageParser interface represents a ServerMessageParser.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public interface IMessageParser {
  /**
   * Returns the server's frame.
   * @return the server's frame.
   */
  String getFrame();

  /**
   * Returns the server's payload.
   * @return the server's payload.
   */
  String getPayload();
}
