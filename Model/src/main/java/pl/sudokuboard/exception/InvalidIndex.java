package pl.sudokuboard.exception;

import pl.sudokuboard.OutOfRangeException;
import pl.sudokuboard.logger.GlobalLogger;
import pl.sudokuboard.logger.I10N;

public class InvalidIndex extends OutOfRangeException {
    public InvalidIndex() {
        super(GlobalLogger.message(I10N.INVALID_INDEX));
        GlobalLogger.warn(I10N.INVALID_INDEX);
    }
}

