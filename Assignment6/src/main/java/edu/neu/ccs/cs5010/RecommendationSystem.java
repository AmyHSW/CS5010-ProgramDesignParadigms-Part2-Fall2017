package edu.neu.ccs.cs5010;

public class RecommendationSystem {
  public static void main(String[] args) {
    INetwork network = new Network();
    IIoLibrary ioLibrary = new IoLibrary();
    ICsvParser nodesCsvParser = new CsvParser(ioLibrary.parseToLines("nodes"));
    IUserGenerator userGenerator = new UserGenerator(nodesCsvParser);
    while (userGenerator.hasNextUser()) {
      IUser user = userGenerator.getNextUser();
      network.addUser(user);
    }
    ICsvParser edgesCsvParser = new CsvParser(ioLibrary.parseToLines("edges"));
    IEdgeGenerator edgeGenerator = new EdgeGenerator(edgesCsvParser);
    while (edgeGenerator.hasNextEdge()) {
      IEdge edge = edgeGenerator.getNextEdge();
      network.addEdge(edge);
    }

  }
}
