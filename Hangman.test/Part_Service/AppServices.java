package Part_Service;

import Part_Data.CsvScoreRepository;
import Part_Data.CsvWordRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Shared application services and data location.
 *
 * The original project assumed the JVM working directory was exactly the
 * project folder. VS Code can launch a Java file with the workspace root as
 * the working directory, which made the game fail as soon as a difficulty
 * was selected because data/words/*.csv could not be found.
 *
 * This class now locates the data folder automatically, so the game can be
 * started from either the project folder or its parent workspace folder.
 */
public final class AppServices {
    private static final Path DATA_ROOT = locateDataRoot();
    private static final Path DATA_WORDS = DATA_ROOT.resolve("words");
    private static final Path DATA_PLAYERS = DATA_ROOT.resolve("players").resolve("players.csv");

    private static final WordService WORDS = new WordService(new CsvWordRepository(DATA_WORDS));
    private static final GameService GAME = new GameService(WORDS);
    private static final LeaderboardService LEADERBOARD =
            new LeaderboardService(new CsvScoreRepository(DATA_PLAYERS));

    private AppServices() {}

    public static GameService game() { return GAME; }
    public static LeaderboardService leaderboard() { return LEADERBOARD; }

    private static Path locateDataRoot() {
        Path workingDir = Paths.get(System.getProperty("user.dir")).toAbsolutePath().normalize();

        // Check the current folder and its parents first. This also covers
        // launches started from a source subfolder.
        for (Path p = workingDir; p != null; p = p.getParent()) {
            Path direct = p.resolve("data");
            if (isDataRoot(direct)) return direct;

            // Common VS Code case: workspace opened one folder above the project.
            Path nested = p.resolve("HangmanSC_FINAL_JAVA8_FIXED2").resolve("data");
            if (isDataRoot(nested)) return nested;
        }

        // Generic fallback: locate a data folder a few levels below the
        // current workspace. This keeps the app working if the project folder
        // is renamed or nested inside another workspace directory.
        try (Stream<Path> stream = Files.walk(workingDir, 4)) {
            Path found = stream
                    .filter(Files::isDirectory)
                    .map(p -> p.resolve("data"))
                    .filter(AppServices::isDataRoot)
                    .findFirst()
                    .orElse(null);
            if (found != null) return found;
        } catch (IOException ignored) {
            // The explicit paths above are enough for the normal VS Code setup.
        }

        // Keep the error close to the actual configuration problem.
        throw new IllegalStateException(
                "Cannot find Hangman data folder. Expected data/words/*.csv and data/players/players.csv "
                        + "under the project folder. Current folder: " + workingDir);
    }

    private static boolean isDataRoot(Path root) {
        return Files.isDirectory(root.resolve("words"))
                && Files.isRegularFile(root.resolve("words").resolve("easy.csv"))
                && Files.isRegularFile(root.resolve("words").resolve("medium.csv"))
                && Files.isRegularFile(root.resolve("words").resolve("hard.csv"))
                && Files.isDirectory(root.resolve("players"));
    }
}
