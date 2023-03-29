package pl.sudokuboard;

import org.junit.jupiter.api.Test;
import pl.sudokuboard.exception.InvalidIndex;
import pl.sudokuboard.exception.JdbcLoadException;
import pl.sudokuboard.exception.JdbcSaveException;

import java.sql.SQLException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JdbcSudokuBoardDaoTest {

    @Test
    void DaoTest() throws InvalidIndex {
        String name = "Tablica";
        String name2 = "Tablica2";
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Map<Integer, String> boardsMap;

        try(JdbcSudokuBoardDao dao = (JdbcSudokuBoardDao) factory.getJdbcDao(name, "",true)) {
            boardsMap = dao.getBoards();
            assertEquals(boardsMap.keySet().size(), 0);

            assertTrue(dao.write(board));

            boardsMap = dao.getBoards();
            assertEquals(0, boardsMap.keySet().size());

            SudokuBoard b = dao.read();
            assertEquals(b, board);
            assertNotSame(b, board);
        } catch (JdbcLoadException | JdbcSaveException | SQLException e) {
            e.printStackTrace();
        }

        try(JdbcSudokuBoardDao dao = (JdbcSudokuBoardDao) factory.getJdbcDao(name2, "", true)) {
            assertThrows(JdbcLoadException.class, dao::read);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}