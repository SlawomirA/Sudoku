package pl.sudokuboard;

/**
 * Interfejs klas rozwiązujących tablicę Sudoku.
 */
public interface SudokuSolver {
    void solve(SudokuBoard board) throws OutOfRangeException;
}
