package com.bijansoft.graphs;

import com.bijansoft.graphs.Graph.Node;
import com.bijansoft.graphs.Graph.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

public class GraphMain {

  private static Graph createNewGraph() {
    Graph g = new Graph();
    Node[] temp = new Node[6];

    temp[0] = new Node("a", 3);
    temp[1] = new Node("b", 1);
    temp[2] = new Node("c", 0);
    temp[3] = new Node("d", 1);
    temp[4] = new Node("e", 1);
    temp[5] = new Node("f", 0);

    temp[0].addAdjacent(temp[1]);
    temp[0].addAdjacent(temp[2]);
    temp[0].addAdjacent(temp[3]);
    temp[1].addAdjacent(temp[0]);
    temp[3].addAdjacent(temp[4]);
    temp[4].addAdjacent(temp[5]);
    for (int i = 0; i < 6; i++) {
      g.addNode(temp[i]);
    }
    return g;
  }

  private static boolean searchDfs(Graph g, Node start, Node end) {
    if (start.getState() == State.VISITED) {
      return false;
    }
    start.setState(State.VISITED);
    for (Node adjNode : start.getAdjacents()) {
      if (adjNode == end) {
        System.out.print(adjNode.getVertex() + " <-- ");
        return true;
      }
      if (searchDfs(g, adjNode, end)) {
        System.out.print(adjNode.getVertex() + " <-- ");
        return true;
      }
    }
    return false;
  }

  private static boolean searchBfs(Graph g, Node start, Node end) {
    Queue<Node> queue = new LinkedList<>();
    queue.add(start);
    while (!queue.isEmpty()) {
      Node node = queue.remove();
      node.setState(State.VISITED);
      for (Node adjNode : node.getAdjacents()) {
        if (adjNode.getState() == State.VISITED) {
          continue;
        }
        adjNode.setFrom(node);
        if (adjNode == end) {
          Node parent = adjNode;
          while (parent != null) {
            System.out.print(" <-- " + parent.getVertex());
            parent = parent.getFrom();
          }
          return true;
        }
        queue.add(adjNode);
      }
    }
    return false;
  }

  private static List<String> findBuildOrder(List<String> projects,
      List<List<String>> dependencies) {
    Objects.requireNonNull(projects);
    Objects.requireNonNull(dependencies);
    if (projects.size() != dependencies.size()) {
      throw new IllegalArgumentException("Size of projects must be equal to size of dependencies");
    }

    List<Node> vertices = buildGraph(projects, dependencies);
    int size = vertices.size();
    List<String> order = new ArrayList<>(size);

    for (Node node : vertices) {
      addToOrderList(node, node, order);
    }

    return order;
  }

  private static void addToOrderList(Node startNode, Node node, List<String> order) {
    if (node.getState() == State.VISITED) {
      return;
    }
    node.setState(State.VISITED);
    for (Node adjacent : node.getAdjacents()) {
      adjacent.setFrom(node);

      // Cycle detection
      if (startNode == adjacent) {
        System.out.println("\u001B[31m");
        System.out.printf("Cycle: %s", startNode.getVertex());
        Node cycleNode = startNode.getFrom();
        while (cycleNode != null && cycleNode != startNode) {
          System.out.printf(" <- %s", cycleNode.getVertex());
          cycleNode = cycleNode.getFrom();
        }
        System.out.printf(" <- %s\n", startNode.getVertex());
        System.out.println("\u001B[0m");
      }

      addToOrderList(startNode, adjacent, order);
    }

    order.add(node.getVertex());
  }

  private static List<Node> buildGraph(List<String> keys, List<List<String>> dependencies) {
    int size = keys.size();

    List<Node> vertices = new ArrayList<>(size);
    Map<String, Node> map = new HashMap<>(size);
    for (int i = 0; i < size; i++) {
      Node node = new Node(keys.get(i), dependencies.get(i).size());
      map.put(node.getVertex(), node);
      vertices.add(node);
    }

    for (int i = 0; i < size; i++) {
      for (String dependency : dependencies.get(i)) {
        vertices.get(i).addAdjacent(map.get(dependency));
      }
    }
    return vertices;
  }

  public static void main(String[] args) {
    List<String> projects = Arrays.asList("a", "b", "c", "d", "e", "f");

    List<List<String>> dependencies = new ArrayList<>(projects.size());
    dependencies.add(Collections.singletonList("f"));
    dependencies.add(Collections.singletonList("f"));
    dependencies.add(Collections.singletonList("d"));
    dependencies.add(Arrays.asList("a", "b"));
    dependencies.add(Collections.emptyList());
//    dependencies.add(Collections.emptyList());
    dependencies.add(Collections.singletonList("c"));

    List<String> order = findBuildOrder(projects, dependencies);
    System.out.println(order);
  }

//  public static void main(String[] args) {
//    Graph g = createNewGraph();
//    Node[] n = g.getVertices();
//    Node start = n[0];
//    Node end = n[5];
//    boolean result = searchDfs(g, start, end);
//    System.out.print(start.getVertex());
//    System.out.printf(
//        "\nstart: %s, end: %s, result %b: ", start.getVertex(), end.getVertex(), result);
//    System.out.println("\n=======");
//    g = createNewGraph();
//    n = g.getVertices();
//    start = n[0];
//    end = n[5];
//
//    result = searchBfs(g, start, end);
//    System.out.printf(
//        "\nstart: %s, end: %s, result %b: ", start.getVertex(), end.getVertex(), result);
//  }
}
