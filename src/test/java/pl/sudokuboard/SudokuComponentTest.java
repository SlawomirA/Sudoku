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

public class SudokuComponentTest {

    public SudokuComponentTest(){

    }

    @Test
    public void verifyTest(){
        List<SudokuField> l = Arrays.asList(new SudokuField(1),new SudokuField(2),new SudokuField(3),
                    new SudokuField(4),new SudokuField(5),new SudokuField(6),new SudokuField(7),
                new SudokuField(8),new SudokuField(9));
        SudokuComponent comp = new SudokuComponent(l);
        assertTrue(comp.verify());


        List<SudokuField> l2 = Arrays.asList(new SudokuField(1),new SudokuField(1),new SudokuField(3),
                new SudokuField(4),new SudokuField(5),new SudokuField(6),new SudokuField(7),
                new SudokuField(8),new SudokuField(9));
        SudokuComponent comp2 = new SudokuComponent(l2);
        assertFalse(comp2.verify());
    }

    @Test
    public void toStringTest()
    {
        List<SudokuField> l = Arrays.asList(new SudokuField(1),new SudokuField(2),new SudokuField(3),
                new SudokuField(4),new SudokuField(5),new SudokuField(6),new SudokuField(7),
                new SudokuField(8),new SudokuField(9));
        SudokuComponent comp = new SudokuComponent(l);
        String expected = new ToStringBuilder(comp).append("fields", l).toString();
        assertEquals(expected, comp.toString());

    }

    @Test
    public void hashCodeTest()
    {

        List<SudokuField> l = Arrays.asList(new SudokuField(1),new SudokuField(2),new SudokuField(3),
                new SudokuField(4),new SudokuField(5),new SudokuField(6),new SudokuField(7),
                new SudokuField(8),new SudokuField(9));
        SudokuComponent comp = new SudokuComponent(l);


        List<SudokuField> l2 = Arrays.asList(new SudokuField(1),new SudokuField(2),new SudokuField(3),
                new SudokuField(4),new SudokuField(5),new SudokuField(6),new SudokuField(7),
                new SudokuField(8),new SudokuField(9));
        SudokuComponent comp2 = new SudokuComponent(l2);


        List<SudokuField> l3 = Arrays.asList(new SudokuField(10),new SudokuField(2),new SudokuField(3),
                new SudokuField(4),new SudokuField(5),new SudokuField(6),new SudokuField(7),
                new SudokuField(8),new SudokuField(9));
        SudokuComponent comp3 = new SudokuComponent(l3);

        int expected = new HashCodeBuilder(13, 37)
                .append(l)
                .toHashCode();
        assertEquals(expected, comp.hashCode());
        assertEquals(comp2.hashCode(), comp.hashCode());
        assertEquals(comp.hashCode(), comp.hashCode());
        assertNotEquals(comp.hashCode(), comp3.hashCode());
    }

    @Test
    public void equalsTest()
    {
        SudokuField f = new SudokuField(5);

        List<SudokuField> l = Arrays.asList(new SudokuField(1),new SudokuField(2),new SudokuField(3),
                new SudokuField(4),new SudokuField(5),new SudokuField(6),new SudokuField(7),
                new SudokuField(8),new SudokuField(9));
        SudokuComponent comp = new SudokuComponent(l);


        List<SudokuField> l2 = Arrays.asList(new SudokuField(1),new SudokuField(2),new SudokuField(3),
                new SudokuField(4),new SudokuField(5),new SudokuField(6),new SudokuField(7),
                new SudokuField(8),new SudokuField(9));
        SudokuComponent comp2 = new SudokuComponent(l2);

        List<SudokuField> l3 = Arrays.asList(new SudokuField(1),new SudokuField(2),new SudokuField(3),
                new SudokuField(4),new SudokuField(5),new SudokuField(6),new SudokuField(7),
                new SudokuField(8),new SudokuField(9));
        SudokuComponent comp3 = new SudokuComponent(l3);

        assertNotEquals(null, comp2);
        assertNotEquals(comp2,null);
        assertNotEquals(comp, f);
        assertNotEquals(f, comp);
        assertEquals(comp, comp);
        assertEquals(comp, comp2);
        assertEquals(comp, comp3);
    }
}
