package com.lysiakov.advent.y2024;

import com.lysiakov.advent.util.Input;
import com.lysiakov.advent.util.Puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day7 implements Puzzle {

  public static void main(String[] args) {
    new Day7().run();
  }

  @Override
  public Long part1() {
    var inputs = parseInput();
    long result = 0L;
    for (var input : inputs) {
      if (dfs(1, input.nums(), input.nums()[0], input.sum())) {
        result += input.sum();
      }
    }
    return result;
  }

  private boolean dfs(int index, long nums[], long current, long target) {
    if (current == target && index == nums.length) {
      return true;
    }
    if (index >= nums.length) {
      return false;
    }
    return dfs(index + 1, nums, current + nums[index], target) || dfs(index + 1, nums, current * nums[index], target);
  }

  @Override
  public Long part2() { // 472290821152397
    var inputs = parseInput();
    long result = 0L;
    for (var input : inputs) {
      if (dfs2(1, input.nums(), input.nums()[0], input.sum())) {
        result += input.sum();
      }
    }
    return result;
  }

  private boolean dfs2(int index, long nums[], long current, long target) {
    if (current == target && index == nums.length) {
      return true;
    }
    if (index >= nums.length) {
      return false;
    }
    boolean res = dfs2(index + 1, nums, current + nums[index], target);
    if (!res) {
      res = dfs2(index + 1, nums, current * nums[index], target);
    }
    if (!res) {
      var merge = Long.toString(current) + nums[index];
      res =  dfs2(index + 1, nums, Long.parseLong(merge), target);
    }
    return res;
  }

  private record PuzzleInput(long sum, long[] nums) { }

  private List<PuzzleInput> parseInput() {
    var puzzleInputs = new ArrayList<PuzzleInput>();

    try (var scanner = Input.read(2024, 7)) {
      while (scanner.hasNext()) {
        var line = scanner.nextLine();
        var split = line.split(":");
        var sum = Long.parseLong(split[0]);
        var nums = Arrays.stream(split[1].trim().split(" ")).mapToLong(Long::parseLong).toArray();
        puzzleInputs.add(new PuzzleInput(sum, nums));
      }
    }
    return puzzleInputs;
  }
}
