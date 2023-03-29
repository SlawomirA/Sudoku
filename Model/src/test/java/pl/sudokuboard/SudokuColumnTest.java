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
}
