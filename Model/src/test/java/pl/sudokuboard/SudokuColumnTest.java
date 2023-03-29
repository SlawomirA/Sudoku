package pl.sudokuboard;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class SudokuColumnTest {

    public SudokuColumnTest() {

    }

    @Test
    public void sudokuColumnTestByCreatingObject(){

        List<SudokuField> l = Arrays.asList(new SudokuField(1),new SudokuField(2),new SudokuField(3),
                new SudokuField(4),new SudokuField(5),new SudokuField(6),new SudokuField(7),
                new SudokuField(8),new SudokuField(9));

        SudokuColumn c = new SudokuColumn(l);
        int a = c.getFieldValue(1);
        boolean b = a != 0;
        assertTrue(b);
    }
    @Test
    void testClone() throws OutOfRangeException {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        SudokuColumn f1 = board.getCol(1);
        SudokuColumn f2 = board.getCol(2);

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
