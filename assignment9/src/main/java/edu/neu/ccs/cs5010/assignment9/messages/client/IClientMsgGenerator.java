package edu.neu.ccs.cs5010.assignment9.messages.client;

public interface IClientMsgGenerator {

  IMessage getClientMsg(String fromServer);
}
