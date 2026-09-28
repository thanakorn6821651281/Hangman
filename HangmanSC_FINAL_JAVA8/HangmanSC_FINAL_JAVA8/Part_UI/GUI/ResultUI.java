//package Part_UI.GUI;
import Domain.*; import javax.swing.*; import java.awt.*;
public class ResultUI extends JFrame {
    public ResultUI(Player p,HangmanGame game,boolean win){
        UITheme.setup(this,win?"You Win":"Game Over");
        JPanel root=new JPanel(new GridBagLayout()); root.setBackground(UITheme.BG);
        JPanel box=new JPanel(); box.setBackground(UITheme.CARD);
        box.setBorder(BorderFactory.createEmptyBorder(50,80,50,80));
        box.setLayout(new BoxLayout(box,BoxLayout.Y_AXIS));
        JLabel r=UITheme.title(win?"YOU WIN!":"GAME OVER",50); r.setForeground(win?new Color(95,110,60):new Color(145,75,75));
        box.add(r); box.add(Box.createVerticalStrut(20));
        box.add(UITheme.title("Answer: "+game.getAnswer(),20));
        box.add(Box.createVerticalStrut(10)); box.add(UITheme.title("Score: "+p.getScore(),18));
        JButton main=UITheme.button(win?"NEXT":"REPLAY");
        main.setAlignmentX(CENTER_ALIGNMENT);
        if(win) main.addActionListener(e->open(new GameUI(p,game.getDifficulty(),game.getAnswer())));
        else main.addActionListener(e->open(new GameUI(p,game.getDifficulty(),null)));
        JButton menu=UITheme.light("BACK MENU"); menu.setAlignmentX(CENTER_ALIGNMENT);
        menu.addActionListener(e->open(new HomeUI()));
        box.add(Box.createVerticalStrut(30)); box.add(main); box.add(Box.createVerticalStrut(12)); box.add(menu);
        root.add(box); setContentPane(root);
    }
    private void open(JFrame f){ dispose(); f.setVisible(true); }
}
