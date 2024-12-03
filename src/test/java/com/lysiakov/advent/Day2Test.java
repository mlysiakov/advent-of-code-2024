package com.lysiakov.advent;

import com.lysiakov.advent.y2024.Day2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day2Test {

  @Test
  void solve1() {
    var ans = new Day2().part1();
    Assertions.assertEquals(2, ans);
  }

  @Test
  void solve2() {
    var ans = new Day2().part2();
    Assertions.assertEquals(4, ans);
  }
}
