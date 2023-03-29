package pl.sudokuboard;

import java.util.List;

/**
 * Klasa będąca odzwierciedleniem kwadratu, korzysta z klasy bazowej Component.
 */
public class SudokuBox extends SudokuComponent {
    public SudokuBox(List<SudokuField> list) {
        super(list);
    }
}
