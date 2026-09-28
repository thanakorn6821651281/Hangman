package Part_UI.GUI;
import Domain.Player; import Part_Service.AppServices;
import javax.swing.*; import javax.swing.table.DefaultTableModel; import java.awt.*;
public class LeaderboardUI extends JFrame {
    public LeaderboardUI(){
        UITheme.setup(this,"Leaderboard");
        JPanel root=new JPanel(new GridBagLayout()); root.setBackground(UITheme.BG);
        JPanel box=new JPanel(new BorderLayout(10,10)); box.setBackground(UITheme.CARD);
        box.setBorder(BorderFactory.createEmptyBorder(25,35,25,35)); box.setPreferredSize(new Dimension(700,520));
        box.add(UITheme.title("LEADERBOARD",38),BorderLayout.NORTH);
        DefaultTableModel m=new DefaultTableModel(new Object[]{"Rank","Name","Score"},0);
        int rank=1; for(Player p:AppServices.leaderboard().getScores()) m.addRow(new Object[]{rank++,p.getName(),p.getScore()});
        JTable t=new JTable(m); t.setRowHeight(32); box.add(new JScrollPane(t),BorderLayout.CENTER);
        JButton back=UITheme.light("BACK"); back.addActionListener(e->open(new HomeUI())); box.add(back,BorderLayout.SOUTH);
        root.add(box); setContentPane(root);
    }
    private void open(JFrame f){ dispose(); f.setVisible(true); }
}
