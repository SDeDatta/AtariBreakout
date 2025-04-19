import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game implements KeyListener,ActionListener{
    private ArrayList<Block> blocks;
    private GameView window;
    private static final int SLEEP_TIME = 110;
    private static final int STEP_SIZE = 10;
    private Bar bar;
    private Ball ball;
    private int score;
    private String state;
    public Game()
    {
        state = "instructions";
        blocks = new ArrayList<>();
        this.bar = new Bar(10);
        this.ball = new Ball(100, 100, 10);
        int rows = 5;
        int cols = 10;
        int padding = 10;
        int blockWidth = (GameView.WINDOW_WIDTH - (cols + 1) * padding) / cols;
        int blockHeight = 30;
        int startX = padding;
        int startY = 100;
        this.window = new GameView(this);
        window.addKeyListener(this);
        for(int row = 0; row < rows; row++)
        {
            for(int col = 0; col < cols; col++)
            {
                int x = startX + col * (blockWidth + padding);
                int y = startY + row * (blockHeight + padding);
                blocks.add(new Block(x, y, blockWidth, blockHeight, window));
            }
        }
        this.score = 0;
    }
    public void start()
    {}

    public void update()
    {}

    public void checkCollisions()
    {}
    public String getState()
    {
        return this.state;
    }
    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public Ball getBall() {
        return ball;
    }

    public Bar getBar() {
        return bar;
    }
    public int getScore() {
        return score;
    }

    public boolean checkGameOver()
    {
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        window.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                bar.shiftX(-STEP_SIZE, 0, GameView.WINDOW_WIDTH);
                break;
            case KeyEvent.VK_RIGHT:
                bar.shiftX(STEP_SIZE, 0, GameView.WINDOW_WIDTH);
                break;
            case KeyEvent.VK_UP:
                this.state = "game";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public static void main(String[] args) {
        Game g = new Game();
        Timer clock = new Timer(SLEEP_TIME, g);
        clock.start();
    }
}


