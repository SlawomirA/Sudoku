package pl.sudokuboard;

/**
 * Wyjątek poza zakresem jest używany,
 * gdy wartość/index wybiegnie poza zakres tablicy/ustalony zakres.
 */
public class OutOfRangeException extends Exception {
    public OutOfRangeException(String information) {
        super(information);
    }
}
