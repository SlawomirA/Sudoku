package pl.sudokuboard;

import java.util.List;
import pl.sudokuboard.exception.CloneException;


/**
 * Klasa będąca odzwierciedleniem kolumny, korzysta z klasy bazowej Component.
 */
public class SudokuColumn extends SudokuComponent {
    public SudokuColumn(List<SudokuField> list) {
        super(list);
    }


    @Override
    protected SudokuColumn clone() throws CloneException {
        return (SudokuColumn) super.clone();
    }
}
