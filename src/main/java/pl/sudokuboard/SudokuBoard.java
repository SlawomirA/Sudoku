package pl.sudokuboard;

import java.util.Arrays;

public class SudokuBoard {
    private int boxSize = 3;
    private int boardSize = 9;
    private int[][] board = new int[boardSize][boardSize];
    private final SudokuSolver solver;

    public SudokuBoard(SudokuSolver solver) {
        this.solver = solver;
    }

    /**
     * Rozwiązuje tablicę Sudoku.
     * @throws OutOfRangeException Wyjątek poza zakresem.
     */
    public void solveGame() throws OutOfRangeException {
        this.solver.solve(this);
    }

    public void setBoard(int[][] tab) {
        board = tab;
    }


    /**
     * Zwraca rozmiar planszy, domyślnie 9.
     * @return Int, domyślnie 9.
     */
    public int getBoardSize() {
        return boardSize;
    }

    /**
     * Zwraca rozmiar "pudełka" czyli 3.
     * @return Int, domyślnie 3.
     */
    public int getBoxSize() {

        return boxSize;
    }

    /**Zwraca konkretne pole z planszy.
     *
     * @param col Index kolumny.
     * @param row Index wiersza.
     * @return Int, wartość z tego pola
     * @throws OutOfRangeException Index kolumny bądź wiersza jest poza zakresem
     */
    public int get(int col, int row) throws OutOfRangeException {
        if (col < 0 || col > boardSize) {
            throw new OutOfRangeException("Wrong col value: " + col
                    + ". Use values between 0 and " + boardSize + ".");
        }
        if (row < 0 || row > boardSize) {
            throw new OutOfRangeException("Wrong row value: " + row
                    + ". Use values between 0 and " + boardSize + ".");
        }
        return board[col][row];
    }

    /**
     * Zwraca konkretną kolumnę.
     * @param value Index wiersza.
     * @return int[] Zwraca kopię oryginalnej tablicy
     *          rozszerzoną/przyciętą do zadanego rozmiaru (boardSize).
     * @throws OutOfRangeException Index wiersza poza zakresem/poza rozmiarem planszy.
     */
    public int[] getRow(int value) throws OutOfRangeException {
        if (value < 0 || value >= boardSize) {
            throw new OutOfRangeException("Wrong row value: "
                    + value + ". Use values between 0 and " + boardSize + ".");
        }
        return Arrays.copyOf(board[value],boardSize);
    }

    /**
     * Zwraca tablicę zawierającą zawartości z danej kolumny.
     * @param value Index danej kolumny.
     * @return int[], wypełniona wartościami z danej kolumny.
     * @throws OutOfRangeException Index kolumny poza zakresem/poza rozmiarem planszy.
     */
    public int[] getCol(int value) throws OutOfRangeException {
        if (value < 0 || value >= boardSize) {
            throw new OutOfRangeException("Wrong col value: "
                    + value + ". Use values between 0 and " + boardSize + ".");
        }

        int[] result = new int[boardSize];
        for (int i = 0; i < boardSize; i++) {
            result[i] = board[i][value];
        }
        return result;
    }

    /**
     * Zwraca tablicę jednowymiarową z wartościami z danego boxa 3x3.
     * @param col Index kolumny.
     * @param row Index wiersza.
     * @return Tablica[], wypełniona wartościami z konkretnego boxa 3x3.
     * @throws OutOfRangeException  Index kolumny/wiersza poza zakresem/poza rozmiarem planszy.
     */
    public int[] getBox(int col, int row) throws OutOfRangeException {
        if (col < 0 || col >= boardSize || row < 0 || row >= boardSize) {
            throw new OutOfRangeException("Wrong position on board: " + row + "," + col
                    + ". Please use numbers from 0 to: " + (boardSize - 1) + ".");
        }

        col = col / boxSize;        // /3 żeby wziąć index konkretnego kwadratu 3x3 czyli 0 1 2
        row = row / boxSize;        // /3 żeby wziąć index konkretnego kwadratu 3x3 czyli 0 1 2
        int[] box = new int[boxSize * boxSize]; // tablica  9 elementów czyli tyle ile ma kwadrat
        for (int i = 0; i < boxSize; i++) {     //Wypełnia nowo stworzoną tablicę a potem ją zwraca
            for (int j = 0; j < boxSize; j++) {
                box[i * boxSize + j] = board[i + row * boxSize][j + col * boxSize];
            }
        }

        return box;
    }

    /**
     * Zmienia wartość podanego pola.
     * @param col Index kolumny.
     * @param row Index wiersza.
     * @param value Wartość pola.
     * @throws OutOfRangeException Index kolumny/wiersza poza zakresem/poza rozmiarem planszy
     */
    public void set(int row, int col, int value) throws OutOfRangeException {
        if (row < 0 || row >= boardSize || col < 0 || col >= boardSize) {
            throw new OutOfRangeException("Wrong position on board: row: "
                    + row + ". Col: " + col + ". Use values between 0 and "
                    + (boardSize - 1) + ".");
        }
        board[row][col] = value;
    }

    /**
     * Sprawdzanie poprawności wstawianego numerka w kolumnie, wierszu i kwadracie.
     * @param col Index kolumny.
     * @param row Index wiersza.
     * @param value Wartość, którą należy sprawdzić.
     * @return True/False w zależności czy wartość jest poprawna w wierszu kolumnie i boxie 3x3.
     * @throws OutOfRangeException Index kolumny/wiersza poza zakresem.
     */
    public boolean correctNumber(int col, int row, int value) throws OutOfRangeException {
        return correct(getCol(col),value)
                && correct(getRow(row),value)
                && correct(getBox(col,row),value);
    }

    /**
     * Sprawdzanie poprawności (w tablicy nie może występować wartość którą chcemy wstawić).
     * @param tab Tablica zawierająca wartości z kolumny/wiersza/boxa 3x3
     * @param value Wartość do sprawdzenia.
     * @return True/False
     */
    private boolean correct(int[] tab, int value) {
        for (int i = 0;i < getBoardSize();i++) {
            if (tab[i] == value) {
                return false;
            } else if (tab[i] == 0) {
                break;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        SudokuBoard b;
    }

}
