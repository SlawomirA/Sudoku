package pl.sudokuboard;

import org.junit.jupiter.api.Test;
import pl.sudokuboard.exception.FileOperationException;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileSudokuBoardDecoratorDaoTest {

    @Test
    void SerializationDeserializationTest() throws CloneNotSupportedException, OutOfRangeException {
        String path = "Test.txt";
        SudokuBoardDecoratorDaoFactory factory = new SudokuBoardDecoratorDaoFactory();
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        SudokuBoardDecorator decorator = new SudokuBoardDecorator(board);
        SudokuBoardDecorator decorator2 = null;

        try (
                FileSudokuBoardDecoratorDao dao = (FileSudokuBoardDecoratorDao)factory.getFileDao(path)
        ) {
            dao.write(decorator);
            decorator2 = dao.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(decorator.hashCode(), decorator2.hashCode());
        assertEquals(decorator, decorator2);

        File test = new File(path);
        test.delete();

        try (
                FileSudokuBoardDecoratorDao dao = (FileSudokuBoardDecoratorDao)factory.getFileDao("")
        ) {

            assertThrows(FileOperationException.class, () -> {dao.write(decorator);});
        }
        try (
                FileSudokuBoardDecoratorDao dao = (FileSudokuBoardDecoratorDao)factory.getFileDao("")
        ) {
            assertThrows(FileOperationException.class, dao::read);
        }
    }
}