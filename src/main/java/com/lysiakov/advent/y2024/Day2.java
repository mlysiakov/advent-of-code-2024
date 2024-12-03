package com.lysiakov.advent.y2024;

import com.lysiakov.advent.util.Input;
import com.lysiakov.advent.util.Puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 implements Puzzle {

  public static void main(String[] args) {
    new Day2().run();
  }

  @Override
  public Long part1() {
    var reports = parse();
    return reports.stream().filter(report -> validReport(report, 0)).count();
  }

  @Override
  public Long part2() {
    var reports = parse();
    return reports.stream().filter(report -> validReport(report, 1)).count();
  }

  private boolean validReport(List<Integer> report, int failureLevel) {
    if (report.size() <= 1) {
      return false;
    }
    var validator = new ReportValidator(report, failureLevel);
    var isValid = validator.isValid();
    // try clockwise to tolerate when first number is invalid
    if (!isValid) {
      Collections.reverse(report);
      validator = new ReportValidator(report, failureLevel);
      isValid = validator.isValid();
    }
    return isValid;
  }

  private List<List<Integer>> parse() {
    var result = new ArrayList<List<Integer>>();
    try (var scanner = Input.read(2024, 2)) {
      while (scanner.hasNextLine()) {
        var line = scanner.nextLine();
        var numbers = Arrays.stream(line.split("\\s+"))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
        result.add(numbers);
      }
    }
    return result;
  }

  private enum Order {
    INCREASE, DECREASE
  }

  private class ReportValidator {

    private final int MIN_INCREASE = 1;
    private final int MAX_INCREASE = 3;

    private int failureTolerationLevel;
    private List<Integer> report;
    private Order order;

    private Boolean isValid;
    private Integer prev;

    public ReportValidator(List<Integer> report, int failureToleration) {
      if (failureToleration < 0) {
        throw new IllegalArgumentException("Failure toleration level must be non-negative");
      }
      this.report = report;
      this.failureTolerationLevel = failureToleration;
      this.order = report.get(1) > report.get(0) ? Order.INCREASE : Order.DECREASE;
    }

    public boolean isValid() {
      if (this.isValid != null) {
        return this.isValid;
      }
      var isValid = true;
      for (var number : report) {
        if (!this.add(number)) {
          isValid = false;
          break;
        }
      }
      this.isValid = isValid;
      return isValid;
    }

    private boolean add(int number) {
      if (prev == null) {
        prev = number;
        return true;
      }
      var order = number > prev ? Order.INCREASE : Order.DECREASE;
      if (order != this.order) {
        return canTolerateFailure();
      }
      var diff = Math.abs(number - prev);
      if (diff < MIN_INCREASE || diff > MAX_INCREASE) {
        return canTolerateFailure();
      }
      prev = number;
      return true;
    }

    private boolean canTolerateFailure() {
      if (failureTolerationLevel == 0) {
        return false;
      }
      failureTolerationLevel--;
      return true;
    }

  }


}
