/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Lab02.Nonogram;


import org.junit.Test;
import static org.junit.Assert.*;

public class Lab02Test {
    @Test 
    public void test01() {
        int[][] columns = {{0,1}, {0,1}, {0,1}, {0,1}, {0,1}}; 
        int[][] rows = {{0,5}};
        boolean[][] result = {{true,true,true,true,true}, {}};
        assertEquals(result, solveNonogram(columns, rows));
    }
    
    
}
