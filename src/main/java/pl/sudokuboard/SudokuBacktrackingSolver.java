package pl.sudokuboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Klasa odpowiadająca za rozwiązanie sudoku algorytmem z użyciem backtrackingu.
 */
public class SudokuBacktrackingSolver {
    private final SudokuBoard sudokuBoard;
    private static final ArrayList<Integer> allowedNumbers
            = new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

    public SudokuBacktrackingSolver(SudokuBoard board) {
        sudokuBoard = board;
    }

    /**
     * Rozwiązuje sudoku idąc po wierszach. Jesli jakikegoś wiersza nie można rozwiązać
     * po pewnej skończonej liczbie kroków czyści go.
     *
     * @throws OutOfRangeException Oznacza, że index wiersza jest poza zakresem.
     */
    public void solve() throws OutOfRangeException {
        for (int row = 0; row < sudokuBoard.getBoardSize(); row++) {
            int failures = 0;
            while (!solveRow(row)) {
                cleanRow(row);
                failures++;
                if (failures > 20) {
                    row = 0;
                }
            }
        }
    }

    /**
     * Czyści wiersz.
     *
     * @param row Index wiersza
     * @throws OutOfRangeException Index wiersza poza zakresem.
     */
    private void cleanRow(int row) throws OutOfRangeException {
        for (int i = 0; i < sudokuBoard.getBoardSize(); i++) {
            sudokuBoard.set(row, i, 0);
        }
    }

    /**
     * Rozwiązuje wiersz idąc po kolumnach należących do tego wiersza metodą backtrackingu.
     *
     * @param row Index wiersza
     * @return True/False w zależności czy udało się rozwiązać wiersz
     *     w pewnej skończonej liczbie kroków.
     * @throws OutOfRangeException Index wiersza poza zakresem.
     */
    private boolean solveRow(int row) throws OutOfRangeException {
        List<Integer> currentNumbers = generateNumbers();

        for (int col = 0; col < sudokuBoard.getBoardSize(); col++) {
            boolean inserting = true;
            int currentIndex = 0;
            int inserts = 0;
            while (inserting) {
                if (correctNumber(col, row, currentNumbers.get(currentIndex))) {
                    sudokuBoard.set(row, col, currentNumbers.get(currentIndex));
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

                if (inserts > sudokuBoard.getBoardSize()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Losuje kolejność liczb 0-9 z listy.
     *
     * @return Lista z pomieszanymi wartościami
     */
    private List<Integer> generateNumbers() {
        List<Integer> list = (List<Integer>) allowedNumbers.clone();
        Collections.shuffle(list);
        return list;
    }

    /**
     * Sprawdzanie poprawności wstawianego numerka w kolumnie, wierszu i kwadracie.
     *
     * @param col   Index kolumny
     * @param row   Index wiersza
     * @param value Wartość, którą należy sprawdzić
     * @return True/False w zależności czy wartość jest poprawna w wierszu kolumnie i boxie 3x3
     * @throws OutOfRangeException Index kolumny/wiersza poza zakresem.
     */
    private boolean correctNumber(int col, int row, int value) throws OutOfRangeException {
        return correct(sudokuBoard.getCol(col), value)
                && correct(sudokuBoard.getRow(row), value)
                && correct(sudokuBoard.getBox(col, row), value);
    }

    /**
     * Sprawdzanie poprawności (w tablicy nie może występować wartość którą chcemy wstawić).
     *
     * @param tab   Tablica zawierająca wartości z kolumny/wiersza/boxa 3x3
     * @param value Wartość do sprawdzenia.
     * @return True/False
     */
    private boolean correct(int[] tab, int value) {
        for (int i = 0; i < sudokuBoard.getBoardSize(); i++) {
            if (tab[i] == value) {
                return false;
            } else if (tab[i] == 0) {
                break;
            }
        }
        return true;
    }
}
