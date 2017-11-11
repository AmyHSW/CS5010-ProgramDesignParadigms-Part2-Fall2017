package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.*;

public class NetworkTest {

  private INetwork network;
  private int influencerBound = 2;
  private IUser user1, user2, user3, user4, user5, user6, user7, user8, user9, user10;

  @Before
  public void setUp() throws Exception {
    network = new Network(influencerBound);

    user1 = new User(1, LocalDate.now().minusMonths(6), 'F', 15, "Shanghai");
    user2 = new User(2, LocalDate.now().minusMonths(7), 'F', 15, "Shanghai");
    user3 = new User(3, LocalDate.now().minusMonths(3), 'F', 15, "Shanghai");
    user4 = new User(4, LocalDate.now().minusMonths(1), 'F', 15, "Shanghai");
    user5 = new User(5, LocalDate.now(), 'F', 15, "Shanghai");
    user6 = new User(6, LocalDate.now().minusMonths(2), 'F', 15, "Shanghai");
    user7 = new User(7, LocalDate.now().minusMonths(1), 'F', 15, "Shanghai");
    user8 = new User(8, LocalDate.now(), 'F', 15, "Shanghai");
    user9 = new User(9, LocalDate.now().minusMonths(6), 'F', 15, "Shanghai");
    user10 = new User(10, LocalDate.now().minusMonths(4), 'F', 15, "Shanghai");

    network.addUser(user1);
    network.addUser(user2);
    network.addUser(user3);
    network.addUser(user4);
    network.addUser(user5);
    network.addUser(user6);
    network.addUser(user7);
    network.addUser(user8);
    network.addUser(user9);
    network.addUser(user10);

    network.addEdge(new Edge(1, 2));
    network.addEdge(new Edge(1, 3));
    network.addEdge(new Edge(1, 5));
    network.addEdge(new Edge(2, 3));
    network.addEdge(new Edge(3, 5));
    network.addEdge(new Edge(4, 3));
    network.addEdge(new Edge(4, 2));
    network.addEdge(new Edge(5, 1));
    network.addEdge(new Edge(6, 2));
    network.addEdge(new Edge(8, 2));
    network.addEdge(new Edge(8, 6));
    network.addEdge(new Edge(9, 2));
    network.addEdge(new Edge(9, 4));
    network.addEdge(new Edge(10, 8));
    network.addEdge(new Edge(10, 9));

  }

  @Test
  public void getFriendsOfUser() throws Exception {
    Set<Integer> friends = network.getFriendsOfUser(1);
    assertTrue(friends.size() == 3);
    assertTrue(friends.contains(2));
    assertTrue(friends.contains(3));
    assertTrue(friends.contains(5));
  }

  @Test
  public void getInfluencers() throws Exception {
    List<Integer> influencers = network.getInfluencers();
    assertTrue(influencers.size() == 2);
    assertTrue(influencers.contains(3));
    assertTrue(influencers.contains(2));
  }

  @Test
  public void getAllUsers() throws Exception {
    Collection<IUser> allUsers = network.getAllUsers();
    assertTrue(allUsers.size() == 10);
    assertTrue(allUsers.contains(user1));
    assertTrue(allUsers.contains(user2));
  }

  @Test
  public void equals() throws Exception {
    assertTrue(!network.equals(null));
    assertTrue(!network.equals(""));
    assertTrue(network.equals(network));
    assertTrue(!network.equals(new Network(5)));
    assertTrue(!network.equals(new Network(2)));
    assertTrue((new Network(5)).equals(new Network(5)));
  }

  @Test
  public void testHashCode() throws Exception {
    assertTrue(network.hashCode() != (new Network(3)).hashCode());
  }

  @Test(expected = InvalidUserException.class)
  public void expectedInvalidUserException() throws Exception {
    network.getFriendsOfUser(88);
  }
}