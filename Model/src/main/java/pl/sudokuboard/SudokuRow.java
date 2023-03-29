package pl.sudokuboard;

import java.util.List;
import pl.sudokuboard.exception.CloneException;


/**
 * Klasa będąca odzwierciedleniem wiersza, korzysta z klasy bazowej Component.
 */
public class SudokuRow extends SudokuComponent {
    public SudokuRow(List<SudokuField> list) {
        super(list);
    }

    @Override
    protected SudokuRow clone() throws CloneException {
        return (SudokuRow) super.clone();
    }
}
