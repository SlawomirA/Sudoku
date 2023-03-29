package pl.sudokuboard;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pl.sudokuboard.exception.InvalidIndex;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InvalidIndexTest {
    InvalidIndexTest() {}

    @Test
    public void InvalidIndexTestOne() {
        SudokuBoard b = new SudokuBoard(new BacktrackingSudokuSolver());
        InvalidIndex thrown = assertThrows(
                InvalidIndex.class,
                () -> b.get(150,150),
                "Powinien wyrzucić wyjątek."
        );

        assertTrue(thrown.getMessage().length() > 0);
    }
}
