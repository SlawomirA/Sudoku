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

/**
 *  Serializuje i deserializuje klasę SudokuBoard do i z pliku.
 */
public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {
    String fileName;

    /**
     * Konstruktor.
     * @param fileName nazwa pliku do serializacji/deserializacji.
     */
    public FileSudokuBoardDao(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Deserializuje sudokuBoard z pliku.
     * @return Obiekt sudokuBoard.
     * @throws FileOperationException Input output exception
     * @throws WrongClassException Class is not found exception.
     * @throws FileReadException Class is not found exception.
     */
    public SudokuBoard read() throws FileOperationException, WrongClassException {
        SudokuBoard sudokuBoard = null;

        try (
                FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            sudokuBoard = (SudokuBoard) ois.readObject();
        } catch (IOException e) {
            throw new FileReadException(e, fileName);
        } catch (ClassNotFoundException e) {
            throw new WrongClassException();
        }

        return sudokuBoard;
    }

    /**
     * Serializuje SudokuBoard do pliku.
     * @param sudokuBoard Obiekt do serializacji.
     * @return Prawda/Fałsz czy się udało.
     * @throws FileOperationException Input output exception.
     * @throws FileSaveException Input output exception.
     */
    public boolean write(final SudokuBoard sudokuBoard) throws FileOperationException {
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
