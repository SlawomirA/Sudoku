package pl.sudokuboard;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SudokuBoardTest {
    public SudokuBoardTest(){
        String[] s = new String[5];
        SudokuBoard.main(s);
    }

    @Test
    /**
     * Testuje stworzenie obiektu board i sprawdza poprawność jego rozmiarów.
     */
    public void BoardSizeTest() {
        boolean result = false;
        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(s);
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

        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(s);
        board.setBoard(tab);
        int rezult = board.get(2,2);
        assertEquals(3,rezult);

        OutOfRangeException thrown = assertThrows(
                OutOfRangeException.class,
                () -> board.get(-1,0),
                "Numer kolumny < 0"
        );
        assertTrue(thrown.getMessage().contains("Wrong"));

        thrown = assertThrows(
                OutOfRangeException.class,
                () -> board.get(15,0),
                "Numer kolumny > max"
        );
        assertTrue(thrown.getMessage().contains("Wrong"));

        thrown = assertThrows(
                OutOfRangeException.class,
                () -> board.get(0,-1),
                "Numer wiersza<0"
        );
        assertTrue(thrown.getMessage().contains("Wrong"));

        thrown = assertThrows(
                OutOfRangeException.class,
                () -> board.get(0,15),
                "Numer wiersza>max"
        );
        assertTrue(thrown.getMessage().contains("Wrong"));

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

        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(s);
        board.setBoard(tab);
        boolean rezult = false;
        int[] arr  = board.getRow(0);
        if(arr[0]==1 & arr[1]==2 & arr[2]==3 & arr[3]==4 & arr[4]==5 & arr[5]==6 & arr[6]==7 & arr[7]==8 & arr[8]==9)
             rezult = true;
        assertTrue(rezult);

        OutOfRangeException thrown = assertThrows(
                OutOfRangeException.class,
                () -> board.getRow(-1),
                "Numer wiersza < 0"
        );
        assertTrue(thrown.getMessage().contains("Wrong"));

        thrown = assertThrows(
                OutOfRangeException.class,
                () -> board.getRow(15),
                "Numer wiersza > max"
        );
        assertTrue(thrown.getMessage().contains("Wrong"));

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

        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(s);
        board.setBoard(tab);
        boolean rezult = false;
        int[] arr  = board.getCol(0);
        if(arr[0]==1 & arr[1]==1 & arr[2]==1 & arr[3]==1 & arr[4]==1 & arr[5]==1 & arr[6]==1 & arr[7]==1 & arr[8]==1)
            rezult = true;
        assertTrue(rezult);


        OutOfRangeException thrown = assertThrows(
                OutOfRangeException.class,
                () -> board.getCol(-1),
                "Numer kolumny < 0"
        );
        assertTrue(thrown.getMessage().contains("Wrong"));

        thrown = assertThrows(
                OutOfRangeException.class,
                () -> board.getCol(15),
                "Numer kolumny > max"
        );
        assertTrue(thrown.getMessage().contains("Wrong"));

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

        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(s);
        board.setBoard(tab);
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


        OutOfRangeException thrown = assertThrows(
                OutOfRangeException.class,
                () -> board.getBox(-1,0),
                "Numer kolumny < 0"
        );
        assertTrue(thrown.getMessage().contains("Wrong"));

        thrown = assertThrows(
                OutOfRangeException.class,
                () -> board.getBox(15,0),
                "Numer kolumny > max"
        );
        assertTrue(thrown.getMessage().contains("Wrong"));

        thrown = assertThrows(
                OutOfRangeException.class,
                () -> board.getBox(0,-1),
                "Numer wiersza < 0"
        );
        assertTrue(thrown.getMessage().contains("Wrong"));

        thrown = assertThrows(
                OutOfRangeException.class,
                () -> board.getBox(0,15),
                "Numer wiersza > max"
        );
        assertTrue(thrown.getMessage().contains("Wrong"));

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

        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(s);
        board.setBoard(tab);
        board.set(0,0, 1);
        int val = board.get(0,0);
        boolean rezult = val == 1 ? true : false;
        assertTrue(rezult);

        OutOfRangeException thrown = assertThrows(
                OutOfRangeException.class,
                () -> board.set(-1,0,-1),
                "Numer kolumny < 0"
        );
        assertTrue(thrown.getMessage().contains("Wrong"));

        thrown = assertThrows(
                OutOfRangeException.class,
                () -> board.set(15,0,-1),
                "Numer kolumny > max"
        );
        assertTrue(thrown.getMessage().contains("Wrong"));

        thrown = assertThrows(
                OutOfRangeException.class,
                () -> board.set(0,-1,-1),
                "Numer wiersza < 0"
        );
        assertTrue(thrown.getMessage().contains("Wrong"));

        thrown = assertThrows(
                OutOfRangeException.class,
                () -> board.set(0,15,-1),
                "Numer wiersza > max"
        );
        assertTrue(thrown.getMessage().contains("Wrong"));

    }


    @Test
    /**
     * Testuje poprawność funkcji correctNumber
     */
    public  void CorrectNumberTest() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, OutOfRangeException {
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

        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(s);
        board.setBoard(tab);
        boolean rezult = board.correctNumber(0, 0, 1);

        assertFalse(rezult);
    }

    @Test
    /**
     * Testuje poprawność funkcji correct
     */
    public  void CorrectTest() throws NoSuchMethodException, OutOfRangeException, InvocationTargetException, IllegalAccessException {
        //Test polegający na sprawdzeniu, że correct na wypełnionym wierszu sudoku da fałsz.
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

        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(s);
        board.setBoard(tab);
        boolean rezult;


        Method method = SudokuBoard.class.getDeclaredMethod("correct", int[].class, int.class);
        method.setAccessible(true);
        rezult = (boolean) method.invoke(board, board.getRow(7), 7);
        assertFalse(rezult);

        //Test sprawdzający, czy próba dodania nie istniejącej wartości do tablicy zakończy się zwróceniem True.
        rezult = (boolean) method.invoke(board, board.getRow(8), 0);
        assertTrue(rezult);

        //
    }

}
