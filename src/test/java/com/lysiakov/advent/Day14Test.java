package com.lysiakov.advent;

import com.lysiakov.advent.y2024.Day14;

public class Day14Test extends AbstractPuzzleTest {

  public Day14Test() {
    super(new Day14(7, 11));
  }

  @Override
  long expectedFirstPart() {
    return 12;
  }

  @Override
  long expectedSecondPart() {
    return 11387L;
  }
}