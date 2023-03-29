package pl.sudokuboard;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OutOfRangeExceptionTest {
    OutOfRangeExceptionTest() {}

    @Test
    public void outOfRangeExceptionTestOne() {
        SudokuBoard b = new SudokuBoard(new BacktrackingSudokuSolver());
        OutOfRangeException thrown = assertThrows(
                OutOfRangeException.class,
                () -> b.get(150,150),
                "Powinien wyrzucić wyjątek."
        );

        assertTrue(thrown.getMessage().contains("Wrong"));
    }
}
