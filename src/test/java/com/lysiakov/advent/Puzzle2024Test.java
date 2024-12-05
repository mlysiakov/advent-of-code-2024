package com.lysiakov.advent;

import com.lysiakov.advent.util.Puzzle;
import com.lysiakov.advent.y2024.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Puzzle2024Test {

  private Puzzle puzzle;

  @ParameterizedTest
  @MethodSource("test")
  void test(Puzzle puzzle, long partOneExpected, long partTwoExpected) {
    assertEquals(partOneExpected, puzzle.part1());
    assertEquals(partTwoExpected, puzzle.part2());
  }

  private static Stream<Arguments> test() {
    return Stream.of(
        Arguments.of(new Day1(), 11, 31),
        Arguments.of(new Day2(), 2, 4),
        Arguments.of(new Day3(), 161, 48),
        Arguments.of(new Day4(), 18, 9),
        Arguments.of(new Day5(), 143, 123)
    );
  }

}
