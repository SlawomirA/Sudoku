package pl.sudokuboard;

import java.io.Serializable;

/**
 * Interfejs klas rozwiązujących tablicę Sudoku.
 */
public interface SudokuSolver extends Serializable, Cloneable {
    void solve(SudokuBoard board) throws OutOfRangeException;

    SudokuSolver clone() throws CloneNotSupportedException;
}
