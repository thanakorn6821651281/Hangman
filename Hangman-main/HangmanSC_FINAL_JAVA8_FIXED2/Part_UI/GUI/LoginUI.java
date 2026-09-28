package Part_UI.GUI;
import Domain.Player;
import javax.swing.*; import java.awt.*;
public class LoginUI extends JFrame {
    private final JTextField name=new JTextField();
    public LoginUI(){
        UITheme.setup(this,"Hangman - Login");
        JPanel root=new JPanel(new GridBagLayout()); root.setBackground(UITheme.BG);
        JPanel box=new JPanel(); box.setBackground(UITheme.CARD);
        box.setBorder(BorderFactory.createEmptyBorder(40,60,40,60));
        box.setLayout(new BoxLayout(box,BoxLayout.Y_AXIS));
        JLabel title=UITheme.title("LOGIN",44);
        name.setFont(new Font("Arial",Font.BOLD,20)); name.setMaximumSize(new Dimension(420,55));
        JButton login=UITheme.button("START"); JButton back=UITheme.light("BACK");
        login.setAlignmentX(CENTER_ALIGNMENT); back.setAlignmentX(CENTER_ALIGNMENT);
        login.addActionListener(e->go()); back.addActionListener(e->open(new HomeUI()));
        box.add(title); box.add(Box.createVerticalStrut(35));
        box.add(new JLabel("PLAYER NAME")); box.add(name);
        box.add(Box.createVerticalStrut(25)); box.add(login);
        box.add(Box.createVerticalStrut(10)); box.add(back);
        root.add(box); setContentPane(root);
    }
    private void go(){
        if(name.getText().trim().isEmpty()){ JOptionPane.showMessageDialog(this,"กรุณาใส่ชื่อ"); return; }
        open(new DifficultyUI(new Player(name.getText())));
    }
    private void open(JFrame f){ dispose(); f.setVisible(true); }
}
