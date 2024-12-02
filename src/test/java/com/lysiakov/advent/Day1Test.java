package com.lysiakov.advent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day1Test {

  @Test
  void solve() {
    var ans = new Day1().solve("day1.txt");
    Assertions.assertEquals(11, ans);
  }

  @Test
  void solve2() {
    var ans = new Day1().solve2("day1.txt");
    Assertions.assertEquals(31, ans);
  }
}
