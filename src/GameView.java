import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

public class GameView extends JFrame{
    private Image bgImage;
    public static final int WINDOW_WIDTH = 1000;
    public final int WINDOW_HEIGHT = 800;
    private final int TITLE_BAR_HEIGHT = 23;
    private Game game;
    private String state;
    private Ball ball;
    private Bar bar;
    public GameView(Game g)
    {
        // Initialize instance variables.
        // TODO: initialize the View's instance variables.
        this.game = g;
        bgImage = new ImageIcon("Resources/Pengu.jpeg").getImage();
        ball = game.getBall();
        bar = game.getBar();
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
    }
    public void drawStart(Graphics g)
    {
        g.setFont(new Font("SansSerif", Font.BOLD, 15));
        g.setColor(Color.GRAY);
        g.drawString("Welcome to Penguin Breakout! This penguin loves the beach, he is far too ", 200, 350);
        g.drawString("cold. Deflect the sun to hit the blocks of ice and warm him up!", 250, 380);
        g.drawString("You lose if you let the sun hit the ground. Hit the up arrow to start. Enjoy!", 250, 410);
    }
    public void drawGame(Graphics g)
    {
        g.drawImage(bgImage,0,0,WINDOW_WIDTH, WINDOW_HEIGHT, this);
        bar.draw(g);
        ball.draw(g);
        for(Block b: game.getBlocks())
        {
            b.draw(g);
        }
    }
}
