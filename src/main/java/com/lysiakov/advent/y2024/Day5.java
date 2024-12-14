package com.lysiakov.advent.y2024;

import com.lysiakov.advent.util.Graph;
import com.lysiakov.advent.util.Input;
import com.lysiakov.advent.util.Pair;
import com.lysiakov.advent.util.Puzzle;
import java.util.*;

public class Day5 implements Puzzle {

  public static void main(String[] args) {
    new Day5().run();
  }

  @Override
  public Long part1() {
    var input = parse();
    var graph = new Graph();
    for (var edge : input.edges()) {
      graph.addEdge(edge.left(), edge.right());
    }
    long answer = 0L;
    for (List<Integer> page : input.pages()) {
      if (validOrder(page, graph)) {
        int index = page.size() / 2;
        answer += page.get(index);
      }
    }
    return answer;
  }

  @Override
  public Long part2() {
    var input = parse();
    var graph = new Graph();
    for (var edge : input.edges()) {
      graph.addEdge(edge.left(), edge.right());
    }
    long answer = 0L;
    for (List<Integer> page : input.pages()) {
      if (!validOrder(page, graph)) {
        List<Integer> sortedPage = sort(graph.subgraph(page));
        int index = sortedPage.size() / 2;
        answer += sortedPage.get(index);
      }
    }
    return answer;
  }

  private boolean validOrder(List<Integer> pages, Graph graph) {
    for (int i = pages.size() - 1; i >= 0; i--) {
      int vertex = pages.get(i);
      for (int j = i - 1; j >= 0; j--) {
        if (graph.containsEdge(vertex, pages.get(j))) {
          return false;
        }
      }
    }
    return true;
  }

  private List<Integer> sort(Graph graph) {

    Map<Integer, Set<Integer>> adjList = graph.getAdjList();
    Map<Integer, Integer> mappedAdjList = graph.getMappedAdjList();
    int[] degree = new int[graph.size()];

    for (int vertex : adjList.keySet()) {
      for (int edge : adjList.get(vertex)) {
        degree[mappedAdjList.get(edge)]++;
      }
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int vertex : adjList.keySet()) {
      if (degree[mappedAdjList.get(vertex)] == 0) {
        queue.add(vertex);
      }
    }

    List<Integer> sorted = new ArrayList<>();

    while (!queue.isEmpty()) {
      int vertex = queue.poll();
      sorted.add(vertex);
      for (int edge : adjList.get(vertex)) {
        int mappedEdge = mappedAdjList.get(edge);
        degree[mappedEdge]--;
        if (degree[mappedEdge] == 0) {
          queue.add(edge);
        }
      }
    }
    return sorted;
  }

  private record PuzzleInput(List<Pair<Integer, Integer>> edges, List<List<Integer>> pages) {}

  private PuzzleInput parse() {
    List<Pair<Integer, Integer>> edges = new ArrayList<>();
    List<List<Integer>> pages = new ArrayList<>();

    try (var scanner = Input.read(2024, 5)) {
      while (scanner.hasNext()) {
        String line = scanner.nextLine();
        if (line.contains("|")) {
          String[] tmp = line.split("\\|");
          edges.add(new Pair<>(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1])));
        } else if (!line.isBlank()) {
          var list = Arrays.stream(line.split(",")).map(Integer::parseInt).toList();
          pages.add(list);
        }
      }
    }
    return new PuzzleInput(edges, pages);
  }
}
