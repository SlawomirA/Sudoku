package pl.sudokuboard;

import java.util.Arrays;

public class SudokuBoard {
    private int boxSize = 3;
    private int boardSize = 9;
    private int[][] board = new int[boardSize][boardSize];


    public SudokuBoard() {

    }

    public SudokuBoard(int[][] board) {
        this.board = board;
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
     * @param value Index wiersza
     * @return int[] Zwraca kopię oryginalnej tablicy
     *      rozszerzoną/przyciętą do zadanego rozmiaru (boardSize).
     * @throws OutOfRangeException Index wiersza poza zakresem/poza rozmiarem planszy
     */
    public int[] getRow(int value) throws OutOfRangeException {
        if (value < 0 || value >= boardSize) {
            throw new OutOfRangeException("Wrong row value: " + value
                    + ". Use values between 0 and " + boardSize + ".");
        }
        return Arrays.copyOf(board[value],boardSize);
    }

    /**
     * Zwraca tablicę zawierającą zawartości z danej kolumny.
     * @param value Index danej kolumny
     * @return int[], wypełniona wartościami z danej kolumny
     * @throws OutOfRangeException Index kolumny poza zakresem/poza rozmiarem planszy
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
     * @param col Index kolumny
     * @param row Index wiersza
     * @return Tablica[], wypełniona wartościami z konkretnego boxa 3x3
     * @throws OutOfRangeException  Index kolumny/wiersza poza zakresem/poza rozmiarem planszy
     */
    public int[] getBox(int col, int row) throws OutOfRangeException {
        if (col < 0 || col >= boardSize || row < 0 || row >= boardSize) {
            throw new OutOfRangeException("Wrong position on board: " + row + "," + col
                    + ". Please use numbers from 0 to: " + (boardSize - 1) + ".");
        }

        col = col / boxSize;          // /3 żeby wziąć index konkretnego kwadratu 3x3 czyli 0 1 2
        row = row / boxSize;          // /3 żeby wziąć index konkretnego kwadratu 3x3 czyli 0 1 2
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
     * @param col Index kolumny
     * @param row Index wiersza
     * @param value Wartość pola.
     * @throws OutOfRangeException Index kolumny/wiersza poza zakresem/poza rozmiarem planszy
     */
    public void set(int row, int col, int value) throws OutOfRangeException {
        if (row < 0 || row >= boardSize || col < 0 || col >= boardSize) {
            throw new OutOfRangeException("Wrong position on board: row: "
                    + row + ". Col: " + col
                    + ". Use values between 0 and " + (boardSize - 1) + ".");
        }
        board[row][col] = value;
    }

    /**
     * Rozwiązuje sudoku w postaci board[][] według metody backtrackingu.
     * @throws OutOfRangeException Wyjatek poza zasiegiem
     */
    public void fillBoard() throws OutOfRangeException {
        SudokuBacktrackingSolver solver = new SudokuBacktrackingSolver(this);
        solver.solve();
    }

    public static void main(String[] args) {
        SudokuBoard b;
    }
}
