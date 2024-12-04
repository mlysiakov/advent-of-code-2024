package com.lysiakov.advent.y2024;

import com.lysiakov.advent.util.Input;
import com.lysiakov.advent.util.Pair;
import com.lysiakov.advent.util.Puzzle;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day3 implements Puzzle {

  private static final Pattern MUL_PATTERN = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
  private static final Pattern DO_PATTERN = Pattern.compile("do\\(\\)|don't\\(\\)");

  public static void main(String[] args) {
    new Day3().run();
  }

  @Override
  public Long part1() {
    var string = Input.asString(2024, 3);
    return MUL_PATTERN.matcher(string).results()
        .map(MatchResult::group)
        .map(s -> s.substring(s.indexOf("(") + 1, s.length() - 1))
        .map(s -> s.split(","))
        .map(arr -> Stream.of(arr).mapToInt(Integer::parseInt).toArray())
        .mapToLong(arr -> arr[0] * arr[1])
        .sum();
  }

  @Override
  public Long part2() {
    var string = Input.asString(2024, 3);
    List<Pair<Integer, String>> mulPairs = MUL_PATTERN.matcher(string).results()
        .map(matcher -> Pair.of(matcher.start(), matcher.group()))
        .toList();

    List<Pair<Integer, Boolean>> instructionPairs = DO_PATTERN.matcher(string).results()
        .map(matcher -> Pair.of(matcher.start(), isDoOrDont(matcher.group())))
        .toList();

    long result = 0L;
    for (Pair<Integer, String> mul : mulPairs) {
      if (instructionBinarySearch(mul.left(), instructionPairs)) {
        String[] arr = mul.right().substring(mul.right().indexOf("(") + 1, mul.right().length() - 1).split(",");
        result += Integer.parseInt(arr[0]) * Integer.parseInt(arr[1]);
      }
    }
    return result;
  }


  private boolean instructionBinarySearch(int index, List<Pair<Integer, Boolean>> instructionPairs) {
    int left = 0;
    int right = instructionPairs.size() - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (instructionPairs.get(mid).left() == index) {
        return instructionPairs.get(mid).right();
      }
      if (instructionPairs.get(mid).left() < index) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return right == -1 || instructionPairs.get(right).right();
  }

  // return true for do and false for don`t
  private boolean isDoOrDont(String match) {
    return match.equals("do()");
  }


}
