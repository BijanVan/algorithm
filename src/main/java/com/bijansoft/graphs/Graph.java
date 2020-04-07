package com.bijansoft.graphs;

class Graph {
  private static final int MAX_VERTICES = 6;
  private Node[] vertices;

  private int count;

  public Graph() {
    vertices = new Node[MAX_VERTICES];
    count = 0;
  }

  public void addNode(Node node) {
    if (count < vertices.length) {
      vertices[count] = node;
      count++;
    } else {
      System.out.print("Graph full");
    }
  }

  public Node[] getVertices() {
    return vertices;
  }

  public int getCount() {
    return count;
  }

  static class Node {
    private Node[] adjacents;
    private int adjacentCount;
    private String vertex;
    private State state;
    private Node from;

    Node(String vertex, int adjacentLength) {
      this.vertex = vertex;
      adjacentCount = 0;
      adjacents = new Node[adjacentLength];
      state = State.UNVISITED;
      from = null;
    }

    void addAdjacent(Node node) {
      if (adjacentCount < adjacents.length) {
        this.adjacents[adjacentCount] = node;
        adjacentCount++;
      } else {
        System.out.print("No more adjacents can be added");
      }
    }

    public Node[] getAdjacents() {
      return adjacents;
    }

    public int getAdjacentCount() {
      return adjacentCount;
    }

    public String getVertex() {
      return vertex;
    }

    public State getState() {
      return state;
    }

    public Node getFrom() {
      return from;
    }

    public void setFrom(Node from) {
      this.from = from;
    }

    public void setState(State state) {
      this.state = state;
    }
  }

  enum State {
    UNVISITED,
    VISITED,
    VISITING
  }
}
