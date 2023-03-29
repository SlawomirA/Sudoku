package pl.sudokuboard;

import java.util.List;

/**
 * Klasa będąca odzwierciedleniem wiersza, korzysta z klasy bazowej Component.
 */
public class SudokuRow extends SudokuComponent {
    public SudokuRow(List<SudokuField> list) {
        super(list);
    }
}
