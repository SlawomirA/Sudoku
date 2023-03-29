package pl.sudokuboard;

import java.util.Objects;
import pl.sudokuboard.exception.FileOperationException;
import pl.sudokuboard.exception.FileSaveException;
import pl.sudokuboard.exception.InvalidIndex;


public class SudokuBoardDecorator extends SudokuBoard {
    private final SudokuBoard parent;
    private final String parentName;

    public SudokuBoardDecorator(SudokuBoard baseBoard) throws CloneNotSupportedException {
        this(baseBoard, null);
    }

    public SudokuBoardDecorator(SudokuBoard baseBoard, SudokuBoard parent)
            throws CloneNotSupportedException {
        super(baseBoard);
        this.parent = Objects.requireNonNullElse(parent, baseBoard);
        this.parentName = String.valueOf(baseBoard.hashCode());
    }

    public String getParentName() {
        return parentName;
    }

    public SudokuBoard getParent() {
        return parent;
    }

    public void restoreBoard() throws InvalidIndex {
        for (int i = 0; i < getBoardSize(); i++) {
            for (int j = 0; j < getBoardSize(); j++) {
                set(i, j, parent.get(i, j));
            }
        }
    }

    public void save(String fileName) throws FileSaveException {
        SudokuBoardFactory<SudokuBoardDecorator> factory =
                new SudokuBoardDecoratorDaoFactory();
        try (
                FileSudokuBoardDecoratorDao dao =
                        (FileSudokuBoardDecoratorDao) factory.getFileDao(fileName)
        ) {
            dao.write(this);
        } catch (FileOperationException e) {
            throw new FileSaveException(e, fileName);
        }
    }
}
