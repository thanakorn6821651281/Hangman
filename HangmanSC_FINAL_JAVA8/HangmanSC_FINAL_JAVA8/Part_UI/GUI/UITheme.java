//package Part_UI.GUI;
import java.awt.*;
import javax.swing.*;
public final class UITheme {
    public static final Color BG=new Color(235,224,205);
    public static final Color CARD=new Color(247,239,224);
    public static final Color BROWN=new Color(154,111,77);
    public static final Color DARK=new Color(78,65,54);
    public static final Color KEY=new Color(255,250,240);
    private UITheme(){}
    public static void setup(JFrame f,String title){
        f.setTitle(title); f.setSize(1050,700); f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static JButton button(String text){
        JButton b=new JButton(text); b.setFont(new Font("Arial",Font.BOLD,18));
        b.setBackground(BROWN); b.setForeground(Color.WHITE); b.setFocusPainted(false);
        b.setPreferredSize(new Dimension(250,50)); return b;
    }
    public static JButton light(String text){
        JButton b=new JButton(text); b.setFont(new Font("Arial",Font.BOLD,16));
        b.setBackground(KEY); b.setForeground(DARK); b.setFocusPainted(false);
        b.setPreferredSize(new Dimension(210,46)); return b;
    }
    public static JLabel title(String s,int size){
        JLabel l=new JLabel(s,SwingConstants.CENTER); l.setFont(new Font("Arial",Font.BOLD,size));
        l.setForeground(DARK); return l;
    }
}
