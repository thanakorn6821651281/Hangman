package Part_UI.GUI;
import Domain.*; import Part_Service.AppServices;
import javax.swing.*; import java.awt.*; import java.awt.event.KeyAdapter; import java.awt.event.KeyEvent;

public class GameUI extends JFrame {
    private final Player player; private final Difficulty difficulty; private final String previous;
    private final HangmanGame game;
    private final JLabel word=UITheme.title("",34);
    private final JLabel wrong=UITheme.title("",18);
    private final JLabel hint=UITheme.title("Hint: ???",16);
    private final JButton[] keys=new JButton[26];

    public GameUI(Player p,Difficulty d,String previous){
        player=p; difficulty=d; this.previous=previous;
        game=previous==null?AppServices.game().newGame(d):AppServices.game().nextGame(d,previous);
        UITheme.setup(this,"Hangman - "+d.name());
        build(); refresh();
    }
    private void build(){
        JPanel root=new JPanel(new BorderLayout(15,15)); root.setBackground(UITheme.BG);
        JPanel top=new JPanel(new BorderLayout()); top.setOpaque(false);
        JButton back=UITheme.light("BACK"); back.addActionListener(e->open(new HomeUI()));
        top.add(back,BorderLayout.WEST); top.add(UITheme.title("HANGMAN",38),BorderLayout.CENTER);
        top.add(UITheme.title(difficulty.name()+" | "+player.getName(),15),BorderLayout.EAST);
        root.add(top,BorderLayout.NORTH);

        JPanel left=new JPanel(new BorderLayout()); left.setBackground(UITheme.CARD);
        JLabel man=UITheme.title("☻",120); left.add(man,BorderLayout.CENTER); left.add(wrong,BorderLayout.SOUTH);

        JPanel right=new JPanel(new BorderLayout(10,10)); right.setBackground(UITheme.CARD);
        JPanel info=new JPanel(); info.setOpaque(false); info.setLayout(new BoxLayout(info,BoxLayout.Y_AXIS));
        info.add(word); info.add(Box.createVerticalStrut(15)); info.add(hint);
        JButton hintBtn=UITheme.light("HINT"); hintBtn.setAlignmentX(CENTER_ALIGNMENT);
        hintBtn.addActionListener(e->{hint.setText("<html>Hint: "+game.getHint()+"</html>"); hintBtn.setEnabled(false);});
        info.add(Box.createVerticalStrut(12)); info.add(hintBtn);
        JPanel kb=new JPanel(new GridLayout(5,5,7,7)); kb.setOpaque(false);
        for(int i=0;i<26;i++){
            char c=(char)('A'+i); JButton b=new JButton(""+c); b.setFont(new Font("Arial",Font.BOLD,17));
            final char x=c; b.addActionListener(e->guess(x)); keys[i]=b; kb.add(b);
        }
        right.add(info,BorderLayout.NORTH); right.add(kb,BorderLayout.CENTER);
        root.add(left,BorderLayout.CENTER); root.add(right,BorderLayout.EAST); setContentPane(root);

        addKeyListener(new KeyAdapter(){ public void keyTyped(KeyEvent e){
            char c=Character.toUpperCase(e.getKeyChar()); if(c>='A'&&c<='Z') guess(c);
        }});
        setFocusable(true);
    }
    private void guess(char c){
        if(!game.guess(c)) return;
        keys[c-'A'].setEnabled(false); refresh();
        if(game.getStatus()==GameStatus.WON) finish(true);
        else if(game.getStatus()==GameStatus.LOST) finish(false);
        requestFocusInWindow();
    }
    private void refresh(){ word.setText(game.displayWord()); wrong.setText("Wrong: "+game.getWrong()+" / "+game.getMaxWrong()); }
    private void finish(boolean win){
        int points=win?AppServices.game().score(game):0;
        player.addScore(points); AppServices.leaderboard().save(player);
        open(new ResultUI(player,game,win));
    }
    private void open(JFrame f){ dispose(); f.setVisible(true); }
}
