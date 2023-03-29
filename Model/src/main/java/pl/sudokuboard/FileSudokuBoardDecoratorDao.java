package pl.sudokuboard;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import pl.sudokuboard.exception.FileOperationException;
import pl.sudokuboard.exception.FileReadException;
import pl.sudokuboard.exception.FileSaveException;
import pl.sudokuboard.exception.WrongClassException;

public class FileSudokuBoardDecoratorDao implements Dao<SudokuBoardDecorator>, AutoCloseable {
    String fileName;

    public FileSudokuBoardDecoratorDao(final String fileName) {
        this.fileName = fileName;
    }

    public SudokuBoardDecorator read() throws FileOperationException, WrongClassException {
        SudokuBoardDecorator sudokuBoardDecorator = null;

        try (
                FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            sudokuBoardDecorator = (SudokuBoardDecorator) ois.readObject();
        } catch (IOException e) {
            throw new FileReadException(e, fileName);
        } catch (ClassNotFoundException e) {
            throw new WrongClassException();
        }

        return sudokuBoardDecorator;
    }

    public boolean write(final SudokuBoardDecorator sudokuBoard) throws FileOperationException {
        try (
                FileOutputStream fos = new FileOutputStream(fileName);
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(sudokuBoard);
        } catch (IOException e) {
            throw new FileSaveException(e, fileName);
        }
        return true;
    }

    @Override
    public void close() {
    }
}