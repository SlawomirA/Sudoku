package pl.sudokuboard.exception;

import pl.sudokuboard.logger.GlobalLogger;
import pl.sudokuboard.logger.I10N;

public class CloneException extends CloneNotSupportedException {
    public CloneException() {
        super(GlobalLogger.message(I10N.CLONE_NOT_SUPPORTED));
        GlobalLogger.warn(I10N.CLONE_NOT_SUPPORTED);
    }
}
