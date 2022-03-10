We build the board based on the length of rows and columns. We make the board an instance variable.
We also make the rows and the columns an instance variable. Then we call to solve method with row = 0
col = 0 , block - 0. In solve method we check if row is equal to numRows(rows.length) and col is
more than 1 and block is 1 then the board is solved we have reached the end. return true. else we check if block
is zero or not. If it is then make block 1 for same row and col. After that get the blockSize we want to
put in the array. We call isSafe method. In isSafe then call isColumn if isColumn returns true and isRow is
true then isSafe is true. False otherwise. If it is isSafe then change the blockSize from row and col
to true. Then checks if it is the last row and block is 1. If true then call on the next block.
if it false then remove the block and call solve again with the increased row.
