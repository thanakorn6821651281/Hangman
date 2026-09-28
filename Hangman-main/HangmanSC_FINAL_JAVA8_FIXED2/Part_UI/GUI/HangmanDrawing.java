package Part_UI.GUI;

import javax.swing.*;
import java.awt.*;

/** Draws the hangman progressively as wrong guesses increase. */
public class HangmanDrawing extends JPanel {
    private int wrong;
    private int maxWrong;

    public HangmanDrawing(int maxWrong) {
        this.maxWrong = Math.max(1, maxWrong);
        this.wrong = 0;
        setOpaque(false);
        setPreferredSize(new Dimension(300, 430));
    }

    public void setWrong(int wrong) {
        this.wrong = Math.max(0, Math.min(wrong, maxWrong));
        repaint();
    }

    public void setMaxWrong(int maxWrong) {
        this.maxWrong = Math.max(1, maxWrong);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(UITheme.DARK);
        g2.setStroke(new BasicStroke(7f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        int w = getWidth();
        int h = getHeight();
        int baseY = h - 55;
        int poleX = Math.max(70, w / 3);
        int topY = 55;
        int beamX2 = Math.min(w - 45, poleX + 130);

        // Gallows are always visible.
        g2.drawLine(poleX - 55, baseY, beamX2 + 30, baseY);
        g2.drawLine(poleX, baseY, poleX, topY);
        g2.drawLine(poleX, topY, beamX2, topY);
        g2.drawLine(beamX2, topY, beamX2, topY + 45);

        // Convert the game's difficulty-specific maximum into the standard 6 drawing stages.
        int stage = (int) Math.ceil((wrong * 6.0) / maxWrong);
        if (wrong <= 0) stage = 0;

        int headX = beamX2 - 26;
        int headY = topY + 45;
        int headD = 52;
        int bodyTop = headY + headD;
        int bodyBottom = bodyTop + 105;
        int centerX = headX + headD / 2;

        if (stage >= 1) {
            g2.drawOval(headX, headY, headD, headD);
        }
        if (stage >= 2) {
            g2.drawLine(centerX, bodyTop, centerX, bodyBottom);
        }
        if (stage >= 3) {
            g2.drawLine(centerX, bodyTop + 25, centerX - 60, bodyTop + 82);
        }
        if (stage >= 4) {
            g2.drawLine(centerX, bodyTop + 25, centerX + 60, bodyTop + 82);
        }
        if (stage >= 5) {
            g2.drawLine(centerX, bodyBottom, centerX - 55, bodyBottom + 75);
        }
        if (stage >= 6) {
            g2.drawLine(centerX, bodyBottom, centerX + 55, bodyBottom + 75);
        }

        // Small face detail only after the final stage.
        if (stage >= 6) {
            g2.setStroke(new BasicStroke(4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.drawLine(headX + 16, headY + 18, headX + 23, headY + 25);
            g2.drawLine(headX + 23, headY + 18, headX + 16, headY + 25);
            g2.drawLine(headX + 29, headY + 18, headX + 36, headY + 25);
            g2.drawLine(headX + 36, headY + 18, headX + 29, headY + 25);
            g2.drawArc(headX + 14, headY + 25, 26, 16, 200, 140);
        }

        g2.dispose();
    }
}
