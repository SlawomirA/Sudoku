package pl.sudokuboard.exception;

import pl.sudokuboard.logger.GlobalLogger;
import pl.sudokuboard.logger.I10N;

public class FileReadException extends FileOperationException {
    public FileReadException(Throwable cause, Object... args) {
        super(cause);
        GlobalLogger.warn(I10N.FILE_READ_ERROR, args);
    }
}

