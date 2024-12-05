package com.lysiakov.advent.util;

import java.util.*;

// Implemented by adjacency list
public class Graph {

  private Map<Integer, Set<Integer>> adj;

  public Graph() {
    adj = new HashMap<>();
  }

  public void addVertex(int vertex) {
    adj.putIfAbsent(vertex, new HashSet<>());
  }

  public void addEdge(int u, int v) {
    adj.putIfAbsent(u, new HashSet<>());
    adj.get(u).add(v);
  }

  public boolean containsEdge(int u, int v) {
    return Optional.ofNullable(adj.get(u)).map(set -> set.contains(v)).orElse(false);
  }

  public Graph subgraph(List<Integer> vertices) {
    Set<Integer> verticesSet = new HashSet<>(vertices);
    var subgraph = new Graph();
    for (int u : vertices) {
      subgraph.addVertex(u);
      for (int v : adj.getOrDefault(u, Set.of())) {
        if (verticesSet.contains(v)) {
          subgraph.addEdge(u, v);
        }
      }
    }
    return subgraph;
  }

  public int size() {
    return adj.size();
  }

  public Map<Integer, Set<Integer>> getAdjList() {
    return Collections.unmodifiableMap(adj);
  }

  public Map<Integer, Integer> getMappedAdjList() {
    Map<Integer, Integer> mapping = new HashMap<>();
    int index = 0;
    for (int vertex : adj.keySet()) {
      mapping.put(vertex, index++);
    }
    return Collections.unmodifiableMap(mapping);
  }
}
