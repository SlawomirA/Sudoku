package pl.sudokuboard;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
     * @throws IOException Input output exception
     * @throws ClassNotFoundException Class is not found exception.
     */
    public SudokuBoard read() throws IOException, ClassNotFoundException {
        SudokuBoard sudokuBoard = null;

        try (
                FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            sudokuBoard = (SudokuBoard) ois.readObject();
        }

        return sudokuBoard;
    }

    /**
     * Serializuje SudokuBoard do pliku.
     * @param sudokuBoard Obiekt do serializacji.
     * @return Prawda/Fałsz czy się udało.
     * @throws IOException Input output exception.
     */
    public boolean write(final SudokuBoard sudokuBoard) throws IOException {
        try (
                FileOutputStream fos = new FileOutputStream(fileName);
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(sudokuBoard);
        }
        return true;
    }

    @Override
    public void close() {
    }
}
