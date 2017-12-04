package edu.neu.ccs.cs5010.assignment9.messages.client;

/**
 * The IClientMsgGenerator interface represents a ClientMsgGenerator.
 *
 * @author Shuwan Huang, Jingyu Shen
 */
public interface IClientMsgGenerator {
  /**
   * Returns the client's message.
   * @param fromServer the string message from the server
   * @return the client's message.
   */
  IMessage getClientMsg(String fromServer);
}
