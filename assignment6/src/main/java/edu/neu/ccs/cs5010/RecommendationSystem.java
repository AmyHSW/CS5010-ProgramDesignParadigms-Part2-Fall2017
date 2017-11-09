package edu.neu.ccs.cs5010;

public class RecommendationSystem {

  public static void main(String[] args) {
    ICmdHandler cmdHandler = new CmdHandler(args);
    if (!cmdHandler.isValid()) {
      throw new InvalidInputException(cmdHandler.getErrorMessage());
    }
    INetwork network = new Network();
    IIoLibrary ioLibrary = new IoLibrary();
    ICsvParser nodesCsvParser = new CsvParser(ioLibrary.parseToLines(cmdHandler.getNodeFile()));
    IUserGenerator userGenerator = new UserGenerator(nodesCsvParser);
    while (userGenerator.hasNextUser()) {
      IUser user = userGenerator.getNextUser();
      network.addUser(user);
    }
    ICsvParser edgesCsvParser = new CsvParser(ioLibrary.parseToLines(cmdHandler.getEdgeFile()));
    IEdgeGenerator edgeGenerator = new EdgeGenerator(edgesCsvParser);
    while (edgeGenerator.hasNextEdge()) {
      IEdge edge = edgeGenerator.getNextEdge();
      network.addEdge(edge);
    }

  }
}
