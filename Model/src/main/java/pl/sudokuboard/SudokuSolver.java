package pl.sudokuboard;

import java.io.Serializable;
import pl.sudokuboard.exception.CloneException;
import pl.sudokuboard.exception.InvalidIndex;


/**
 * Interfejs klas rozwiązujących tablicę Sudoku.
 */
public interface SudokuSolver extends Serializable, Cloneable {
    void solve(SudokuBoard board) throws InvalidIndex;

    SudokuSolver clone() throws CloneException;
}
