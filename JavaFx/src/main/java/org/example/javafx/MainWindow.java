/**
 * Sample Skeleton for 'MainWindow.fxml' Controller Class
 */

package org.example.javafx;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainWindow {
    private final Stage stage;

    public MainWindow() {
        this.stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        loader.setController(this);
        try {
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Gra Sudoku");
    }

    @FXML
    // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        setData();
        setControlls();
    }

    private void setControlls() {
        // action event
    }

    public void setData() {
    }

    public void show() {
        stage.showAndWait();
    }
}
