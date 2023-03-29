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
}
//Fabryka abstrakcyjna i metoda wytwórcza różnica