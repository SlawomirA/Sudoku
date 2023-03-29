package pl.sudokuboard;

import java.io.Serializable;

/**
 * Interfejs klas rozwiązujących tablicę Sudoku.
 */
public interface SudokuSolver extends Serializable {
    void solve(SudokuBoard board) throws OutOfRangeException;
}
