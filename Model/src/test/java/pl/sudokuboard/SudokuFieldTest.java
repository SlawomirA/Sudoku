package pl.sudokuboard;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.jupiter.api.Test;

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

    @Test
    void compareTo() {
        SudokuField f1 = new SudokuField(1);
        SudokuField f2 = new SudokuField(2);
        SudokuField f3 = new SudokuField(3);
        SudokuField f4 = new SudokuField(1);

        assertEquals(f1.compareTo(f2), -1);
        assertEquals(f1.compareTo(f3), -2);
        assertEquals(f1.compareTo(f4), 0);
        assertEquals(f2.compareTo(f3), -1);
        assertEquals(f2.compareTo(f4), 1);
        assertEquals(f3.compareTo(f4), 2);
        assertThrows(NullPointerException.class, ()->{f1.compareTo(null);});
    }

    @Test
    void testClone() {
        SudokuField f1 = new SudokuField(1);
        SudokuField f2 = new SudokuField(2);

        try {
            SudokuField fc1 = f1.clone();
            SudokuField fc2 = f2.clone();

            assertEquals(f1, fc1);
            assertEquals(f2, fc2);

            assertNotEquals(fc1, f2);
            assertNotEquals(fc2, f1);

            f1.setFieldValue(12);
            f2.setFieldValue(45);
            assertNotEquals(f1, fc1);
            assertNotEquals(f2, fc2);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}
