package pl.sudokuboard.exception;

import pl.sudokuboard.logger.I10N;

public class JdbcCreateSchemaException extends JdbcException {
    public JdbcCreateSchemaException(Throwable cause, String databaseUrl) {
        super(cause, I10N.DB_CREATE_SCHEMA_ERROR, databaseUrl);
    }
}
