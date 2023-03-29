package pl.sudokuboard.exception;

import pl.sudokuboard.logger.GlobalLogger;
import pl.sudokuboard.logger.I10N;

public class JdbcNullBoardException extends Exception {
    public JdbcNullBoardException(String databaseUrl, String boardName) {
        GlobalLogger.warn(I10N.DB_NULL_BOARD_ERROR, databaseUrl, boardName);
    }
}
