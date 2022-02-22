package Lab02.Nonogram;

//@name
//@date/version
//CS245 Lab02: Nonogram

import java.util.Arrays;

public class Lab02{
  int[][] rows;
  int[][] columns;
  boolean[][] board;
  int numToColor;
  //nonogram method solves the puzzle with the given two parameters
  //@param double array of integers that represent the columns
  //@param double array of integers that represent the rows
  //@returns double boolean array of solution
  public static boolean[][] solveNonogram(int[][] columns, int[][] rows) {
    Lab02 board = new Lab02();
    board.columns = columns;
    board.rows = rows;
    board.board = new boolean[rows.length][columns.length];
     board.solve(0, 0, 0 );
     return board.solve(0, 0, 0 )? null:board.board;
  }
  
  
  public boolean solve(int row, int col, int block) {
    if(row == rows.length){
      return true;
    }
   if( block < 2 && rows[row][block] == 0){
     solve(row,col,block + 1);
   }else if(block < 2) {
     numToColor = rows[row][block];
     for (int i = 0; i < columns.length; i++) {
       if (isSafe(row, col, i)) {
         if (solve(row + 1, col, 0)) {
           return true;
         }
       }
     }
   }
  return false;
  }
  
  private boolean isSafe(int row, int col, int index) {
    //need to do two things check if the numToColor is satisfied.
    //if(isSafeColumn(index)){
      //while(board[row][col] )
    //}
    // calls isSafecolumn to check the column.
    return true;
  }
  
  public static void main(String[] args){
  }
}
