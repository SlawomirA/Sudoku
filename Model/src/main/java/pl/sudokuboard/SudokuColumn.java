package pl.sudokuboard;

import java.util.List;

/**
 * Klasa będąca odzwierciedleniem kolumny, korzysta z klasy bazowej Component.
 */
public class SudokuColumn extends SudokuComponent {
    public SudokuColumn(List<SudokuField> list) {
        super(list);
    }
}
