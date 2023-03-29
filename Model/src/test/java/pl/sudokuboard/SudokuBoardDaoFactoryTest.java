package pl.sudokuboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SudokuBoardDaoFactoryTest {
    public SudokuBoardDaoFactoryTest(){

    }

    @Test
    public void FileSudokuBoardDao(){
        SudokuBoardFactory factory = new SudokuBoardDaoFactory();
        FileSudokuBoardDao dao = (FileSudokuBoardDao) factory.getFileDao("Test.txt");

        assertNotEquals(dao, null);
    }
}
