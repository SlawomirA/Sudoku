package pl.sudokuboard;

import pl.sudokuboard.exception.DaoException;
import pl.sudokuboard.exception.WrongClassException;

/**
 * Interfejs umożliwiający zapis i odczyt konkretnego obiektu klasy T.
 * @param <T> Typ klasy której obiekt ma być poddany serializacji.
 */
public interface Dao<T> extends AutoCloseable {

    /**
     * Deserializuje obiekt.
     * @return Dana klasa.
     * @throws DaoException wyjatek
     * @throws WrongClassException wyjatek
     */
    T read() throws DaoException, WrongClassException;



    /**
     * Serializuje obiekt.
     * @param obj Dany obiekt do zapisania.
     * @return Prawda/Fałsz czy się udało
     * @throws DaoException Input output exception.
     */
    boolean write(T obj) throws DaoException;
}
//Abstract factory i FACTORY METHOD