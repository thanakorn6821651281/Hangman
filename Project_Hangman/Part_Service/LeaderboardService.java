package Part_Service;
import Domain.Player;
import Part_Data.ScoreRepository;
import java.util.List;
public class LeaderboardService {
    private final ScoreRepository repository;
    public LeaderboardService(ScoreRepository repository){ this.repository=repository; }
    public void save(Player p){ repository.save(p); }
    public List<Player> getScores(){ return repository.load(); }
}
