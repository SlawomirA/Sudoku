package pl.sudokuboard;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class SudokuRowTest {

    public SudokuRowTest() {

    }

    @Test
    public void sudokuRowTestByCreatingObject(){

        List<SudokuField> l = Arrays.asList(new SudokuField(1),new SudokuField(2),new SudokuField(3),
                new SudokuField(4),new SudokuField(5),new SudokuField(6),new SudokuField(7),
                new SudokuField(8),new SudokuField(9));

        SudokuRow r = new SudokuRow(l);
        int a = r.getFieldValue(1);
        boolean b = a != 0;
        assertTrue(b);
    }
}
