package pl.sudokuboard;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        ArrayList<Integer> ints = new ArrayList<>(Arrays.asList(9,7,8,6,5,4,3,2,1));
        ArrayList<SudokuField> tab = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            tab.add(new SudokuField(ints.get(i)));
        }

        SudokuComponent comp = new SudokuComponent(tab);

        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(s);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.set(i, j,j+1);
                //System.out.println(String.format("%d => %d", j+1, board.get(j, i)));
            }
        }

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

        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(s);

        board.solveGame();

        for (int i = 0; i < 9; i++) {
            SudokuRow r = board.getRow(i);
            for (int j = 0; j < 9; j++)
                assertEquals(board.get(i,j), r.getFieldValue(j));
        }


    }


    @Test
    /**
     * Testuje metodę GetCol
     */
    public void BoardGetColTest() throws OutOfRangeException {

        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(s);

        board.solveGame();

        for (int i = 0; i < 9; i++) {
            SudokuColumn c = board.getCol(i);
            for (int j = 0; j < 9; j++)
                assertTrue(board.get(j,i) == c.getFieldValue(j));
        }
    }

    @Test
    /**
     * Testuje metodę GetBox
     */
    public void BoardGetBoxTest() throws OutOfRangeException {


        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(s);

        board.solveGame();

        for (int i = 0; i < 9; i+=3) {
            for (int j = 0; j < 9; j+=3) {
                SudokuBox b = board.getBox(i, j);
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        assertEquals(b.getFieldValue(k*3+l), board.get(i + k, j + l));
                    }
                }
            }
        }

    }


    @Test
    /**
     * Testuje metodę Set
     */
    public void BoardSetTest() throws OutOfRangeException {


        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(s);
        board.solveGame();
        //System.out.println(board.get(0,0));
        board.set(0,0, 0);
        assertEquals(board.get(0,0),0);

    }


    @Test
    /**
     * Testuje poprawność funkcji correctNumber
     */
    public  void CorrectNumberTest() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, OutOfRangeException {

        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(s);

        assertTrue(board.correctNumber(0,0,1));

        board.solveGame();
        assertFalse(board.correctNumber(0, 0, 1));
    }

    @Test
    /**
     * Testuje poprawność funkcji correct
     */
    public  void CorrectTest() throws NoSuchMethodException, OutOfRangeException, InvocationTargetException, IllegalAccessException {
        ArrayList<SudokuField> fields = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            fields.add(i,new SudokuField(i));
        }
        SudokuComponent comp = new SudokuComponent(fields);

        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(s);


        Method method = SudokuBoard.class.getDeclaredMethod("correct", SudokuComponent.class, int.class);
        method.setAccessible(true);
        assertFalse((boolean) method.invoke(board, comp,0));
        assertTrue((boolean) method.invoke(board, comp,9));
    }

    @Test
    public void ObserverTest() throws OutOfRangeException {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        int value = board.get(0, 0);
        board.set(0, 0, board.get(0, 1));
        assertEquals(value, board.get(0, 0));
    }

}
