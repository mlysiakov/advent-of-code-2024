package com.lysiakov.advent;

import com.lysiakov.advent.y2024.Day1;

public class Day1Test extends AbstractPuzzleTest {

  public Day1Test() {
    super(new Day1());
  }

  @Override
  long expectedFirstPart() {
    return 11L;
  }

  @Override
  long expectedSecondPart() {
    return 31L;
  }
}
