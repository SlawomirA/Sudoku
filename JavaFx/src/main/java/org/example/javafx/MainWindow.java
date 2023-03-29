/**
 * Sample Skeleton for 'MainWindow.fxml' Controller Class
 */

package org.example.javafx;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pl.sudokuboard.GameDifficultyLevel;
import pl.sudokuboard.OutOfRangeException;
import pl.sudokuboard.logger.GlobalLogger;
import pl.sudokuboard.logger.I10N;


public class MainWindow {
    private Scene scene;
    private final Stage stage;
    private GameDifficultyLevel gameLevel = GameDifficultyLevel.EASY;
    private Locale locale;
    private ResourceBundle bundle;

    @FXML
    private ComboBox<String> levelComboBox;

    @FXML
    private ComboBox<String> languageComboBox;

    @FXML
    private Button gameStartButton;

    @FXML
    private Button authorsButton;

    public MainWindow() throws IOException {
        this.stage = new Stage();

        locale = new Locale("pl", "PL");
        bundle = ResourceBundle.getBundle("i18n.SudokuGame", locale);
        loadScene();
    }

    private void loadScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"), bundle);
        loader.setController(this);
        this.scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.getIcons().add(new Image("icon.png"));
        stage.setResizable(false);
        scene.getStylesheets().add("sample.css"); //dodawanie css do projektu
        stage.setTitle(bundle.getString("MainWindow.Title"));
    }

    @FXML
    // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        setData();
        setControlls();
    }

    private void setControlls() {
        levelComboBox.setOnAction(e -> {
            if (Objects.equals(levelComboBox.getValue(),
                    bundle.getString("MainWindow.Level.Easy"))) {
                gameLevel = GameDifficultyLevel.EASY;
            } else if (Objects.equals(levelComboBox.getValue(),
                    bundle.getString("MainWindow.Level.Normal"))) {
                gameLevel = GameDifficultyLevel.NORMAL;
            } else if (Objects.equals(levelComboBox.getValue(),
                    bundle.getString("MainWindow.Level.Hard"))) {
                gameLevel = GameDifficultyLevel.HARD;
            } else if (Objects.equals(levelComboBox.getValue(),
                    bundle.getString("MainWindow.Level.Hardcore"))) {
                gameLevel = GameDifficultyLevel.HARDCORE;
            }
        });
        languageComboBox.setOnAction(e -> {
            if (Objects.equals(languageComboBox.getValue(),
                    bundle.getString("MainWindow.Language.PL"))) {
                locale = new Locale("pl", "PL");
                bundle = ResourceBundle.getBundle("i18n.SudokuGame", locale);
                GlobalLogger.changeLanguage(locale);
                try {
                    loadScene();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (Objects.equals(languageComboBox.getValue(),
                    bundle.getString("MainWindow.Language.EN"))) {
                locale = new Locale("en", "US");
                bundle = ResourceBundle.getBundle("i18n.SudokuGame", locale);
                GlobalLogger.changeLanguage(locale);
                try {
                    loadScene();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        gameStartButton.setOnAction(event -> {
            try {
                openBoard();
            } catch (OutOfRangeException e) {
                throw new RuntimeException(e);
            }
        });
        authorsButton.setOnAction(event -> showAuthors());
    }

    private void openBoard() throws OutOfRangeException {
        try {
            SudokuBoardView boardView = new SudokuBoardView(this, gameLevel);
            boardView.show();
        } catch (CloneNotSupportedException e) {
            GlobalLogger.warn(I10N.CLONE_NOT_SUPPORTED, SudokuBoardView.class.getName());
        }
    }


    public void setData() {
        levelComboBox.getItems().setAll(
                bundle.getString("MainWindow.Level.Easy"),
                bundle.getString("MainWindow.Level.Normal"),
                bundle.getString("MainWindow.Level.Hard"),
                bundle.getString("MainWindow.Level.Hardcore")
        );
        languageComboBox.getItems().setAll(
                bundle.getString("MainWindow.Language.PL"),
                bundle.getString("MainWindow.Language.EN")
        );
    }


    public void show() {
        stage.showAndWait();
    }

    ResourceBundle getBundle() {
        return bundle;
    }

    private void showAuthors() {
        AuthorsList authorsList = new AuthorsList();
        String authors =
                String.format("%s, %s", authorsList.getString("1"), authorsList.getString("2"));
        Alert alert =
                new Alert(Alert.AlertType.INFORMATION,bundle.getString("MainWindow.Copyright"));
        alert.setTitle(bundle.getString("MainWindow.Authors"));
        alert.setHeaderText(authors);
        alert.showAndWait();
    }
}
