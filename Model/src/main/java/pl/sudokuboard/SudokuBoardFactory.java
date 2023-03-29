package pl.sudokuboard;

/**
 * Interfejs fabryk używanych do serializacji.
 * @param <T> Klasa mająca zaimplementowany interfejs DAO.
 */
public interface SudokuBoardFactory<T> {

    Dao<T> getFileDao(final String fileName);
}
