package com.lysiakov.advent.util;

import java.util.Scanner;

public class Input {

  private final static String FILE_NAME = "input.txt";

  public static Scanner read(int year, int day) {
    var inputFilePath = String.format("%d/%d/%s", year, day, FILE_NAME);
    var inStream = Input.class.getClassLoader().getResourceAsStream(inputFilePath);
    return new Scanner(inStream);
  }

  public static String asString(int year, int day) {
    try (var scanner = read(year, day)) {
      return scanner.useDelimiter("\\A").next();
    }
  }
}
