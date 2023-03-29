package pl.sudokuboard.exception;

import pl.sudokuboard.logger.GlobalLogger;
import pl.sudokuboard.logger.I10N;

public class FileSaveException extends FileOperationException {
    public FileSaveException(Throwable cause, Object... args) {
        super(cause);
        GlobalLogger.warn(I10N.FILE_SAVE_ERROR, args);
    }
}
