package com.lysiakov.advent.y2024;

import com.lysiakov.advent.util.Input;
import com.lysiakov.advent.util.Puzzle;
import java.util.ArrayList;
import java.util.List;

public class Day14 implements Puzzle {

  public static void main(String[] args) {
    new Day14(103, 101).run();
  }

  private final int rowSize;
  private final int columnSize;

  public Day14(int rowSize, int columnSize) {
    this.rowSize = rowSize;
    this.columnSize = columnSize;
  }

  @Override
  public long part1() {
    var robots = parse();
    for (var robot : robots) {
      robot.move(100);
    }
    long midR = rowSize / 2;
    long midC = columnSize / 2;
    long left_top = 0;
    long right_top = 0;
    long left_bottom = 0;
    long right_bottom = 0;
    for (var robot : robots) {
      var x = robot.getPositionX();
      var y = robot.getPositionY();
      if (x < midR && y < midC) {
        left_top++;
      } else if (x < midR && y > midC) {
        right_top++;
      } else if (x > midR && y < midC) {
        left_bottom++;
      } else if (x > midR && y > midC) {
        right_bottom++;
      }
    }
    return left_top * left_bottom * right_bottom * right_top;
  }

  @Override
  public long part2() {
    return 0;
  }


  private int safeRowPosition(int position) {
    int newPosition = position % rowSize;

    // Handle wrapping for negative indices
    if (newPosition < 0) {
      newPosition += rowSize;
    }

    return newPosition;
  }

  private int safeColumnPosition(int position) {
    int newPosition = position % columnSize;

    // Handle wrapping for negative indices
    if (newPosition < 0) {
      newPosition += columnSize;
    }

    return newPosition;
  }


  private class Robot {

    private int positionX;
    private int positionY;

    private int velocityX;
    private int velocityY;

    private Robot(int p1, int p2, int v1, int v2) {
      this.positionX = p1;
      this.positionY = p2;

      this.velocityX = v1;
      this.velocityY = v2;
    }

    private void move(int times) {
      for (int i = 0; i < times; i++) {
        this.positionX = safeRowPosition(this.positionX + this.velocityX);
        this.positionY = safeColumnPosition(this.positionY + this.velocityY);
      }
    }

    public int getPositionX() {
      return positionX;
    }

    public int getPositionY() {
      return positionY;
    }
  }

  private List<Robot> parse() {
    var robots = new ArrayList<Robot>();
    try (var scanner = Input.read(2024, 14)) {
      while (scanner.hasNextLine()) {
        var line = scanner.nextLine();
        // Remove the 'p=' and 'v=' prefixes and split the parts
        String[] parts = line.replace("p=", "").replace("v=", "").split("\\s+");

        // Extract the `p` pair
        String[] pCoordinates = parts[0].split(",");
        int p1 = Integer.parseInt(pCoordinates[1]);
        int p2 = Integer.parseInt(pCoordinates[0]);

        // Extract the `v` pair
        String[] vCoordinates = parts[1].split(",");
        int v1 = Integer.parseInt(vCoordinates[1]);
        int v2 = Integer.parseInt(vCoordinates[0]);

        robots.add(new Robot(p1, p2, v1, v2));
      }

    }
    return robots;
  }

}
