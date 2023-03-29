package pl.sudokuboard.exception;

public class JdbcLoadException extends DaoException {
    public JdbcLoadException(Throwable cause) {
        super(cause);
    }
}
