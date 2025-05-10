// Penguin Breakout by Surya De Datta
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game implements KeyListener,ActionListener {
    private ArrayList<Block> blocks;
    private GameView window;
    private static final int SLEEP_TIME = 50;
    private Bar bar;
    private Ball ball;
    private int score;
    private String state;
    private boolean leftPressed;
    private boolean rightPressed;
    private String level;
    private int hits;

    public Game()
    {
        // Calls a function that does constructor duties without being a constructor
        // Ensures that the game resets after the player plays again
        resetGame();
        this.window = new GameView(this);
        window.addKeyListener(this);
    }
    // Updates the game by checking if the game is over
    // Moves the bar and ball by calling the "move" functions
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
        checkCollisions();
    }
    // Checks if all blocks have been destroyed and the game is therefore won
    public boolean gameWon() {
        return this.blocks.isEmpty();
    }
    // Checks if the ball has collided with the bar or any of the blocks
    public void checkCollisions()
    {
        // Uses the bounds to determine if a collision occurred
        if(ball.getBounds().intersects(bar.getBounds()))
        {
            // Ensures that the ball goes in a different direction based on where the ball makes contact with the bar
            hits++;
            int barMid = bar.getBounds().x + bar.getWidth() / 2;
            int ballMid = ball.getX() + ball.getDiameter() / 2;
            int distFromCenter = ballMid - barMid;
            double ratio = (double) distFromCenter / ((double) bar.getWidth() / 2);
            int maxHorSpeed = 8;
            ball.setSpeed((ratio * maxHorSpeed), ball.getDy()* -1);
        }
        // Checks if each block has made contact with the ball
        for(int i = 0; i < blocks.size(); i++)
        {
            Block block = blocks.get(i);
            // Uses the bounds to determine if a collision occurred
            if(ball.getBounds().intersects(block.getBounds()))
            {
                // Ensures that the ball goes in a different direction based on where the ball makes contact with the block
                int blockMid = block.getBounds().x + block.getWidth() / 2;
                int ballMid = ball.getX() + ball.getDiameter() / 2;
                int distFromCenter = ballMid - blockMid;
                double ratio = (double) distFromCenter / ((double) block.getWidth() / 2);
                int maxHorSpeed = 8;
                ball.setSpeed((ratio * maxHorSpeed), ball.getDy()* -1);
                // Removes the block if it has been hit
                blocks.remove(i);
                // Increases the speed of the ball after a block is hit
                // Different increase of speed based on what level the player is playing
                switch (this.level) {
                    case "easy" -> ball.increaseSpeed(0.2);
                    case "medium" -> ball.increaseSpeed(0.4);
                    case "hard" -> ball.increaseSpeed(0.8);
                }
                break;
            }
        }
    }

    public String getState() {
        return this.state;
    }

    public ArrayList<Block> getBlocks() {
        return this.blocks;
    }

    public Ball getBall() {
        return this.ball;
    }

    public Bar getBar() {
        return this.bar;
    }
    public int getHits() {
        return this.hits;
    }
    public int getScore() {
        return this.score;
    }

    // Updates the game every time an action is performed during the game
    @Override
    public void actionPerformed(ActionEvent e) {
        if (state.equals("game")) {
            update();
        }
        // Repaints the window according to the update
        window.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            // Uses user key input to check if the bar needs to move
            case KeyEvent.VK_LEFT:
                leftPressed = true;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = true;
                break;
            // Starts the game with a specific level based on key input
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
            // Stops bar movement once an arrow key is released
            case KeyEvent.VK_LEFT:
                bar.setVelocity(0);
                leftPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
                bar.setVelocity(0);
                rightPressed = false;
                break;
            // Resets the game when a space is pressed
            // Used after the game is over
            case KeyEvent.VK_SPACE:
                resetGame();
                break;
        }
    }
    // Sets up different variables based on the difficulty that the user selected
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
        // Initializes all the instance variables
        this.state = "instructions";
        this.blocks = new ArrayList<>();
        this.bar = new Bar(0);
        this.ball = new Ball(35, window);
        // Creates all the blocks and gets them to draw themselves
        int rows = 3;
        int cols = 10;
        int padding = 10;
        int blockWidth = (GameView.WINDOW_WIDTH - (cols + 1) * padding) / cols;
        int blockHeight = 30;
        int startY = 100;
        for(int row = 0; row < rows; row++)
        {
            for(int col = 0; col < cols; col++)
            {
                int x = padding + col * (blockWidth + padding);
                int y = startY + row * (blockHeight + padding);
                blocks.add(new Block(x, y, blockWidth, blockHeight, window));
            }
        }
        this.score = 0;
        this.hits = 0;
        this.leftPressed = false;
        this.rightPressed = false;
    }

    public static void main(String[] args) {
        // Starts the program
        Game g = new Game();
        Timer clock = new Timer(SLEEP_TIME, g);
        clock.start();
    }
}


