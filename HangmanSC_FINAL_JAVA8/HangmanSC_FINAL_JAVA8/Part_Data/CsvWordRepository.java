package Part_Data;

import Domain.Difficulty;
import Domain.Word;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class CsvWordRepository implements WordRepository {
    private final Path dataDir;
    public CsvWordRepository(Path dataDir) { this.dataDir = dataDir; }

    public List<Word> findByDifficulty(Difficulty difficulty) {
        Path file = dataDir.resolve(difficulty.name().toLowerCase() + ".csv");
        List<Word> result = new ArrayList<Word>();
        try (BufferedReader br = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (first) { first = false; continue; }
                if (line.trim().isEmpty()) continue;
                String[] p = splitCsv(line);
                if (p.length >= 3) result.add(new Word(p[0], p[1], p[2]));
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read " + file, e);
        }
        return result;
    }

    private String[] splitCsv(String line) {
        List<String> out = new ArrayList<String>();
        StringBuilder cur = new StringBuilder();
        boolean quoted = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') quoted = !quoted;
            else if (c == ',' && !quoted) {
                out.add(cur.toString().trim());
                cur.setLength(0);
            } else cur.append(c);
        }
        out.add(cur.toString().trim());
        return out.toArray(new String[out.size()]);
    }
}
