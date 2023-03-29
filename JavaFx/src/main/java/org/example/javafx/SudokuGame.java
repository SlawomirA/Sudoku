package org.example.javafx;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;



public class SudokuGame extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainWindow window = new MainWindow();
        window.show();
    }

    public static void main(String[] args) {
        launch();
    }
}