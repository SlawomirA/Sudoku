package pl.sudokuboard;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa bazowa dla Sudoku Row,Column,Box. Zawiera listę i metodę sprawdzania poprawności pola.
 */
public class SudokuComponent {
    private final int size = 9;
    private final List<SudokuField> fields = new ArrayList<>();

    public SudokuComponent(List<SudokuField> list) {
        fields.addAll(list);
    }

    public int getFieldValue(int number) {
        return fields.get(number).getFieldValue();
    }

    public boolean verify() {
        int[] values = new int[size];
        int index;

        for (int i = 0; i < size; i++) {
            index = fields.get(i).getFieldValue() - 1;
            // Pole niewypełnione.
            if (index < 0) {
                continue;
            }
            values[index]++;
            if (values[index] > 1) {
                return false;
            }
        }
        return true;
    }
}
