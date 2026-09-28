package Part_UI.GUI;
import Domain.*;
import javax.swing.*; import java.awt.*;
public class DifficultyUI extends JFrame {
    private final Player player;
    public DifficultyUI(Player p){
        player=p; UITheme.setup(this,"Choose Difficulty");
        JPanel root=new JPanel(new GridBagLayout()); root.setBackground(UITheme.BG);
        JPanel box=new JPanel(); box.setBackground(UITheme.CARD);
        box.setBorder(BorderFactory.createEmptyBorder(35,70,35,70));
        box.setLayout(new BoxLayout(box,BoxLayout.Y_AXIS));
        box.add(UITheme.title("CHOOSE DIFFICULTY",38));
        box.add(Box.createVerticalStrut(20));
        box.add(UITheme.title("Player: "+player.getName(),18));
        for(Difficulty d:Difficulty.values()){
            JButton b=UITheme.button(d.name());
            b.setAlignmentX(CENTER_ALIGNMENT);
            b.addActionListener(e->open(new GameUI(player,d,null)));
            box.add(Box.createVerticalStrut(15)); box.add(b);
        }
        JButton back=UITheme.light("BACK"); back.setAlignmentX(CENTER_ALIGNMENT);
        back.addActionListener(e->open(new LoginUI()));
        box.add(Box.createVerticalStrut(20)); box.add(back); root.add(box); setContentPane(root);
    }
    private void open(JFrame f){ dispose(); f.setVisible(true); }
}
