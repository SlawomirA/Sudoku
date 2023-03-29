/**
 * Sample Skeleton for 'SudokuBoardView.fxml' Controller Class
 */

package org.example.javafx;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pl.sudokuboard.BacktrackingSudokuSolver;
import pl.sudokuboard.GameDifficultyLevel;
import pl.sudokuboard.GameLevel;
import pl.sudokuboard.OutOfRangeException;
import pl.sudokuboard.SudokuBoard;

public class SudokuBoardView {
    private final Stage stage;
    private final SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());


    @FXML // fx:id="leftFieldsLabel"
    private Label leftFieldsLabel; // Value injected by FXMLLoader

    @FXML // fx:id="sudokuFields"
    private GridPane sudokuFields; // Value injected by FXMLLoader

    public SudokuBoardView(GameDifficultyLevel gameLevel) throws OutOfRangeException {

        this.stage = new Stage();

        board.solveGame();
        GameLevel.setLevel(board, gameLevel);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SudokuBoardView.fxml"));
        loader.setController(this);
        try {
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (gameLevel == GameDifficultyLevel.EASY) {
            stage.setTitle("Sudoku - Poziom Łatwy");
            leftFieldsLabel.setText("10");
        } else if (gameLevel == GameDifficultyLevel.NORMAL) {
            stage.setTitle("Sudoku - Poziom Normalny");
            leftFieldsLabel.setText("20");
        } else if (gameLevel == GameDifficultyLevel.HARD) {
            stage.setTitle("Sudoku - Poziom Trudny");
            leftFieldsLabel.setText("30");
        } else if (gameLevel == GameDifficultyLevel.HARDCORE) {
            stage.setTitle("Sudoku - Poziom Niemożliwy");
            leftFieldsLabel.setText("40");
        }
    }

    @FXML
    // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws OutOfRangeException {
        fillBoard();
    }

    private void fillBoard() throws OutOfRangeException {
        sudokuFields.getChildren().clear();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                TextField field = new TextField();
                field.setAlignment(Pos.CENTER);
                field.setFont(new Font("Verdana", 20));
                field.setPrefSize(50, 50);
                field.setText(String.valueOf(board.get(row, col)));
                if (board.get(row, col) != 0) {
                    field.setEditable(false);
                    field.setStyle("-fx-background-color: #f7f71f;-fx-border-style: solid;");
                } else {
                    field.setStyle("-fx-border-style: solid;");
                    int finalRow = row;
                    int finalCol = col;
                    field.textProperty().addListener((observable, oldValue, newValue) -> {
                        if (!newValue.matches("[0-9]|^$")) {
                            field.setText("0");
                        } else if (newValue.matches("[1-9]")) {
                            try {
                                if (board.correctNumber(finalRow, finalCol,
                                        Integer.parseInt(newValue))) {
                                    field.setStyle("-fx-background-color: #f7f71f"
                                            + ";-fx-border-style: solid;"
                                            + "-fx-border-color: red");
                                    leftFieldsLabel.setText(String.valueOf(
                                            Integer.parseInt(leftFieldsLabel.getText()) - 1));
                                }
                            } catch (OutOfRangeException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                board.set(finalRow, finalCol, Integer.parseInt(newValue));
                            } catch (OutOfRangeException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                }

                //boardFields[row][col] = field;
                sudokuFields.add(field, row, col);
            }
        }

    }

    public void show() {
        stage.showAndWait();
    }
}
