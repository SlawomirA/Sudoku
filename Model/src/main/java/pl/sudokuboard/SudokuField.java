package pl.sudokuboard;


import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Klasa odzwierciedlająca wartość pojedynczego pola.
 */
public class SudokuField implements Serializable,Cloneable, Comparable<SudokuField> {

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

    /**
     * Zwraca obiekt w postaci łańcucha znaków.
     *
     * @return Obiekt w postaci łańcucha znaków.
     */
    public final String toString() {
        return new ToStringBuilder(this)
                .append("value", val)
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
        return new HashCodeBuilder(17, 77).append(val).toHashCode();
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

        SudokuField sb = (SudokuField) obj;
        return new EqualsBuilder()
                .append(val, sb.val)
                .isEquals();
    }

    @Override
    public SudokuField clone() throws CloneNotSupportedException {
        return (SudokuField) super.clone();
    }

    @Override
    public int compareTo(SudokuField o) throws NullPointerException {
        if (o == null) {
            throw new NullPointerException();
        }
        return val - o.val;
    }
}
