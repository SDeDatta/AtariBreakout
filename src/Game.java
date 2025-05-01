// Penguin Breakout by Surya De Datta
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game implements KeyListener,ActionListener {
    private ArrayList<Block> blocks;
    private GameView window;
    private static final int SLEEP_TIME = 50;
    private static final int STEP_SIZE = 10;
    private Bar bar;
    private Ball ball;
    private int score;
    private String state;
    private boolean leftPressed;
    private boolean rightPressed;
    private String level;

    public Game()
    {
        resetGame();
        this.window = new GameView(this);
        window.addKeyListener(this);
    }

    public void start() {
    }

    public void update() {
        if (ball.checkHitBottom()) {
            this.state = "lost";
        }
        if (gameWon()) {
            this.state = "won";
        }
        if (leftPressed) {
            bar.moveLeft();
        }
        if (rightPressed) {
            bar.moveRight();
        }
        ball.move();
        if(ball.getBounds().intersects(bar.getBounds()))
        {
            int barMid = bar.getBounds().x + bar.getWidth() / 2;
            int ballMid = ball.getX() + ball.getDiameter() / 2;
            int distFromCenter = ballMid - barMid;
            double ratio = (double) distFromCenter / (bar.getWidth() / 2);
            int maxHorSpeed = 8;
            ball.setSpeed((ratio * maxHorSpeed), ball.getDy()* -1);
        }

        for(int i = 0; i < blocks.size(); i++)
        {
            Block b = blocks.get(i);
            if(ball.getBounds().intersects(b.getBounds()))
            {
                ball.setDy(-1* ball.getDy());
                blocks.remove(i);
                if(this.level.equals("easy"))
                {
                    ball.increaseSpeed(0.2);

                }
                else if(this.level.equals("medium"))
                {
                    ball.increaseSpeed(0.4);
                }
                else if(this.level.equals("hard"))
                {
                    ball.increaseSpeed(0.8);
                }
                break;
            }
        }
        checkCollisions();
    }

    public boolean gameWon() {
        if (this.blocks.isEmpty()) {
            return true;
        }
        return false;
    }

    public void checkCollisions() {
    }

    public String getState() {
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

    public boolean checkGameOver() {
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (state.equals("game")) {
            update();
        }
        window.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                leftPressed = true;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = true;
                break;
            case KeyEvent.VK_1:
                this.state = "game";
                this.level = "easy";
                setupLevel();
                break;
            case KeyEvent.VK_2:
                this.state = "game";
                this.level = "medium";
                setupLevel();
                break;
            case KeyEvent.VK_3:
                this.state = "game";
                this.level = "hard";
                setupLevel();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                bar.setVelocity(0);
                leftPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
                bar.setVelocity(0);
                rightPressed = false;
                break;
            case KeyEvent.VK_SPACE:
                resetGame();
                break;
        }
    }

    public void setupLevel()
    {
        if(this.level.equals("easy"))
        {
            bar.setWidth(70);
            ball.setDiameter(45);
            ball.setSpeed(3, -7);
        }
        if(this.level.equals("medium"))
        {
            bar.setWidth(60);
            ball.setDiameter(38);
            ball.setSpeed(5, -9);
        }
        if(this.level.equals("hard"))
        {
            bar.setWidth(50);
            ball.setDiameter(30);
            ball.setSpeed(7, -11);
        }
    }
    public void resetGame()
    {
        this.state = "instructions";
        this.blocks = new ArrayList<>();
        this.bar = new Bar(10, 0, 0);
        this.ball = new Ball(100, 100, 35, this);
        int rows = 3;
        int cols = 10;
        int padding = 10;
        int blockWidth = (GameView.WINDOW_WIDTH - (cols + 1) * padding) / cols;
        int blockHeight = 30;
        int startX = padding;
        int startY = 100;
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
        this.leftPressed = false;
        this.rightPressed = false;
    }

    public static void main(String[] args) {
        Game g = new Game();
        Timer clock = new Timer(SLEEP_TIME, g);
        clock.start();
    }
}


