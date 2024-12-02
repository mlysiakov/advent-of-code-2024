package com.lysiakov.advent;

import java.util.Scanner;

public class InputReader {

  public static Scanner read(String inputFilePath) {
    var inStream = InputReader.class.getClassLoader().getResourceAsStream(inputFilePath);
    return new Scanner(inStream);
  }
}
