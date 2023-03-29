package pl.sudokuboard.exception;

import pl.sudokuboard.logger.GlobalLogger;
import pl.sudokuboard.logger.I10N;

public class NullCloneException extends CloneNotSupportedException {
    public NullCloneException(Object... args) {
        super(GlobalLogger.message(I10N.NULL_CLONE_ATTEMPT, args));
        GlobalLogger.warn(I10N.NULL_CLONE_ATTEMPT, args);
    }
}
