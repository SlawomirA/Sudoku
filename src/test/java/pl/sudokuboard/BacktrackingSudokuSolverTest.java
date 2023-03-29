package pl.sudokuboard;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class BacktrackingSudokuSolverTest {

    public BacktrackingSudokuSolverTest() {}

    @Test
    /**
     * Testuje poprawność funkcji CleanRow
     */
    public void cleanRowTest() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException, OutOfRangeException {

        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(s);
        board.solveGame();
        Method method = BacktrackingSudokuSolver.class.getDeclaredMethod("cleanRow", SudokuBoard.class, int.class);
        method.setAccessible(true);
        boolean rezult=true;
        for (int i = 0; i < 9; i++) {
            method.invoke(s, board,i);
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(board.get(i,j)!=0)
                    rezult = false;
            }
        }

        assertTrue(rezult);
    }

    @Test
    /**
     * Testuje funkcję generateNumbers
     */
    public void generateNumbersTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Sprawdzanie czy lista nie jest pusta
        boolean rezult = true;

        List<Integer> l,k,j;
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        BacktrackingSudokuSolver s = new BacktrackingSudokuSolver();

        Method method = BacktrackingSudokuSolver.class.getDeclaredMethod("generateNumbers");
        method.setAccessible(true);
        l = (List<Integer>) method.invoke(s);

        for (int i : l)
            if(l.get(i-1) == 0)
                rezult = false;

        assertTrue(rezult);

        //Sprawdzenie czy listy są losowane różne
        k = (List<Integer>) method.invoke(s);
        j = (List<Integer>) method.invoke(s);

        boolean rozne = false;
        for (int i : k)
            if(k.get(i-1) != j.get(i-1)) {
                rozne = true;
                //System.out.println(k.get(i-1) + "  " +  j.get(i-1)); //Wypisywanie tych tablic
            }
        if(!rozne)
            rezult=false;

        assertTrue(rezult);
    }



    @Test
    /**
     * Testuje funkcję solve row
     */
    public void solveRowTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, OutOfRangeException {

        BacktrackingSudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(s);
        boolean rezult = true;

        Method method = BacktrackingSudokuSolver.class.getDeclaredMethod("solveRow", SudokuBoard.class, int.class);
        method.setAccessible(true);
        method.invoke(s, board,0);

        SudokuRow r = board.getRow(0);
        rezult = true;
        for (int i=0;i<9;i++){
            if(r.getFieldValue(i)==0)
            {
                rezult=false;
                break;
            }
        }
        assertTrue(rezult);
    }

    @Test
    public void solveTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, OutOfRangeException {
        //Test czy działa samo wypełnianie tablicy
        BacktrackingSudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(s);
        boolean rezult = true;

        Method method = BacktrackingSudokuSolver.class.getDeclaredMethod("solve", SudokuBoard.class);
        method.setAccessible(true);
        method.invoke(s, board);

        for (int i = 0;i<9;i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i, j) == 0)
                    rezult = false;
                //System.out.print(board.get(i,j));
            }
            //System.out.println(" ");
        }
        assertTrue(rezult);

        BacktrackingSudokuSolver s2 = new BacktrackingSudokuSolver();
        SudokuBoard board2 = new SudokuBoard(s2);
        Method method2 = BacktrackingSudokuSolver.class.getDeclaredMethod("solve", SudokuBoard.class);
        method.setAccessible(true);
        method.invoke(s2, board2);

        //Test że różnie wypełnione
        boolean different = false;
        for (int i = 0;i<9;i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i, j) != board2.get(i, j)) {
                    different = true;
                    //System.out.print(board.get(i,j) + " " + board2.get(i,j) + "\n");
                }
            }
            //System.out.println("");
        }
        if(!different)
            rezult = false;
        assertTrue(rezult);
    }
}
