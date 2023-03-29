package pl.sudokuboard;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SudokuBoardDaoFactoryTest {
    public SudokuBoardDaoFactoryTest(){

    }

    @Test
    public void FileSudokuBoardDao(){
        SudokuBoardFactory factory = new SudokuBoardDaoFactory();
        try(
                FileSudokuBoardDao dao = (FileSudokuBoardDao) factory.getFileDao("Test.txt")
        ) {
            assertNotEquals(dao, null);
        }
    }

    @Test
    public void FileSudokuBoardJdbcDao() throws SQLException {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        try(
                JdbcSudokuBoardDao dao = (JdbcSudokuBoardDao) factory.getJdbcDao("Test");
        ) {
            assertNotEquals(dao, null);
        }
    }
}
