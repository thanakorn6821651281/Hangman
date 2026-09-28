package Domain;

public class ScoreEntry {
    private final String name;
    private final int score;
    private final Difficulty difficulty;

    public ScoreEntry(String name, int score, Difficulty difficulty) {
        this.name = name;
        this.score = score;
        this.difficulty = difficulty;
    }
    public String getName() { return name; }
    public int getScore() { return score; }
    public Difficulty getDifficulty() { return difficulty; }
}
