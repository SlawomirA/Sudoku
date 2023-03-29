package pl.sudokuboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameLevelTest {

    @Test
    void setLevel() throws OutOfRangeException {
        SudokuBoard f1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard f2 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard f3 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard f4 = new SudokuBoard(new BacktrackingSudokuSolver());
        f1.solveGame();
        f2.solveGame();
        f4.solveGame();

        assertTrue(f1.isSolved());
        GameLevel.setLevel(f1, GameDifficultyLevel.EASY);
        assertFalse(f1.isSolved());

        assertTrue(f2.isSolved());
        GameLevel.setLevel(f2, GameDifficultyLevel.NORMAL);
        assertFalse(f2.isSolved());

        assertFalse(f3.isSolved());
        GameLevel.setLevel(f2, GameDifficultyLevel.HARD);
        assertFalse(f3.isSolved());

        assertTrue(f4.isSolved());
        GameLevel.setLevel(f4, GameDifficultyLevel.HARDCORE);
        assertFalse(f4.isSolved());

        GameLevel l = new GameLevel();
    }
}