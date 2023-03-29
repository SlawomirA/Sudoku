package pl.sudokuboard;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pl.sudokuboard.exception.JdbcConnectionException;
import pl.sudokuboard.exception.JdbcCreateSchemaException;
import pl.sudokuboard.exception.JdbcLoadException;
import pl.sudokuboard.exception.JdbcNullBoardException;
import pl.sudokuboard.exception.JdbcQueryException;
import pl.sudokuboard.exception.JdbcSaveException;




public class JdbcSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {
    private Jdbc db;
    private final String tableName;
    private String parentname;

    public JdbcSudokuBoardDao(String tableName) {
        this(tableName, false);
    }

    public JdbcSudokuBoardDao(String tableName, Boolean inMemory) {
        this(tableName, "", inMemory);
    }

    public JdbcSudokuBoardDao(String tableName, String parentName, Boolean inMemory) {
        try {
            this.db = new Jdbc(inMemory);
        } catch (JdbcConnectionException | JdbcCreateSchemaException e) {
            e.printStackTrace();
        }
        this.tableName = tableName;
        this.parentname = parentName;
    }

    public SudokuBoard read() throws JdbcLoadException {
        SudokuBoard sudokuBoard;
        try {
            sudokuBoard = db.readSudokuBoard(tableName);
            parentname = db.getParentName(tableName);
        } catch (JdbcConnectionException | JdbcQueryException e) {
            throw new JdbcLoadException(e);
        }

        return sudokuBoard;
    }

    public SudokuBoard readParent() throws JdbcLoadException {
        SudokuBoard sudokuBoard;
        try {
            sudokuBoard = db.readSudokuBoard(parentname);
        } catch (JdbcConnectionException | JdbcQueryException e) {
            throw new JdbcLoadException(e);
        }

        return sudokuBoard;
    }

    public boolean write(final SudokuBoard sudokuBoard) throws JdbcSaveException {
        try {
            return db.writeSudokuBoard(sudokuBoard, tableName, parentname);
        } catch (JdbcNullBoardException | JdbcConnectionException | JdbcQueryException e) {
            throw new JdbcSaveException(e);
        }
    }

    public Map<Integer, String> getBoards() {
        try {
            return db.getSudokuBoardsList();
        } catch (JdbcConnectionException | JdbcQueryException e) {
            e.printStackTrace();
        }

        return new HashMap<>();
    }

    @Override
    public void close() throws SQLException {
        db.safelyCloseConnection();
    }
}
