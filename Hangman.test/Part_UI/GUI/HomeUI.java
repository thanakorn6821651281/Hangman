package Part_UI.GUI;
import javax.swing.*;
import java.awt.*;
public class HomeUI extends JFrame {
    public HomeUI(){
        UITheme.setup(this,"Hangman - Home");
        JPanel root=new JPanel(); root.setBackground(UITheme.BG);
        root.setLayout(new BoxLayout(root,BoxLayout.Y_AXIS));
        JLabel title=UITheme.title("HANGMAN",58);
        JLabel sub=UITheme.title("Guess the word before you are hanged!",20);
        JButton start=UITheme.button("START GAME");
        JButton board=UITheme.light("LEADERBOARD");
        start.setAlignmentX(CENTER_ALIGNMENT); board.setAlignmentX(CENTER_ALIGNMENT);
        start.addActionListener(e->open(new LoginUI()));
        board.addActionListener(e->open(new LeaderboardUI()));
        root.add(Box.createVerticalStrut(70)); root.add(title); root.add(sub);
        root.add(Box.createVerticalStrut(100)); root.add(start);
        root.add(Box.createVerticalStrut(15)); root.add(board);
        root.add(Box.createVerticalGlue()); setContentPane(root);
    }
    private void open(JFrame f){ dispose(); f.setVisible(true); }
}
