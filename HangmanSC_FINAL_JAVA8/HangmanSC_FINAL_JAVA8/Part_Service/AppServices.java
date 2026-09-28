package Part_Service;

import Part_Data.CsvScoreRepository;
import Part_Data.CsvWordRepository;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class AppServices {
    private static final Path DATA = Paths.get("data", "words");
    private static final WordService WORDS = new WordService(new CsvWordRepository(DATA));
    private static final GameService GAME = new GameService(WORDS);
    private static final LeaderboardService LEADERBOARD =
            new LeaderboardService(new CsvScoreRepository(Paths.get("data", "players", "players.csv")));

    private AppServices() {}
    public static GameService game() { return GAME; }
    public static LeaderboardService leaderboard() { return LEADERBOARD; }
}
