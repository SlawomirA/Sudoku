package pl.sudokuboard;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class SudokuBoxTest {

    public SudokuBoxTest() {

    }

    @Test
    public void sudokuBoxTestByCreatingObject(){

        List<SudokuField> l = Arrays.asList(new SudokuField(1),new SudokuField(2),new SudokuField(3),
                new SudokuField(4),new SudokuField(5),new SudokuField(6),new SudokuField(7),
                new SudokuField(8),new SudokuField(9));

        SudokuBox bo = new SudokuBox(l);
        int a = bo.getFieldValue(1);
        boolean b = a != 0;
        assertTrue(b);
    }

    @Test
    void testClone() throws OutOfRangeException {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        SudokuBox f1 = board.getBox(1, 1);
        SudokuBox f2 = board.getBox(5, 1);

        try {
            SudokuComponent fc1 = f1.clone();
            SudokuComponent fc2 = f2.clone();

            assertEquals(fc1, f1);
            assertEquals(fc2, f2);

            assertNotEquals(fc1, f2);
            assertNotEquals(fc2, f1);

            fc1.setField(12,1);
            fc2.setField(12,1);

            assertNotEquals(fc1, f1);
            assertNotEquals(fc2, f2);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
