package pl.sudokuboard;

import java.util.Random;

public class GameLevel {


    public static void setLevel(SudokuBoard board, GameDifficultyLevel level)
            throws OutOfRangeException {
        if (!board.isSolved()) {
            board.solveGame();
        }

        if (level == GameDifficultyLevel.EASY) {
            eraseFields(board, 10);
        } else if (level == GameDifficultyLevel.NORMAL) {
            eraseFields(board, 20);
        } else if (level == GameDifficultyLevel.HARD) {
            eraseFields(board, 30);
        } else {
            eraseFields(board, 40);
        }
    }

    private static void eraseFields(SudokuBoard board, int amount) throws OutOfRangeException {
        Random rand = new Random();
        int row = rand.nextInt(0, 9);
        int col = rand.nextInt(0, 9);
        for (int i = 0; i < amount; i++) {
            while (board.get(row, col) == 0) {
                row = rand.nextInt(0, 9);
                col = rand.nextInt(0, 9);
            }
            board.set(row, col, 0);
        }
    }
}
