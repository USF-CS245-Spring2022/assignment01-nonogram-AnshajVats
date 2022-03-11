package Lab02.Nonogram;

//@name Anshaj Vats
//3/10/2022.
// Version 5: added comments.
//CS245 Lab02: Nonogram

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lab02 {
    int[][] rows;
    int[][] columns;
    boolean[][] board;
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

    /**
     * @param row       row which we are currently at.
     * @param col       col which we are currently at.
     * @param blockSize number of blocks to  remove.
     *                  Removes the block from the given row and col.
     */
    private void removeBlock(int row, int col, int blockSize) {
        for (int i = 0; i < blockSize; i++) {
            board[row][col + i] = false;
        }

    }

    /**
     * @param row   row which we are currently at.
     * @param col   col which we are currently at.
     * @param block the index of the 2D rows[row][block]
     * @return
     * @throws Exception To handle the case if the board is solved and saves time complexity.
     */
    public boolean solve(int row, int col, int block)  {
        System.out.println("solve "+row+" "+col+" "+block);
        if (col>=numCols)
            return false;
        if (block > 1) {
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
            if (solve(row, col, 1)) // row will have at least 1 non zero number.
                return true;
            return false;
        }
        for (int j = col; j+blockSize <= numCols; j++) {

            if (isSafe(row, j, blockSize)) {
                // paint
                System.out.println("painting "+row+" "+j+" "+blockSize);
                for (int i = 0; i < blockSize; i++) {
                    board[row][j + i] = true;
                }
                //if(j+ blockSize +1 >= numCols && block == 0) {
                  //  return false;
                //}
                int nextrow=row;
                int nextcol=col;
                int nextblock=block;
                if (block==1) {
                    nextrow++;
                    nextcol=0;
                    nextblock=0;
                } else {
                    nextcol=j+blockSize+1;
                    nextblock=1;
                }
                if (solve(nextrow, nextcol, nextblock)) {
                    return true;
                }
                //undo paint
                System.out.println("undoing "+row+" "+j+" "+blockSize);
                for (int i = 0; i < blockSize; i++) {
                    board[row][j + i] = false;
                }

            }
        }
        return false;
    }

    /**
     * @param row       row which we are currently at.
     * @param col       col which we are currently at.
     * @param blockSize the number of blocks to check if it is safe or not.
     * @return true if it is safe false otherwise.
     */
    public boolean isSafe(int row, int col, int blockSize) {
        System.out.println("isSafe"+" "+row+" "+col+" " + blockSize);
        if (blockSize + col > numCols) {
            return false;
        }
        for (int i = col; i < col + blockSize; i++) {
            if (!isColumn(row, i)) {
                System.out.println("Print" + i + "row" + row);
                return false;
            }
        }
        return true;

    }

    /**
     * @param rows the block which we check is in this row.
     * @param col  the row to check.
     * @return true if it is safe and false otherwise.
     */
    public boolean isColumn(int rows, int col) {
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int firstValue = columns[col][0];
        int secondValue = columns[col][1];
        if (rows >= numRows) {//this is a issue.
            return false;
        }
        if (col >= numCols) {
            return false;
        }
        List<Boolean> array = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            array.add(board[i][col]);
        }
        int  j = 0;
            while (!array.get(j)) {
                j++;
                if( j >= array.size()) return true;
            }
            while (j < array.size() && array.get(j) && firstValue != 0) {
                count2++;
                j++;
            }
            if(firstValue != 0 && count2 < firstValue) {
                return true;
            }

            if(firstValue != 0 && count2 == firstValue && j  > 0 && j < array.size() &&array.get(j - 1)
                        && j == rows)
                return false;

            while (j < array.size() && !array.get(j)) {
                j++;
            }

            while (j < array.size() && array.get(j)) {
                count4++;
                j++;
            }
            if(secondValue != 0 && count4 < secondValue) {
                int lastTrue = -1;
                if (firstValue == 0) {
                    for (int i = 0; i < numRows; i++) {


                        if (board[i][col]) {
                            lastTrue = i;
                        }
                    }
                    if (lastTrue == (rows - 1))
                        return true;
                    return false;
                }
                else return true;
            }
            while (j < array.size() && array.get(j)) {
                j++;
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


