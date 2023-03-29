package pl.sudokuboard;

/**
 * Umożliwia serializację i deserializazcję sudokuBoard do i z pliku.
 */
public class SudokuBoardDaoFactory implements SudokuBoardFactory<SudokuBoard> {

    /**
     * Zwraca klasę umożliwiającą serializację sudokuBoard.
     * @param fileName Nazwa pliku.
     * @return Klasa umożliwiająca serializację.
     */
    public Dao<SudokuBoard> getFileDao(final String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

    public Dao<SudokuBoard> getJdbcDao(final String boardName) {
        return getJdbcDao(boardName, "", false);
    }

    public Dao<SudokuBoard> getJdbcDao(final String boardName, final String parentName) {
        return getJdbcDao(boardName, parentName, false);
    }

    public Dao<SudokuBoard> getJdbcDao(final String boardName,
                                       final String parentName, Boolean inMemory) {
        return new JdbcSudokuBoardDao(boardName, parentName, inMemory);
    }
}
//Fabryka abstrakcyjna i metoda wytwórcza różnica