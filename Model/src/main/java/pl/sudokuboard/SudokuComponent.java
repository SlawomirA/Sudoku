package pl.sudokuboard;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.sudokuboard.exception.CloneException;

/**
 * Klasa bazowa dla Sudoku Row,Column,Box. Zawiera listę i metodę sprawdzania poprawności pola.
 */
public class SudokuComponent implements Cloneable {
    private final int size = 9;
    private  List<SudokuField> fields = new ArrayList<>();

    public SudokuComponent(List<SudokuField> list) {
        fields.addAll(list);
    }

    public int getFieldValue(int number) {
        return fields.get(number).getFieldValue();
    }

    public void setField(int fieldValue, int index) {
        fields.get(index).setFieldValue(fieldValue);
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

    /**
     * Zwraca obiekt w postaci łańcucha znaków.
     *
     * @return Obiekt w postaci łańcucha znaków.
     */
    public final String toString() {
        return new ToStringBuilder(this)
                .append("fields", fields)
                .toString();
    }

    /**
     * Służy do zwrócenia (w miarę)
     * unikalnej wartości liczbowej typu int dla każdego unikalnego obiektu.
     * Jeśli dwa obiekty, których
     * porównanie przy pomocy metody equals() zwraca true, to metoda hashCode()
     * powinna zwracać dla tych obiektów taką samą wartość.
     *
     * @return Unikalna liczba dla tego obiektu.
     */
    public int hashCode() {
        return new HashCodeBuilder(13, 37)
                .append(fields)
                .toHashCode();
    }

    /**
     * Sprawdza, czy podany obiekt jest równy temu obiektowi.
     *
     * @param obj Obiekt do porównania.
     * @return True, jeśli obiekt jest ten sam.
     */
    public boolean equals(final Object obj) {
        if (obj == null || obj.getClass() != getClass())  {
            return false;
        }
        if (obj == this) {
            return true;
        }

        SudokuComponent sb = (SudokuComponent) obj;
        return new EqualsBuilder()
                .append(fields, sb.fields)
                .isEquals();
    }

    @Override
    protected SudokuComponent clone() throws CloneException {
        SudokuComponent element = null;
        try {
            element = (SudokuComponent) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new CloneException();
        }
        element.fields = Arrays.asList(new SudokuField[size]);
        for (int i = 0; i < size; i++) {
            element.fields.set(i, new SudokuField(fields.get(i).getFieldValue()));
        }
        return element;
    }
}
