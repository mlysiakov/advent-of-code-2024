package com.lysiakov.advent.util;

public interface Puzzle {

  long part1();

  long part2();

  default void run() {
    System.out.println("Part 1: " + part1());
    System.out.println("Part 2: " + part2());
  }
}
