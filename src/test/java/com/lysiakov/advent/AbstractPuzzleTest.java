package com.lysiakov.advent;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.lysiakov.advent.util.Puzzle;
import org.junit.jupiter.api.Test;

abstract class AbstractPuzzleTest {

  private final Puzzle puzzle;

  protected AbstractPuzzleTest(Puzzle puzzle) {
    this.puzzle = puzzle;
  }

  abstract long expectedFirstPart();

  abstract long expectedSecondPart();

  @Test
  final void firstPartTest() {
    var answer = this.puzzle.part1();
    assertEquals(expectedFirstPart(), answer);
  }

  @Test
  final void secondPartTest() {
    var answer = this.puzzle.part2();
    assertEquals(expectedSecondPart(), answer);
  }
}
