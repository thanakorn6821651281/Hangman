package Part_Service;

import Domain.Difficulty;
import Domain.GameStatus;
import Domain.HangmanGame;
import Domain.Word;

public class GameService {
    private final WordService wordService;

    public GameService(WordService wordService) { this.wordService = wordService; }

    public HangmanGame newGame(Difficulty difficulty) {
        Word word = wordService.randomWord(difficulty);
        return new HangmanGame(word, difficulty);
    }

    public HangmanGame nextGame(Difficulty difficulty, String previousAnswer) {
        Word word = wordService.randomWord(difficulty, previousAnswer);
        return new HangmanGame(word, difficulty);
    }

    public int score(HangmanGame game) {
        if (game.getStatus() != GameStatus.WON) return 0;
        int base;
        switch (game.getDifficulty()) {
            case EASY: base = 10; break;
            case MEDIUM: base = 20; break;
            default: base = 30; break;
        }
        return Math.max(1, base - game.getWrong() * 2);
    }
}
