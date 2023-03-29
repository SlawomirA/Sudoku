package pl.sudokuboard;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class SudokuBacktrackingSolverTest {

    public SudokuBacktrackingSolverTest() {
    }

    @Test
    /**
     * Testuje poprawność funkcji CleanRow
     */
    public void CleanRowTest() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException, OutOfRangeException {
        int[][] tab = {{1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9}
        };

        SudokuBoard board = new SudokuBoard(tab);
        SudokuBacktrackingSolver s = new SudokuBacktrackingSolver(board);
        boolean rezult = true;

        Method method = SudokuBacktrackingSolver.class.getDeclaredMethod("cleanRow", int.class);
        method.setAccessible(true);
        method.invoke(s, 2);


        Field field = SudokuBacktrackingSolver.class.getDeclaredField("sudokuBoard");
        field.setAccessible(true);
        SudokuBoard b = (SudokuBoard) field.get(s);
        int[] arr = b.getRow(2);
        for (int i : arr)
            if (arr[i] != 0) {
                rezult = false;
                break;
            }


        assertTrue(rezult);
    }

    @Test
    /**
     * Testuje funkcję generateNumbers
     */
    public void GenerateNumbersTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Sprawdzanie czy lista nie jest pusta
        boolean rezult = true;

        List<Integer> l, k, j;
        SudokuBoard board = new SudokuBoard();
        SudokuBacktrackingSolver s = new SudokuBacktrackingSolver(board);

        Method method = SudokuBacktrackingSolver.class.getDeclaredMethod("generateNumbers");
        method.setAccessible(true);
        l = (List<Integer>) method.invoke(s);

        for (int i : l)
            if (l.get(i - 1) == 0)
                rezult = false;

        assertTrue(rezult);

        //Sprawdzenie czy listy są losowane różne
        k = (List<Integer>) method.invoke(s);
        j = (List<Integer>) method.invoke(s);

        boolean rozne = false;
        for (int i : k)
            if (k.get(i - 1) != j.get(i - 1)) {
                rozne = true;
                //System.out.println(k.get(i-1) + "  " +  j.get(i-1)); //Wypisywanie tych tablic
            }
        if (!rozne)
            rezult = false;

        assertTrue(rezult);
    }

    @Test
    /**
     * Testuje poprawność funkcji correct
     */
    public void CorrectTest() throws NoSuchMethodException, OutOfRangeException, InvocationTargetException, IllegalAccessException {
        //Test polegający na sprawdzeniu, że correct na wypełnionym wierszu sudoku da fałsz.
        int[] tab = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        SudokuBoard board = new SudokuBoard();
        SudokuBacktrackingSolver s = new SudokuBacktrackingSolver(board);
        boolean rezult = true;


        Method method = SudokuBacktrackingSolver.class.getDeclaredMethod("correct", int[].class, int.class);
        method.setAccessible(true);
        rezult = (boolean) method.invoke(s, tab, 9);
        assertFalse(rezult);

        //Test sprawdzający, czy próba dodania nie istniejącej wartości do tablicy zakończy się zwróceniem True.
        rezult = (boolean) method.invoke(s, tab, 0);
        assertTrue(rezult);

        //
    }


    @Test
    /**
     * Testuje poprawność funkcji correctNumber
     */
    public void CorrectNumberTest() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        //Test na wiersz/kolumnę/box który powiniene zwracać fałsz za każdym razem, więc ostatecznie fałsz
        int[][] tab = {{1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9}
        };

        SudokuBoard board = new SudokuBoard(tab);
        SudokuBacktrackingSolver s = new SudokuBacktrackingSolver(board);
        boolean rezult = true;

        Method method = SudokuBacktrackingSolver.class.getDeclaredMethod("correctNumber", int.class, int.class, int.class);
        method.setAccessible(true);
        rezult = (boolean) method.invoke(s, 0, 0, 1);

        assertFalse(rezult);
    }


    @Test
    /**
     * Testuje funkcję solve row
     */
    public void SolveRowTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, OutOfRangeException {
        int[][] tab = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        SudokuBoard board = new SudokuBoard(tab);
        SudokuBacktrackingSolver s = new SudokuBacktrackingSolver(board);
        boolean rezult = true;

        Method method = SudokuBacktrackingSolver.class.getDeclaredMethod("solveRow", int.class);
        method.setAccessible(true);
        rezult = (boolean) method.invoke(s, 2);
        assertTrue(rezult);

        int[] arr = board.getRow(2);
        for (int i : board.getRow(2))
            if (arr[i - 1] == 0)
                rezult = false;
        assertTrue(rezult);
    }

    @Test
    public void SolveTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, OutOfRangeException {
        //Test czy działa samo wypełnianie tablicy
        SudokuBoard board = new SudokuBoard();
        SudokuBacktrackingSolver s = new SudokuBacktrackingSolver(board);
        boolean rezult = true;

        Method method = SudokuBacktrackingSolver.class.getDeclaredMethod("solve");
        method.setAccessible(true);
        method.invoke(s);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i, j) == 0)
                    rezult = false;
                //System.out.print(board.get(i,j));
            }
            //System.out.println(" ");
        }
        assertTrue(rezult);

        SudokuBoard board2 = new SudokuBoard();
        SudokuBacktrackingSolver s2 = new SudokuBacktrackingSolver(board2);
        Method method2 = SudokuBacktrackingSolver.class.getDeclaredMethod("solve");
        method.setAccessible(true);
        method.invoke(s2);

        //Test że różnie wypełnione
        boolean different = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i, j) != board2.get(i, j)) {
                    different = true;
                    //System.out.print(board.get(i,j) + " " + board2.get(i,j) + "\n");
                }
            }
            //System.out.println("");
        }
        if (!different)
            rezult = false;
        assertTrue(rezult);
    }
}
