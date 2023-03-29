package pl.sudokuboard;

import java.io.IOException;

/**
 * Interfejs umożliwiający zapis i odczyt konkretnego obiektu klasy T.
 * @param <T> Typ klasy której obiekt ma być poddany serializacji.
 */
public interface Dao<T> extends AutoCloseable {

    /**
     * Deserializuje obiekt.
     * @return Dana klasa.
     * @throws IOException Input output exception
     * @throws ClassNotFoundException Class is not found exception.
     */
    T read() throws IOException, ClassNotFoundException;

    /**
     * Serializuje obiekt.
     * @param obj Dany obiekt do zapisania.
     * @return Prawda/Fałsz czy się udało
     * @throws IOException Input output exception.
     */
    boolean write(T obj) throws IOException;
}
//Abstract factory i FACTORY METHOD