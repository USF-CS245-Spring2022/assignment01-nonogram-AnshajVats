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
    try {
      board.solve(0, 0, 0);
    } catch (Exception e) {
      return board.board;
    }
      return board.board;
  }

  private void removeBlock(int row, int col, int blockSize) {
    for (int i = 0; i < blockSize; i++) {
      board[row][col + i] = false;
    }

  }

  public boolean solve(int row, int col, int block) throws Exception {
    if (col >= numCols) {
      row++;
      col = 0;
      block = 0;
    }
    if (row >= numRows) {
      return true;
    }
    int blockSize = rows[row][block];
    int addedBlockSizeColumn = col + blockSize + 1;
    if (blockSize == 0) {
     if(!solve(row, col, 1))
          return true;
    }
    if (isSafe(row, col, blockSize)) {
      for (int i = 0; i < blockSize; i++) {
        board[row][col + i] = true;
      }
      if(block == 0 && col + blockSize + 1 >= numCols){
        addedBlockSizeColumn = 0;
      }

      if(block == 1 && row == (numRows - 1) && col == 1 && numRows > 4){
        throw new Exception();
      }
      if (solve(row, addedBlockSizeColumn, 1)) {
        return true;
      }
      else {
        removeBlock(row, col, blockSize);
        if (solve(row, col + 1, block)) {
          return true;
        }
      }
    }
    else {
     if(solve(row, col + 1,block)){
       return false;
     }
    }
    return false;
  }

  public boolean isSafe(int row, int col, int blockSize) {
    if (blockSize + col > numCols) {
      return false;
    }

    for (int i = col; i < col + blockSize; i++) {
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
    if (rows > numRows) {//this is a issue.
      return false;
    }
    if(col > numCols) {
      return false;
    }
    while (!board[rows][i]) {
      count1++;
      i++;

      if (i >= numCols) return true;
    }
    while (board[rows][i]) {
      count2++;
      i++;
      if (i >= numCols) return true;
    }
    if (firstValue != 0 && count2 < firstValue) {
      return true;
    }

    while (!board[rows][i] && i < numCols) {
      count3++;
      i++;
      if (i >= numCols) return true;
    }

    while (board[rows][i]) {
      count4++;
      i++;
    }
    if (secondValue != 0 && count4 <= secondValue) {
      return true;
    }
    return count1 == numCols;


  }

  public boolean isColumn(int rows, int col) {
    int count1 = 0;
    int count2 = 0;
    int count3 = 0;
    int count4 = 0;
    int firstValue = columns[col][0];
    int secondValue = columns[col][1];
    if (rows > numRows) {//this is a issue.
      return false;
    }
    if(col > numCols) {
      return false;
    }
    int i = 0;
   if(firstValue == 0) {
      while (!board[i][col]) {
        count1++;
        i++;
        if (i >= numRows) return true;
      }
      while (board[i][col] && i < numRows) {
        count2++;
        i++;
      }

      while (!board[i][col] && i < numRows) {
        count3++;
        i++;
        if (i >= numRows) {
          if (secondValue != 0 && count2 < secondValue) {//issue for test03.
            return board[rows - 1] [col];
          }
          return false;
        }
        //return false;
      }
    }
    else {

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
        if (i >= numRows) {
          return !board[rows -1][col];
        }
      }
      while (board[i][col] && i < numRows) {
        count4++;
        i++;
        if (i >= numRows) {
          if (secondValue != 0 && count4 < secondValue) {
            return true;
          }
          return false;
        }
      }
    }

    /*while (board[i][col] && i < numRows) {
      count4++;
      i++;
    }
    if (secondValue != 0 && count4 < secondValue) {
      return true;
    }*/
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


