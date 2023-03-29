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
}
