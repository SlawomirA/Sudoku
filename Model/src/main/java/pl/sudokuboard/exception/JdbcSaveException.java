package pl.sudokuboard.exception;

public class JdbcSaveException extends DaoException {
    public JdbcSaveException(Throwable cause) {
        super(cause);
    }
}
