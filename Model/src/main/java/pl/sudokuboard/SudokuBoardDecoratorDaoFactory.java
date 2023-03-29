package pl.sudokuboard;

public class SudokuBoardDecoratorDaoFactory
        implements SudokuBoardFactory<SudokuBoardDecorator> {

    public Dao<SudokuBoardDecorator> getFileDao(final String fileName) {
        return new FileSudokuBoardDecoratorDao(fileName);
    }
}
