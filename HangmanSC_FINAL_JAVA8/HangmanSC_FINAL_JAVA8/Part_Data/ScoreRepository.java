package Part_Data;
import Domain.Player;
import java.util.List;
public interface ScoreRepository {
    void save(Player player);
    List<Player> load();
}
