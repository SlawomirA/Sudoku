package pl.sudokuboard;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SudokuBoard implements PropertyChangeListener {
    private int boxSize = 3;
    private int boardSize = 9;
    private final SudokuField[][] board = new SudokuField[boardSize][boardSize];
    private final SudokuSolver solver;
    private final PropertyChangeSupport propertyChangeSupp = new PropertyChangeSupport(this);

    public SudokuBoard(SudokuSolver solver) {
        this.solver = solver;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new SudokuField();
                board[i][j].getListener().addPropertyChangeListener(this);
            }
        }
    }


    /**
     *  Rozwiązuje board.
     * @throws OutOfRangeException Wyjątek poza zakresem.
     */
    public void solveGame() throws OutOfRangeException {
        this.solver.solve(this);
        propertyChangeSupp.addPropertyChangeListener(this);

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
        return board[col][row].getFieldValue();
    }

    /**
     * Zwraca konkretną kolumnę.
     * @param value Index wiersza.
     * @return int[] Zwraca kopię oryginalnej
     *          tablicy rozszerzoną/przyciętą do zadanego rozmiaru (boardSize).
     */
    public SudokuRow getRow(int value) {
        return new SudokuRow(Arrays.asList(board[value]).subList(0, boardSize));
    }

    /**
     * Zwraca tablicę zawierającą zawartości z danej kolumny.
     *
     * @param value Index danej kolumny.
     * @return int[], wypełniona wartościami z danej kolumny.
     */
    public SudokuColumn getCol(int value) {
        List<SudokuField> list = new ArrayList<>();
        for (int i = 0; i < boardSize; i++) {
            list.add(board[i][value]);
        }
        return new SudokuColumn(list);
    }

    /**
     * Zwraca tablicę jednowymiarową z wartościami z danego boxa 3x3.
     * @param col Index kolumny.
     * @param row Index wiersza.
     * @return Tablica[], wypełniona wartościami z konkretnego boxa 3x3.
     */
    public SudokuBox getBox(int row, int col) {
        col = col / boxSize;
        row = row / boxSize;
        List<SudokuField> list = new ArrayList<>();

        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++) {
                int r = boxSize * row + i;
                int c = col * boxSize + j;
                list.add(board[r][c]);
            }
        }
        return new SudokuBox(list);
    }

    private boolean checkBoard() {

        boolean result = true;
        for (int row = 0; row < boardSize; row++) {
            SudokuRow r = getRow(row);
            result &= r.verify();
        }

        for (int col = 0; col < boardSize; col++) {
            SudokuColumn c = getCol(col);
            result &= c.verify();
        }


        for (int row = 0; row < boxSize; row++) {
            for (int col = 0; col < boxSize; col++) {
                SudokuBox b = getBox(row * 3, col * 3);
                result &= b.verify();
            }
        }
        return result;
    }


    /**
     * Zmienia wartość podanego pola.
     * @param col Index kolumny.
     * @param row Index wiersza.
     * @param value Wartość pola.
     * @throws OutOfRangeException Wyjątek poza zakresem.
     */
    public void set(int row, int col, int value) throws OutOfRangeException {
        board[row][col].setFieldValue(value);
    }

    /**
     * Sprawdzanie poprawności wstawianego numerka w kolumnie, wierszu i kwadracie.
     * @param col Index kolumny.
     * @param row Index wiersza.
     * @param value Wartość, którą należy sprawdzić.
     * @return True/False w zależności czy wartość jest poprawna w wierszu kolumnie i boxie 3x3.
     * @throws OutOfRangeException Wyjątek poza zakresem.
     */
    public boolean correctNumber(int col, int row, int value) throws OutOfRangeException {
        boolean result = correct(getCol(col), value);
        result &= correct(getRow(row), value);
        result &= correct(getBox(row, col), value);
        return result;
    }


    /**
     * Sprawdzanie poprawności (w tablicy nie może występować wartość którą chcemy wstawić).
     * @param component Element tablicy
     * @param value Wartość do sprawdzenia.
     * @return True/False
     */
    private boolean correct(SudokuComponent component, int value) {
        for (int i = 0; i < boardSize; i++) {
            if (component.getFieldValue(i) == value) {
                return false;
            }
        }
        return true;
    }

    /**
     * Funkcja wywoływana w przypadku spełnienia warunków obserwatora (zmiana jest niepoprawna).
     *
     * @param evt Obiekt z informacjami o zmianie pola.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (!checkBoard()) {
            ((SudokuField) evt.getNewValue())
                    .setFieldValue(((SudokuField) evt.getOldValue()).getFieldValue());
        }
    }

    public static void main(String[] args) {
    }

}
