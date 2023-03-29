package pl.sudokuboard.exception;

import pl.sudokuboard.logger.I10N;

public class JdbcConnectionException extends JdbcException {
    public JdbcConnectionException(Throwable cause, String databaseUrl) {
        super(cause, I10N.DB_CONNECTION_ERROR, databaseUrl);
    }
}
