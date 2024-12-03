package com.lysiakov.advent.y2024;

import com.lysiakov.advent.util.Input;
import com.lysiakov.advent.util.Puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Day1 implements Puzzle {

  private record PuzzleInput(List<Integer> left, List<Integer> right) {}

  @Override
  public Long part1() {
    var input = parse();
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
  public Long part2() {
    var input = parse();
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

  private PuzzleInput parse() {
    var left = new ArrayList<Integer>();
    var right = new ArrayList<Integer>();

    try (var scanner = Input.read(2024, 1)) {
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
    return new PuzzleInput(left, right);
  }

  public static void main(String[] args) {
    System.out.println(new Day1().part1());
    System.out.println(new Day1().part2());
  }

}
