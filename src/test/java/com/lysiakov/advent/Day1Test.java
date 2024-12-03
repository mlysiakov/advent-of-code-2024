package com.lysiakov.advent;

import com.lysiakov.advent.y2024.Day1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day1Test {

  @Test
  void solve1() {
    var ans = new Day1().part1();
    Assertions.assertEquals(11, ans);
  }

  @Test
  void solve2() {
    var ans = new Day1().part2();
    Assertions.assertEquals(31, ans);
  }
}
