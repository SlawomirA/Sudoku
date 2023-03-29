package pl.sudokuboard;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SudokuBoardTest {
    public SudokuBoardTest(){

    }

    @Test
    /**
     * Testuje stworzenie obiektu board i sprawdza poprawność jego rozmiarów.
     */
    public void BoardSizeTest() {
        boolean result = false;
        SudokuBoard board = new SudokuBoard();
        if(board.getBoardSize()==9 && board.getBoxSize()==3)
            result = true;
        else {
            System.out.println("Stworzony board nie ma poprawnych rozmiarów");
            result = false;
        }
        assertTrue(result);
    }

    @Test
    /**
     * Testuje metodę Get
     */
    public void BoardGetTest() throws OutOfRangeException {

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

        SudokuBoard board = new SudokuBoard(tab);
        int rezult = board.get(2,2);
        assertEquals(3,rezult);

    }

    @Test
    /**
     * Testuje metodę GetRow
     */
    public void BoardGetRowTest() throws OutOfRangeException {

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

        SudokuBoard board = new SudokuBoard(tab);
        boolean rezult = false;
        int[] arr  = board.getRow(0);
        if(arr[0]==1 & arr[1]==2 & arr[2]==3 & arr[3]==4 & arr[4]==5 & arr[5]==6 & arr[6]==7 & arr[7]==8 & arr[8]==9)
             rezult = true;
        assertTrue(rezult);

    }


    @Test
    /**
     * Testuje metodę GetCol
     */
    public void BoardGetColTest() throws OutOfRangeException {

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

        SudokuBoard board = new SudokuBoard(tab);
        boolean rezult = false;
        int[] arr  = board.getCol(0);
        if(arr[0]==1 & arr[1]==1 & arr[2]==1 & arr[3]==1 & arr[4]==1 & arr[5]==1 & arr[6]==1 & arr[7]==1 & arr[8]==1)
            rezult = true;
        assertTrue(rezult);

    }


    @Test
    /**
     * Testuje metodę GetBox
     */
    public void BoardGetBoxTest() throws OutOfRangeException {

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

        SudokuBoard board = new SudokuBoard(tab);
        int rezult = 0;
        //Sprawdzenie 1 kwadratu
        int[] arr  = board.getBox(0,0);
        if(arr[0]==1 & arr[1]==2 & arr[2]==3 & arr[3]==1 & arr[4]==2 & arr[5]==3 & arr[6]==1 & arr[7]==2 & arr[8]==3)
            rezult++;

        //Sprawdzenie 2 kwadratu
        arr  = board.getBox(3,3);
        if(arr[0]==4 & arr[1]==5 & arr[2]==6 & arr[3]==4 & arr[4]==5 & arr[5]==6 & arr[6]==4 & arr[7]==5 & arr[8]==6)
            rezult++;

        //Sprawdzenie 2 kwadratu jak zachowa się algorytm na indeksie na prawej krawędzi
        arr  = board.getBox(5,5);
        if(arr[0]==4 & arr[1]==5 & arr[2]==6 & arr[3]==4 & arr[4]==5 & arr[5]==6 & arr[6]==4 & arr[7]==5 & arr[8]==6)
            rezult++;

        //Sprawdzenie 3 kwadratu
        arr  = board.getBox(6,6);
        if(arr[0]==7 & arr[1]==8 & arr[2]==9 & arr[3]==7 & arr[4]==8 & arr[5]==9 & arr[6]==7 & arr[7]==8 & arr[8]==9)
            rezult++;
        assertEquals(4,rezult);

    }


    @Test
    /**
     * Testuje metodę Set
     */
    public void BoardSetTest() throws OutOfRangeException {

        int[][] tab = { {0,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9}
        };

        SudokuBoard board = new SudokuBoard(tab);
        board.set(0,0, 1);
        int val = board.get(0,0);
        boolean rezult = val == 1 ? true : false;
        assertTrue(rezult);

    }

    @Test
    /**
     * Testuje metodę FillBoard.
     */
    public void fillBoard() throws OutOfRangeException {
        SudokuBoard b = new SudokuBoard();
        b.fillBoard();

        SudokuBoard c = new SudokuBoard();
        c.fillBoard();

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
            SudokuBoard b = new SudokuBoard();
            b.fillBoard();
            SudokuBacktrackingSolver s = new SudokuBacktrackingSolver(b);

            Method method = SudokuBacktrackingSolver.class.getDeclaredMethod("correct", int[].class, int.class);
            method.setAccessible(true);
            boolean rezult;
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
