package pl.sudokuboard;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BacktrackingSudokuSolverTest {

    public BacktrackingSudokuSolverTest() {}

    @Test
    /**
     * Testuje poprawność funkcji CleanRow
     */
    public void CleanRowTest() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException, OutOfRangeException {
        int[][] tab = { {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9}
        };
        BacktrackingSudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard b = new SudokuBoard(s);
        b.setBoard(tab);

        boolean rezult = true;

        Method method = BacktrackingSudokuSolver.class.getDeclaredMethod("cleanRow", SudokuBoard.class, int.class);
        method.setAccessible(true);
        method.invoke(s, b,2);


        int[] arr = b.getRow(2);
        for (int i : arr)
            if(arr[i] != 0) {
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
    public void SolveRowTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, OutOfRangeException {
        int[][] tab = { {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0}
        };

        BacktrackingSudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(s);
        board.setBoard(tab);
        boolean rezult = true;

        Method method = BacktrackingSudokuSolver.class.getDeclaredMethod("solveRow", SudokuBoard.class, int.class);
        method.setAccessible(true);
        rezult = (boolean) method.invoke(s, board,2);
        assertTrue(rezult);

        int[] arr = board.getRow(2);
        for (int i : board.getRow(2))
            if(arr[i-1] == 0)
                rezult = false;
        assertTrue(rezult);
    }

    @Test
    public void SolveTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, OutOfRangeException {
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

    @Test
    /**
     * Testuje metodę FillBoard.
     */
    public void fillBoard() throws OutOfRangeException {
        BacktrackingSudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard b = new SudokuBoard(s);
        b.solveGame();

        SudokuBoard c = new SudokuBoard(s);
        c.solveGame();

        int[] tab;
        int[] tab2;

        for (int i = 0; i < b.getBoardSize(); i++) {
            tab = b.getRow(0);
            tab2 = c.getRow(0);
//            System.out.println("i: "+i+" Tab: "+Arrays.toString(tab));
//            System.out.println("i: "+i+" Tab2: "+Arrays.toString(tab2));
            assertTrue(tab != tab2);
        }
    }
    @Test
    /**
     * Testuje metodę FillBoard pod względem poprawności.
     */
    public void fillBoardCorrectnessTest() throws OutOfRangeException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BacktrackingSudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard b = new SudokuBoard(s);
        b.solveGame();
        int[] tab;

        for (int i = 0; i < b.getBoardSize(); i++) {
            tab = b.getCol(i);
            assertTrue(!checkForDuplicates(tab));
            tab = b.getRow(i);
            assertTrue(!checkForDuplicates(tab));
        }


        for (int i = 0; i < b.getBoardSize(); i+=3) {
            for (int k = 0; k < b.getBoardSize(); k+=3) {
                tab = b.getBox(i,k);
//                    System.out.println("i: "+i+" k: "+k+" Tab: "+Arrays.toString(b.getBox(i,k)));
                assertTrue(!checkForDuplicates(tab));
            }
        }



    }

    /**
     * Generyczna metoda do sprawdzenia duplikatów w tablicy
     * @param array Tablica do sprawdzenia
     * @return False jeśli nie ma duplikatów, true jak ma duplikaty
     * @param <T> Szablon
     */
    private static <T> boolean checkForDuplicates(T... array)
    {
        // Pusty zbioru
        Set<T> set = new HashSet<T>();
        for (T e: array)
        {
            if (set.contains(e)) {      // true jeśli zawiera duplikaty
                return true;
            }
            if (e != null) {            //wrzuć element do zbioru
                set.add(e);
            }
        }
        return false;                   //false jak nie znalazł duplikatów
    }


}
