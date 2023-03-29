package pl.sudokuboard;

import java.util.List;
import pl.sudokuboard.exception.CloneException;


/**
 * Klasa będąca odzwierciedleniem kwadratu, korzysta z klasy bazowej Component.
 */
public class SudokuBox extends SudokuComponent {
    public SudokuBox(List<SudokuField> list) {
        super(list);
    }

    @Override
    protected SudokuBox clone() throws CloneException {
        return (SudokuBox) super.clone();
    }
}
