package pl.sudokuboard.exception;

import java.sql.SQLException;
import pl.sudokuboard.logger.GlobalLogger;
import pl.sudokuboard.logger.I10N;



public class JdbcException extends SQLException {
    public JdbcException(Throwable cause, I10N key, Object... args) {
        super(cause);
        GlobalLogger.error(key, args);
    }
}