package pl.sudokuboard;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SudokuFieldTest {

    public SudokuFieldTest()
    {

    }

    @Test
    public void toStringTest()
    {
        SudokuField field = new SudokuField(6);
        String expected = new ToStringBuilder(field).append("value", 6).toString();
        assertEquals(expected, field.toString());

    }

    @Test
    public void hashCodeTest()
    {
        SudokuField f = new SudokuField(5);
        SudokuField f2 = new SudokuField(5);
        SudokuField f3 = new SudokuField(6);

        int expected = new HashCodeBuilder(17, 77)
                .append(f.getFieldValue())
                .toHashCode();
        assertEquals(expected, f.hashCode());
        assertEquals(f2.hashCode(), f.hashCode());
        assertEquals(f.hashCode(), f.hashCode());
        assertNotEquals(f.hashCode(), f3.hashCode());
    }

    @Test
    public void equalsTest()
    {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuField f = new SudokuField(5);
        SudokuField f2 = new SudokuField(5);
        SudokuField f3 = new SudokuField(6);

        assertNotEquals(null, f2);
        assertNotEquals(f2, null);
        assertNotEquals(f, solver);
        assertNotEquals(solver, f);
        assertEquals(f, f);
        assertEquals(f, f2);
        assertNotEquals(f, f3);
    }
}
