package pl.sudokuboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Klasa odpowiadająca za rozwiązanie sudoku algorytmem z użyciem backtrackingu.
 */
public class BacktrackingSudokuSolver implements SudokuSolver {

    private int maxFailures = 2;
    private static final ArrayList<Integer> allowedNumbers
            = new ArrayList(Arrays.asList(1,2,3,4,5,6,7,8,9));


    @Override
    /**
     * Rozwiązuje sudoku idąc po wierszach. Jesli jakikegoś wiersza nie można rozwiązać
     * po pewnej skończonej liczbie kroków czyści go.
     * @throws OutOfRangeException Oznacza, że index wiersza jest poza zakresem.
     */
    public void solve(SudokuBoard board) throws OutOfRangeException {
        for (int row = 0; row < board.getBoardSize(); row++) {
            int failures = 0;
            while (!solveRow(board, row)) {
                cleanRow(board, row);
                failures++;
                if (failures > maxFailures) {
                    row = 0;
                    maxFailures++;
                }
            }
        }
    }

    /**
     * Czyści wiersz.
     *
     * @param board Board do wyczyszczenia
     * @param row   Index wiersza
     * @throws OutOfRangeException Index wiersza poza zakresem.
     */
    private void cleanRow(SudokuBoard board, int row) throws OutOfRangeException {
        for (int i = 0; i < board.getBoardSize();i++) {
            board.set(row, i, 0);
        }
    }

    /**
     * Rozwiązuje wiersz idąc po kolumnach należących do tego wiersza metodą backtrackingu.
     * @param row Index wiersza
     * @return True/False w zależności czy udało się
     *          rozwiązać wiersz w pewnej skończonej liczbie kroków.
     * @throws OutOfRangeException Index wiersza poza zakresem.
     */
    private boolean solveRow(SudokuBoard board, int row) throws OutOfRangeException {
        List<Integer> currentNumbers = generateNumbers();

        for (int col = 0; col < board.getBoardSize(); col++) {
            boolean inserting = true;
            int currentIndex = 0;
            int inserts = 0;
            while (inserting) {
                if (board.correctNumber(col, row, currentNumbers.get(currentIndex))) {
                    board.set(row, col, currentNumbers.get(currentIndex));
                    currentNumbers.remove(currentIndex);
                    currentIndex--;
                    inserting = false;
                    inserts--;
                }
                inserts++;
                currentIndex++;
                if (currentIndex >= currentNumbers.size()) {
                    currentIndex = 0;
                }

                if (inserts > board.getBoardSize()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Losuje kolejność liczb 0-9 z listy.
     * @return Lista z pomieszanymi wartościami
     */
    private List<Integer> generateNumbers() {
        List<Integer> list = (List<Integer>) allowedNumbers.clone();
        Collections.shuffle(list);
        return list;
    }


}
