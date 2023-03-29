package org.example.javafx;

import javafx.application.Application;
import javafx.stage.Stage;

public class SudokuGame extends Application {
    @Override
    public void start(Stage stage) {
        MainWindow window = new MainWindow();
        window.show();
    }

    public static void main(String[] args) {
        launch();
    }
}