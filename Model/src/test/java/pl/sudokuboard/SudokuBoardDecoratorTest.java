package pl.sudokuboard;

import org.junit.jupiter.api.Test;
import pl.sudokuboard.exception.FileOperationException;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SudokuBoardDecoratorTest {

    @Test
    void restoreBoard() throws CloneNotSupportedException, OutOfRangeException {
        SudokuBoardDecoratorDaoFactory factory = new SudokuBoardDecoratorDaoFactory();
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        SudokuBoardDecorator decorator = new SudokuBoardDecorator(board);

        int value = decorator.get(1, 1);
        decorator.set(1, 1, 0);
        assertEquals(decorator.get(1, 1), 0);
        assertNotEquals(decorator.get(1, 1), value);

        decorator.restoreBoard();
        assertNotEquals(decorator.get(1, 1), 0);
        assertEquals(decorator.get(1, 1), value);
    }

    @Test
    void testSave() throws CloneNotSupportedException, OutOfRangeException {
        String path = "Test.txt";
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        SudokuBoardDecorator decorator = new SudokuBoardDecorator(board);

        try {
            decorator.save(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        File test = new File(path);
        assertTrue(test.exists());

        test.delete();

        assertThrows(FileOperationException.class, () -> {decorator.save("");;});
    }
}