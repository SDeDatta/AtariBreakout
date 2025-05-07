import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

public class GameView extends JFrame{
    private Image bgImage;
    private Image initialBgImage;
    private Image endBgImage;
    private Image wonBgImage;
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 800;
    private Game game;
    public GameView(Game g)
    {
        // Initialize instance variables.
        // TODO: initialize the View's instance variables.
        this.game = g;
        bgImage = new ImageIcon("Resources/Pengu.jpeg").getImage();
        initialBgImage = new ImageIcon("Resources/BlackBackground.jpg").getImage();
        endBgImage = new ImageIcon("Resources/GameOverBg.jpg").getImage();
        wonBgImage = new ImageIcon("Resources/WinScreen.jpg").getImage();
        game.getBall();
        game.getBar();
        // Setup the window and the buffer strategy.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Atari Breakout");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        createBufferStrategy(2);
    }
    public void paint(Graphics g) {
        BufferStrategy bf = this.getBufferStrategy();
        if (bf == null)
            return;
        Graphics g2 = null;
        try {
            g2 = bf.getDrawGraphics();
            myPaint(g2);
        }
        finally {
            g2.dispose();
        }
        bf.show();
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * myPaint
     *
     * This is used by the buffering strategy to do the actual painting.
     */
    public void myPaint(Graphics g)
    {
        if(game.getState().equals("instructions"))
        {
            drawStart(g);
        }
        else if(game.getState().equals("game"))
        {
            drawGame(g);
        }
        else if (game.getState().equals("lost"))
        {
            drawEnd(g);
        }
        else if(game.getState().equals("won"))
        {
            drawWon(g);
        }
    }
    public void drawStart(Graphics g)
    {
        g.drawImage(initialBgImage, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.setFont(new Font("SansSerif", Font.BOLD, 15));
        g.setColor(Color.ORANGE);
        int centerX = 200; // approximate x for centered text
        int startY = 350;
        int lineSpacing = 40;

        g.drawString("Welcome to Penguin Breakout! This penguin loves the beach, he is far too", centerX, startY);
        g.drawString("cold. Deflect the sun to hit the blocks of ice and warm him up!", centerX + 20, startY + lineSpacing);
        g.drawString("You lose if you let the sun hit the ground. Press 1 to play easy.", centerX + 20, startY + 2 * lineSpacing);
        g.drawString("Press 2 to play medium, and 3 to play hard. Beware, the ball moves faster", centerX, startY + 3 * lineSpacing);
        g.drawString("as you hit more blocks. Enjoy!", centerX + 150, startY + 4 * lineSpacing);

    }
    public void drawGame(Graphics g)
    {
        g.drawImage(bgImage,0,0,WINDOW_WIDTH, WINDOW_HEIGHT, this);
        game.getBar().draw(g);
        game.getBall().draw(g);
        for(Block b: game.getBlocks())
        {
            b.draw(g);
        }
    }
    public void drawEnd(Graphics g)
    {
        g.drawImage(endBgImage, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.setColor(Color.ORANGE);
        g.setFont(new Font("SansSerif", Font.BOLD, 15));
        g.drawString("You hit " + (30 - game.getBlocks().size()) + "/30 Press space to play again", 400, 600);
    }
    public void drawWon(Graphics g)
    {
        g.drawImage(wonBgImage, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.setColor(Color.ORANGE);
        g.setFont(new Font("SansSerif", Font.BOLD, 15));
        g.drawString("Congrats You're Brilliant", 400, 600);
        g.drawString("Press space to play again", 400, 630);
    }
}
