package com.lysiakov.advent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day1 implements Advent {

  record Input(List<Integer> left, List<Integer> right) {}

  @Override
  public Long solve(String inputFilePath) {
    var input = read(inputFilePath);
    var left = input.left();
    var right = input.right();

    Collections.sort(left);
    Collections.sort(right);

    long answer = 0L;
    int length = left.size();

    for (int i = 0; i < length; i++) {
      answer += Math.abs(left.get(i) - right.get(i));
    }
    return answer;
  }

  @Override
  public Long solve2(String inputFilePath) {
    var input = read(inputFilePath);
    var left = input.left();
    var right = input.right();

    var counter = new HashMap<Integer, Integer>();
    for (int i = 0; i < right.size(); i++) {
      counter.merge(right.get(i), 1, Integer::sum);
    }

    long  answer = 0;
    for (int i = 0; i < left.size(); i++) {
      int tmp = left.get(i);
      answer += (long) tmp * counter.getOrDefault(tmp, 0);
    }
    return answer;
  }

  private Input read(String inputFilePath) {
    List<Integer> left = new ArrayList<>();
    List<Integer> right = new ArrayList<>();

    try (Scanner scanner = InputReader.read(inputFilePath)) {
      boolean leftOrder = true;

      while (scanner.hasNextInt()) {
        int tmp = scanner.nextInt();
        if (leftOrder) {
          left.add(tmp);
        } else {
          right.add(tmp);
        }
        leftOrder = !leftOrder;
      }
    }
    return new Input(left, right);
  }

  public static void main(String[] args) {
    System.out.println(new Day1().solve("day1.txt"));
    System.out.println(new Day1().solve2("day1.txt"));
  }

}
