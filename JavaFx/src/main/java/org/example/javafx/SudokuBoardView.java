/**
 * Sample Skeleton for 'SudokuBoardView.fxml' Controller Class
 */

package org.example.javafx;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.filechooser.FileSystemView;
import pl.sudokuboard.BacktrackingSudokuSolver;
import pl.sudokuboard.FileSudokuBoardDecoratorDao;
import pl.sudokuboard.GameDifficultyLevel;
import pl.sudokuboard.GameLevel;
import pl.sudokuboard.JdbcSudokuBoardDao;
import pl.sudokuboard.OutOfRangeException;
import pl.sudokuboard.SudokuBoard;
import pl.sudokuboard.SudokuBoardDaoFactory;
import pl.sudokuboard.SudokuBoardDecorator;
import pl.sudokuboard.SudokuBoardDecoratorDaoFactory;
import pl.sudokuboard.SudokuBoardFactory;
import pl.sudokuboard.SudokuField;
import pl.sudokuboard.exception.CloneException;
import pl.sudokuboard.exception.FileOperationException;
import pl.sudokuboard.exception.JdbcLoadException;
import pl.sudokuboard.exception.JdbcSaveException;
import pl.sudokuboard.logger.GlobalLogger;
import pl.sudokuboard.logger.I10N;

public class SudokuBoardView {
    private final Stage stage;

    private final MainWindow parent;
    private SudokuBoardDecorator board;
    private final TextField[][] fields = new TextField[9][9];
    private boolean insertMode = true;
    private final ResourceBundle bundle;
    private final GameDifficultyLevel gameLevel;

    @FXML // fx:id="sudokuFields"
    private GridPane sudokuFields; // Value injected by FXMLLoader

    @FXML
    private Button loadBoardButton;

    @FXML
    private Button saveBoardButton;

    @FXML
    private Button restoreBoardButton;

    public SudokuBoardView(MainWindow mainWindow, GameDifficultyLevel level)
            throws OutOfRangeException, CloneNotSupportedException {

        gameLevel = level;
        parent = mainWindow;
        bundle = parent.getBundle();
        this.stage = new Stage();

        GlobalLogger.info(I10N.NEW_GAME_CREATED, level.toString());
        stage.setResizable(false);
        stage.getIcons().add(new Image("icon.png"));


        SudokuBoard startingBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        startingBoard.solveGame();

        GameLevel.setLevel(startingBoard, gameLevel);
        GameLevel.setLevel(startingBoard, gameLevel);
        try {
            board = new SudokuBoardDecorator(startingBoard);
        } catch (CloneNotSupportedException e) {
            throw new CloneException();
        }



        loadScene();
    }

