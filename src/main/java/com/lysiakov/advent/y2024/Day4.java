package com.lysiakov.advent.y2024;

import com.lysiakov.advent.util.Input;
import com.lysiakov.advent.util.Puzzle;

public class Day4 implements Puzzle {

  public static void main(String[] args) {
    new Day4().run();
  }

  @Override
  public long part1() {
    var matrix = parse();
    long answer = 0L;
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (matrix[i][j] == 'X') {
          answer += dfs(i, j, matrix);
        }
      }
    }
    return answer;
  }

  private char[] target = {'X', 'M', 'A', 'S'};

  private int[] directionX = {0, 0, 1, -1, 1, 1, -1, -1};
  private int[] directionY = {1, -1, 0, 0, -1, 1, 1, -1};

  private int dfs(int x, int y, char[][] matrix) {
    int counter = 0;
    for (int i = 0; i < directionX.length; i++) {
      int newX = x;
      int newY = y;
      int index = 0;
      while (index < target.length) {
        if (newX < 0 || newX >= matrix.length || newY < 0 || newY >= matrix[0].length) {
          break;
        }
        if (matrix[newX][newY] != target[index]) {
          break;
        }
        newX += directionX[i];
        newY += directionY[i];
        index++;
      }
      if (index == target.length) {
        counter++;
      }
    }
    return counter;
  }

  @Override
  public long part2() {
    var matrix = parse();
    long answer = 0L;
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (matrix[i][j] == 'A') {
          if (check(i, j, matrix)) {
            answer++;
          }
        }
      }
    }
    return answer;
  }

  private boolean check(int x, int y, char[][] matrix) {
    boolean leftMatch = false;
    if (saveRead(x - 1, y - 1, matrix) == 'M' && saveRead(x + 1, y + 1, matrix) == 'S') {
      leftMatch = true;
    }
    if (saveRead(x - 1, y - 1, matrix) == 'S' && saveRead(x + 1, y + 1, matrix) == 'M') {
      leftMatch = true;
    }
    boolean rightMatch = false;
    if (saveRead(x - 1, y + 1, matrix) == 'M' && saveRead(x + 1, y - 1, matrix) == 'S') {
      rightMatch = true;
    }
    if (saveRead(x - 1, y + 1, matrix) == 'S' && saveRead(x + 1, y - 1, matrix) == 'M') {
      rightMatch = true;
    }
    return rightMatch && leftMatch;
  }

  private char saveRead(int x, int y, char[][] matrix) {
    if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) {
      return ' ';
    }
    return matrix[x][y];
  }

  private char[][] parse() {
    return Input.asString(2024, 4).lines().map(String::toCharArray).toArray(char[][]::new);
  }
}
