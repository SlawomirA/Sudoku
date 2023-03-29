package pl.sudokuboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SudokuBoardDecoratorDaoFactoryTest {

    @Test
    void getFileDao() {
        SudokuBoardDecoratorDaoFactory factory = new SudokuBoardDecoratorDaoFactory();
        FileSudokuBoardDecoratorDao dao = (FileSudokuBoardDecoratorDao) factory.getFileDao("Test.txt");

        assertNotEquals(dao, null);
    }
}