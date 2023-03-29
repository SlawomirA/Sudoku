package pl.sudokuboard;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import pl.sudokuboard.exception.InvalidIndex;
import pl.sudokuboard.exception.JdbcConnectionException;
import pl.sudokuboard.exception.JdbcCreateSchemaException;
import pl.sudokuboard.exception.JdbcNullBoardException;
import pl.sudokuboard.exception.JdbcQueryException;
import pl.sudokuboard.logger.GlobalLogger;
import pl.sudokuboard.logger.I10N;



public class Jdbc {
    private String jdbcUrl;
    private Connection conn = null;
    private DatabaseMetaData meta = null;

    public Jdbc(Boolean inMemory) throws JdbcConnectionException, JdbcCreateSchemaException {
        connect(inMemory);
        if (conn != null) {
            createSchema();
        }
    }

    public Jdbc() throws JdbcConnectionException, JdbcCreateSchemaException {
        this(false);
    }

    public boolean writeSudokuBoard(SudokuBoard board, String boardName)
            throws JdbcNullBoardException, JdbcConnectionException, JdbcQueryException {
        return writeSudokuBoard(board, boardName, "");
    }

    public boolean writeSudokuBoard(SudokuBoard board, String boardName, String parentName)
            throws JdbcNullBoardException, JdbcConnectionException, JdbcQueryException {
        if (board == null) {
            throw new JdbcNullBoardException(jdbcUrl, boardName);
        }

        int boardID;
        int affectedRows;

        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "INSERT INTO BOARDS(NAME, PARENT_NAME, IS_PARENT) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, boardName);
            preparedStatement.setString(2, parentName);
            preparedStatement.setBoolean(3, parentName.isEmpty());
            affectedRows = preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    boardID = generatedKeys.getInt(1);
                } else {
                    throw new JdbcQueryException(jdbcUrl,
                            GlobalLogger.message(I10N.DB_INSERT_BOARD_ERROR));
                }
            } catch (SQLException e) {
                throw new JdbcConnectionException(e, jdbcUrl);
            }

        } catch (SQLException e) {
            throw new JdbcConnectionException(e, jdbcUrl);
        }
        if (affectedRows == 0) {
            throw new JdbcQueryException(jdbcUrl, GlobalLogger.message(I10N.DB_INSERT_BOARD_ERROR));
        }

        try (PreparedStatement statement =
                     conn.prepareStatement("INSERT INTO FIELDS(BOARD, ROW, COL, VALUE) "
                + "VALUES (?, ?, ?, ?)");) {
            statement.setInt(1, boardID);

            for (int row = 0; row < board.getBoardSize(); row++) {
                for (int col = 0; col < board.getBoardSize(); col++) {
                    statement.setInt(2, row);
                    statement.setInt(3, col);
                    statement.setInt(4, board.get(row, col));
                    statement.addBatch();
                }
            }
            int[] numUpdates = statement.executeBatch();
            for (int numUpdate : numUpdates) {
                if (numUpdate < 0) {
                    throw new JdbcQueryException(jdbcUrl,
                            GlobalLogger.message(I10N.DB_INSERT_FIELD_ERROR));
                }
            }
        } catch (SQLException | InvalidIndex e) {
            throw new JdbcConnectionException(e, jdbcUrl);
        }

        try {
            conn.commit();
        } catch (SQLException e) {
            throw new JdbcConnectionException(e, jdbcUrl);
        }

        return true;
    }

    public SudokuBoard readSudokuBoard(String boardName)
            throws JdbcConnectionException, JdbcQueryException {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());

        int boardID;

        try (PreparedStatement statement =
                     conn.prepareStatement("SELECT ID FROM BOARDS WHERE NAME=?")) {
            statement.setString(1, boardName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    boardID = resultSet.getInt(1);
                } else {
                    throw new JdbcQueryException(jdbcUrl,
                            GlobalLogger.message(I10N.DB_SELECT_BOARD_ERROR));
                }
            } catch (SQLException e) {
                throw new JdbcConnectionException(e, jdbcUrl);
            }

        } catch (SQLException e) {
            throw new JdbcConnectionException(e, jdbcUrl);
        }

        try (PreparedStatement statement =
                    conn.prepareStatement("SELECT ROW, COL, VALUE FROM FIELDS WHERE BOARD=?")) {
            statement.setInt(1, boardID);
            try (ResultSet resultSet = statement.executeQuery()) {
                int row;
                int col;
                int value;
                while (resultSet.next()) {
                    row = resultSet.getInt(1);
                    col = resultSet.getInt(2);
                    value = resultSet.getInt(3);
                    board.set(row, col, value);
                }
                conn.commit();
            } catch (InvalidIndex | SQLException e) {
                throw new JdbcQueryException(jdbcUrl,
                        GlobalLogger.message(I10N.DB_SELECT_FIELD_ERROR));
            }

        } catch (SQLException e) {
            throw new JdbcConnectionException(e, jdbcUrl);
        }

        return board;
    }

    public String getParentName(String boardName) throws JdbcConnectionException {
        String parentName;

        try (PreparedStatement statement =
                     conn.prepareStatement("SELECT PARENT_NAME FROM BOARDS WHERE NAME=?")) {
            statement.setString(1, boardName);
            try (ResultSet resultSet = statement.executeQuery();) {
                if (resultSet.next()) {
                    parentName = resultSet.getString(1);
                } else {
                    throw new JdbcQueryException(jdbcUrl,
                            GlobalLogger.message(I10N.DB_SELECT_BOARD_ERROR));
                }
            } catch (SQLException e) {
                throw new JdbcConnectionException(e, jdbcUrl);
            }
        } catch (SQLException e) {
            throw new JdbcConnectionException(e, jdbcUrl);
        }

        try {
            conn.commit();
        } catch (SQLException e) {
            throw new JdbcConnectionException(e, jdbcUrl);
        }

        return parentName;
    }

    public Map<Integer, String> getSudokuBoardsList()
            throws JdbcConnectionException, JdbcQueryException {
        Map<Integer, String> list = new HashMap<>();

        try (PreparedStatement statement =
                     conn.prepareStatement("SELECT * FROM BOARDS WHERE IS_PARENT = FALSE")) {
            try (ResultSet resultSet = statement.executeQuery();) {
                int id;
                String name;
                while (resultSet.next()) {
                    id = resultSet.getInt(1);
                    name = resultSet.getString(2);
                    list.put(id, name);
                }
            } catch (SQLException e) {
                throw new JdbcQueryException(jdbcUrl,
                        GlobalLogger.message(I10N.DB_SELECT_BOARDS_ERROR));
            }
        } catch (SQLException e) {
            throw new JdbcConnectionException(e, jdbcUrl);
        }

        try {
            conn.commit();
        } catch (SQLException e) {
            throw new JdbcConnectionException(e, jdbcUrl);
        }

        return list;
    }

    private void connect(Boolean inMemory) throws JdbcConnectionException {
        String userDir = System.getProperty("user.dir").replace("\\", "\\\\");
        if (inMemory) {
            jdbcUrl = "jdbc:derby:memory:" + userDir + "\\Database\\SudokuGame" + ";create=true";
        } else {
            jdbcUrl = "jdbc:derby:" + userDir + "\\Database\\SudokuGame" + ";create=true";
        }
        try {
            conn = DriverManager.getConnection(jdbcUrl);
            conn.setAutoCommit(false);
            meta = conn.getMetaData();
        } catch (SQLException e) {
            throw new JdbcConnectionException(e, jdbcUrl);
        }
    }

    private void createSchema() throws JdbcConnectionException, JdbcCreateSchemaException {
        Statement stmt;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            throw new JdbcConnectionException(e, jdbcUrl);
        }

        if (tableDontExists("BOARDS")) {
            try {
                stmt.executeUpdate(
                        "Create table BOARDS (ID int not null primary key GENERATED ALWAYS "
                                + "AS IDENTITY (START WITH 1, INCREMENT BY 1), NAME varchar(30), "
                                + "PARENT_NAME varchar(30), IS_PARENT boolean default FALSE)"
                );
            } catch (SQLException e) {
                throw new JdbcCreateSchemaException(e, jdbcUrl);
            }
        }

        if (tableDontExists("FIELDS")) {
            try {
                stmt.executeUpdate(
                        "Create table FIELDS (ID int not null primary key GENERATED ALWAYS AS"
                                + " IDENTITY (START " + "WITH 1, INCREMENT BY 1), "
                                + "BOARD int references BOARDS(ID), ROW int, COL int, VALUE int)"
                );
            } catch (SQLException e) {
                throw new JdbcCreateSchemaException(e, jdbcUrl);
            }
        }

        try {
            conn.commit();
        } catch (SQLException e) {
            throw new JdbcConnectionException(e, jdbcUrl);
        }
    }

    public void safelyCloseConnection() throws SQLException {
        //W przypadku wyjątków transakcje nie są zatwierdzane więc trzeba dać rollback by je wycofać
        conn.rollback();
        conn.close();
    }

    public boolean tableDontExists(String tableName) throws JdbcConnectionException {
        try {
            ResultSet resultSet = meta.getTables(null, null, tableName, new String[]{"TABLE"});
            return !resultSet.next();
        } catch (SQLException e) {
            throw new JdbcConnectionException(e, jdbcUrl);
        }
    }

}

