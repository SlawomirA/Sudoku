package pl.sudokuboard;

import java.beans.PropertyChangeSupport;

/**
 * Klasa odzwierciedlająca wartość pojedynczego pola.
 */
public class SudokuField {

    private int val;
    private PropertyChangeSupport listener;

    public int getFieldValue() {
        return val;
    }

    public SudokuField() {
        this(0);
    }

    public SudokuField(int v) {
        val = v;
        listener = new PropertyChangeSupport(this);
    }


    /**
     * Ustala wartość pola sudoku.
     *
     * @param v Wartość pola sudoku.
     */
    public void setFieldValue(int v) {
        int oldValue = val;
        val = v;
        if (oldValue != val) {
            listener.firePropertyChange(String.format("Field"),
                    new SudokuField(oldValue), this);
        }

    }

    /**
     * Zwraca obserwatora tego pola.
     *
     * @return obserwator tego pola.
     */
    public PropertyChangeSupport getListener() {
        return listener;
    }
}
