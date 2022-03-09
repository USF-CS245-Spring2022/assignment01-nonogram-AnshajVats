package Lab02.Nonogram;

//@name
//@date/version
//CS245 Lab02: Nonogram

import java.util.Arrays;

public class Lab02 {
  int[][] rows;
  int[][] columns;
  boolean[][] board;
  boolean isFound = false;
  int numRows;
  int numCols;

  //nonogram method solves the puzzle with the given two parameters
  //@param double array of integers that represent the columns
  //@param double array of integers that represent the rows
  //@returns double boolean array of solution
  public static boolean[][] solveNonogram(int[][] columns, int[][] rows) {
    Lab02 board = new Lab02();
    board.columns = columns;
    board.rows = rows;
    board.board = new boolean[rows.length][columns.length];
    board.numRows = rows.length;
    board.numCols = columns.length;
    board.solve(0, 0, 0);
    return board.board;

  }

  private void removeBlock(int row, int col, int blockSize) {
    for (int i = board.length - 1; i >= 0; i--) {
      if (board[i][col]) {
        for (int j = i; j >= 0; j--) {
          if (!board[j][col]) {
            return;
          } else {
            board[j][col] = false;
          }
        }
      }

    }

  }

  public boolean solve(int row, int col, int block) {
    if (col >= numCols) {
      row++;
      col = 0;
      block = 0;
    }
    if (row >= numRows) {
      return true;
    }
    int blockSize = rows[row][block];
    if (blockSize == 0) {
      if (solve(row, col, 1)) {
        return true;
      }
    }
    if (isSafe(row, col, blockSize)) {
      for (int i = 0; i < blockSize; i++) {
        board[row][col + i] = true;
      }
      if (solve(row, col + blockSize + 1, 1)) {
        return true;
      }
    }
    removeBlock(row, col, blockSize);
    return false;
  }

   /*if( block < 2 && rows[row][block] == 0){
     solve(row,col,block + 1);
   }else if(block < 2) {
     int blockSize = rows[row][block];
       if (isSafe(row, col, blockSize)) {
         //insertBlock(row,col,blockSize);
         if (solve(row + 1, col, 0)) {
           return true;
         }
       }
     }*/


  public boolean isSafe(int row, int col, int blockSize) {
    if (blockSize + row > numCols) {
      return false;
    }

    for (int i = col; i < blockSize; i++) {
      if (!isColumn(row, i)) {
        return false;
      }
    }
    return isRow(row, col);

  }

  public boolean isRow(int rows, int col) {
    int count1 = 0;
    int count2 = 0;
    int count3 = 0;
    int count4 = 0;
    int firstValue = this.rows[rows][0];
    int secondValue = this.rows[rows][1];
    int i = 0;
    while (!board[rows][i]) {
      count1++;
      i++;

      if (i >= numCols) return true;
    }
    while (board[rows][i]) {
      count2++;
      i++;
    }
    if (firstValue != 0 && count2 < firstValue) {
      return true;
    }

    while (!board[rows][i] && i < numCols) {
      count3++;
      i++;
    }

    while (board[rows][i]) {
      count4++;
      i++;
    }
    if (secondValue != 0 && count4 <= secondValue) {
      return true;
    }
    return count1 == numCols ? true : false;


  }

  public boolean isColumn(int rows, int col) {
    int count1 = 0;
    int count2 = 0;
    int count3 = 0;
    int count4 = 0;
    int firstValue = columns[rows][0];
    int secondValue = columns[rows][1];
    if (rows > numRows) {//this is a issue.
      return false;
    }
    int i = 0;
    while (!board[i][col]) {
      count1++;
      i++;
      if (i >= numRows) return true;
    }
    while (board[i][col] && i < numRows) {
      count2++;
      i++;
    }
    if (firstValue != 0 && count2 < firstValue) {
      return true;
    }

    while (!board[i][col] && i < numRows) {
      count3++;
      i++;
    }

    while (board[i][col]) {
      count4++;
      i++;
    }
    if (secondValue != 0 && count4 < secondValue) {
      return true;
    }
    return false;
  }


  public static void main() {
    int[][] columns = {{0, 1}, {0, 1}, {0, 1}, {0, 1}, {0, 1}};
    int[][] rows = {{0, 0}, {0, 0}, {0, 0}};
    boolean[][] result = {{true, true, true, true, true}, {}};
    boolean[][] solve = solveNonogram(columns, rows);
    for (boolean[] booleans : solve) {
      System.out.println(Arrays.toString(booleans));
    }
  }
}


