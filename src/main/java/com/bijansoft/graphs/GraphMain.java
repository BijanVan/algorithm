package com.bijansoft.graphs;

import com.bijansoft.graphs.Graph.Node;
import com.bijansoft.graphs.Graph.State;
import java.util.LinkedList;
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
    if (start.getState() == State.VISITED) return false;
    start.setState(State.VISITED);
    for (Node adjNode : start.getAdjacent()) {
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
      for (Node adjNode : node.getAdjacent()) {
        if (adjNode.getState() == State.VISITED) continue;
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

  public static void main(String[] args) {
    Graph g = createNewGraph();
    Node[] n = g.getVertices();
    Node start = n[0];
    Node end = n[5];
    boolean result = searchDfs(g, start, end);
    System.out.print(start.getVertex());
    System.out.printf(
        "\nstart: %s, end: %s, result %b: ", start.getVertex(), end.getVertex(), result);
    System.out.println("\n=======");
    result = searchBfs(g, start, end);
    System.out.printf(
        "start: %s, end: %s, result %b: ", start.getVertex(), end.getVertex(), result);
  }
}