    private void setControls() {
        saveBoardButton.setOnAction(event -> saveBoard());
        loadBoardButton.setOnAction(event -> {
            try {
                loadBoard();
            } catch (OutOfRangeException e) {
                throw new RuntimeException(e);
            }
        });
        restoreBoardButton.setOnAction(event -> {
            try {
                restoreBoard();
            } catch (OutOfRangeException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void restoreBoard() throws OutOfRangeException {
        insertMode = true;
        board.restoreBoard();
        fillBoard();
        insertMode = false;
    }

    private void loadScene() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SudokuBoardView.fxml"), bundle);
        loader.setController(this);
        try {
            stage.setScene(new Scene(loader.load()));
            stage.getScene().getStylesheets().add("sample.css");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (gameLevel == GameDifficultyLevel.EASY) {
            stage.setTitle(bundle.getString("SudokuBoardView.Title.Easy"));
        } else if (gameLevel == GameDifficultyLevel.NORMAL) {
            stage.setTitle(bundle.getString("SudokuBoardView.Title.Normal"));
        } else if (gameLevel == GameDifficultyLevel.HARD) {
            stage.setTitle(bundle.getString("SudokuBoardView.Title.Hard"));
        } else if (gameLevel == GameDifficultyLevel.HARDCORE) {
            stage.setTitle(bundle.getString("SudokuBoardView.Title.Hardcore"));
        }
    }

    @FXML
    // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws OutOfRangeException {
        setControls();
        fillBoard();
    }

    private void fillBoard() throws OutOfRangeException {
        sudokuFields.getChildren().clear();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                fields[row][col] = new TextField();
                setBoardFieldBaseStyle(fields[row][col]);
                board.addFieldPropertyChangeEvent(row, col, getSudokuFieldPcl(fields[row][col]));
                fields[row][col].setText(String.valueOf(board.get(row, col)));
                if (board.get(row, col) != 0) {
                    setBoardFieldSolvedStyle(fields[row][col]);
                } else {
                    fields[row][col].textProperty().addListener(getChangeListener(row, col));
                }
                sudokuFields.add(fields[row][col], col, row);
            }
        }
        insertMode = false;
    }

    private void setBoardFieldBaseStyle(TextField field) {
        field.setAlignment(Pos.CENTER);
        field.setFont(new Font("Verdana", 20));
        field.setPrefSize(50, 50);
        field.setStyle("-fx-border-style: solid;");
        field.setEditable(true);
    }

    private void setBoardFieldSolvedStyle(TextField field) {
        field.setEditable(false);
        field.setStyle("-fx-background-color: #81c483;-fx-border-style: solid;");
    }

    private void setBoardFieldUSerSolvedStyle(TextField field) {
        field.setStyle("-fx-background-color: "
                + "#81c483;-fx-border-style: solid;-fx-border-color: red");
        field.setEditable(true);
    }

    private PropertyChangeListener getSudokuFieldPcl(TextField field) {
        return e -> {
            SudokuField sudokuField = (SudokuField) e.getNewValue();
            field.setText(String.valueOf(sudokuField.getFieldValue()));
            if (insertMode && sudokuField.getFieldValue() != 0) {
                setBoardFieldSolvedStyle(field);
            } else if (sudokuField.getFieldValue() != 0) {
                setBoardFieldUSerSolvedStyle(field);
            } else {
                setBoardFieldBaseStyle(field);
            }
        };
    }

    private ChangeListener<? super String> getChangeListener(int finalRow, int finalCol) {
        return (observable, oldValue, newValue) -> {
            if (oldValue.equals(newValue)) {
                return;
            }
            if (!newValue.matches("[0-9]|^$")) {
                fields[finalRow][finalCol].setText("0");
            } else if (newValue.matches("[1-9]")) {
                if (board.correctNumber(finalRow, finalCol, Integer.parseInt(newValue))) {
                    setBoardFieldUSerSolvedStyle(fields[finalRow][finalCol]);
                }
                try {
                    board.set(finalRow, finalCol, Integer.parseInt(newValue));
                } catch (OutOfRangeException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    private FileChooser createSudokuBoardFileChooser() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("SudokuBoard (*.SudokuBoard)", "*.SudokuBoard");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
        return fileChooser;
    }

    private void saveBoard() {
        String location = getLocationResult("SudokuBoardView.SaveDialog.Title",
                "SudokuBoardView.SaveDialog.Header", "SudokuBoardView.SaveDialog");

        if (location.equals("SudokuBoardView.Database")) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle(bundle.getString("SudokuBoardView.SaveDialog.Database.Title"));
            dialog.setHeaderText(bundle.getString("SudokuBoardView.SaveDialog.Database.Header"));
            dialog.setContentText(bundle.getString("SudokuBoardView.SaveDialog.Database"));

            Optional<String> nameResult = dialog.showAndWait();
            if (nameResult.isPresent()) {
                SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
                String name = dialog.getEditor().textProperty().get();
                try (JdbcSudokuBoardDao dao =
                             (JdbcSudokuBoardDao) factory.getJdbcDao(name, board.getParentName())) {
                    dao.write(board);
                } catch (SQLException | JdbcSaveException e) {
                    e.printStackTrace();
                }

                try (JdbcSudokuBoardDao dao =
                             (JdbcSudokuBoardDao) factory.getJdbcDao(board.getParentName())) {
                    dao.write(board.getParent());
                } catch (SQLException | JdbcSaveException e) {
                    e.printStackTrace();
                }
            }
        } else if (location.equals("SudokuBoardView.File")) {
            FileChooser fileChooser = createSudokuBoardFileChooser();

            //Show save file dialog
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                try {
                    board.save(file.getAbsolutePath());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadBoard() throws OutOfRangeException {
        String location = getLocationResult("SudokuBoardView.LoadDialog.Title",
                "SudokuBoardView.LoadDialog.Header", "SudokuBoardView.LoadDialog");

        if (location.equals("SudokuBoardView.Database")) {
            SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
            Map<Integer, String> boards;
            try (JdbcSudokuBoardDao dao = (JdbcSudokuBoardDao) factory.getJdbcDao("")) {
                boards = dao.getBoards();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            List<String> choices = new ArrayList<>();
            for (Integer key : boards.keySet()) {
                choices.add(boards.get(key));
            }

            if (choices.size() > 0) {
                ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
                dialog.setTitle(bundle.getString("SudokuBoardView.LoadDialog.Database.Title"));
                dialog.setHeaderText(
                        bundle.getString("SudokuBoardView.LoadDialog.Database.Header"));
                dialog.setContentText(bundle.getString("SudokuBoardView.LoadDialog.Database"));

                Optional<String> result = dialog.showAndWait();
                result.ifPresent(boardName -> {
                    try (JdbcSudokuBoardDao dao =
                                 (JdbcSudokuBoardDao) factory.getJdbcDao(boardName)) {
                        SudokuBoard b = dao.read();
                        SudokuBoard parent = dao.readParent();

                        board = new SudokuBoardDecorator(b, parent);
                        fillBoard();
                    } catch (SQLException | JdbcLoadException | CloneNotSupportedException e) {
                        e.printStackTrace();
                    } catch (OutOfRangeException e) {
                        throw new RuntimeException(e);
                    }
                });
            } else {
                Alert alert =
                        new Alert(Alert.AlertType.INFORMATION,
                                bundle.getString("SudokuBoardView.LoadDialog.Empty"));
                alert.setTitle(bundle.getString("SudokuBoardView.LoadDialog.Title"));
                alert.setHeaderText("");
                alert.showAndWait();
            }
        } else if (location.equals("SudokuBoardView.File")) {
            FileChooser fileChooser = createSudokuBoardFileChooser();

            //Show open file dialog
            File file = fileChooser.showOpenDialog(stage);

            if (file != null) {
                SudokuBoardFactory<SudokuBoardDecorator> factory =
                        new SudokuBoardDecoratorDaoFactory();
                FileSudokuBoardDecoratorDao dao =
                        (FileSudokuBoardDecoratorDao) factory.getFileDao(file.getAbsolutePath());
                try {
                    board = dao.read();
                    fillBoard();
                } catch (FileOperationException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void show() {
        stage.showAndWait();
    }

    private String getLocationResult(String title, String header, String dialog) {
        ButtonType buttonDatabase = new ButtonType(bundle.getString("SudokuBoardView.Database"));
        ButtonType buttonFile = new ButtonType(bundle.getString("SudokuBoardView.File"));

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, bundle.getString(dialog),
                buttonDatabase, buttonFile, ButtonType.CANCEL);
        alert.setTitle(bundle.getString(title));
        alert.setHeaderText(bundle.getString(header));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == buttonDatabase) {
                return "SudokuBoardView.Database";
            } else if (result.get() == buttonFile) {
                return "SudokuBoardView.File";
            }
        }
        return "";
    }
}
