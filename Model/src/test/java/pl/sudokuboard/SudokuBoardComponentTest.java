package pl.sudokuboard;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Testy jednostkowe projektu SudokuBoard.
 */
public class SudokuBoardComponentTest {

    /**
     * Uruchamia wszystkie testy.
     */
    public SudokuBoardComponentTest() {
    }

    @Test
    public void sudokuBoardElementString() throws OutOfRangeException {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        SudokuComponent f1 = board.getRow(1);
        SudokuComponent f2 = board.getRow(2);
        SudokuComponent f3 = board.getRow(3);
        SudokuComponent f4 = board.getRow(1);

        assertEquals(f1.toString(), f1.toString());
        assertNotEquals(f1.toString(), f2.toString());
        assertNotEquals(f1.toString(), f3.toString());
        assertNotEquals(f1.toString(), f4.toString());
    }

    @Test
    public void sudokuBoardElementEquals() throws OutOfRangeException {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        SudokuComponent f1 = board.getRow(1);
        SudokuComponent f2 = board.getRow(2);
        SudokuComponent f3 = board.getRow(3);
        SudokuComponent f4 = board.getRow(1);
        SudokuComponent f5 = board.getRow(1);

        int test = 1;

        assertNotEquals(f1, null);
        assertNotEquals(f1, test);
        assertEquals(f1, f1);
        assertEquals(f1, f1);
        assertNotEquals(f1, f2);
        assertNotEquals(f1, f3);
        assertEquals(f1, f4);
        assertEquals(f4, f5);
        assertEquals(f1, f5);
        assertEquals(f1.equals(f4), f1.hashCode() == f4.hashCode());
    }

    @Test
    public void sudokuBoardElementHashCode() throws OutOfRangeException {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        SudokuComponent f1 = board.getRow(1);
        SudokuComponent f2 = board.getRow(2);
        SudokuComponent f3 = board.getRow(3);
        SudokuComponent f4 = board.getRow(1);

        assertEquals(f1.hashCode(), f1.hashCode());
        assertNotEquals(f1.hashCode(), f2.hashCode());
        assertNotEquals(f1.hashCode(), f3.hashCode());
        assertEquals(f1.hashCode(), f4.hashCode());
    }

}


