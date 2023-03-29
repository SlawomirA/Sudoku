/**
 * Sample Skeleton for 'MainWindow.fxml' Controller Class
 */

package org.example.javafx;

import java.io.IOException;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pl.sudokuboard.GameDifficultyLevel;
import pl.sudokuboard.OutOfRangeException;


public class MainWindow {
    private final Stage stage;
    private Scene scene;
    private GameDifficultyLevel gameLevel = GameDifficultyLevel.EASY;
    @FXML
    private Label title;
    @FXML
    private ComboBox<String> levelComboBox;

    @FXML
    private Button gameStartButton;

    public MainWindow() throws IOException {
        this.stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        loader.setController(this);
        this.scene = new Scene(loader.load());
        stage.setScene(scene);
        scene.getStylesheets().add("sample.css");                   //dodawanie css do projektu
        stage.setTitle("Sudoku Master");



    }

    @FXML
    // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        setData();
        setControlls();
    }

    private void setControlls() {
        // action event
        EventHandler<ActionEvent> cbevent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (Objects.equals(levelComboBox.getValue(), "Łatwy")) {
                    gameLevel = GameDifficultyLevel.EASY;
                } else if (Objects.equals(levelComboBox.getValue(), "Normalny")) {
                    gameLevel = GameDifficultyLevel.NORMAL;
                } else if (Objects.equals(levelComboBox.getValue(), "Trudny")) {
                    gameLevel = GameDifficultyLevel.HARD;
                } else if (Objects.equals(levelComboBox.getValue(), "Niemożliwy")) {
                    gameLevel = GameDifficultyLevel.HARDCORE;
                }
            }
        };

        levelComboBox.setOnAction(cbevent);
        gameStartButton.setOnAction(event -> {
            try {
                openBoard();
            } catch (OutOfRangeException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void openBoard() throws OutOfRangeException {
        SudokuBoardView boardView = new SudokuBoardView(/*this,*/ gameLevel);
        boardView.show();
    }


    public void setData() {
        //Set Level Box
        levelComboBox.getItems().clear();

        levelComboBox.getItems().addAll(
                "Łatwy",
                "Normalny",
                "Trudny",
                "Niemożliwy");
        levelComboBox.getSelectionModel().select(0);

        //Set Title
        title.setText("Sudoku Master");                 //Useless, na potem
    }


    public void show() {
        stage.showAndWait();
    }
}
