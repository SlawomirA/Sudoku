package pl.sudokuboard.exception;

import pl.sudokuboard.logger.GlobalLogger;
import pl.sudokuboard.logger.I10N;

public class WrongClassException extends ClassNotFoundException {
    public WrongClassException() {
        super();
        GlobalLogger.warn(I10N.CLASS_NOT_FOUND_ERROR);
    }
}
