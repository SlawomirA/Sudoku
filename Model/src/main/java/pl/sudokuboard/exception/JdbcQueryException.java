package pl.sudokuboard.exception;

import pl.sudokuboard.logger.I10N;

public class JdbcQueryException extends JdbcException {
    public JdbcQueryException(String databaseUrl, String message) {
        super(null, I10N.DB_QUERY_ERROR, databaseUrl, message);
    }
}