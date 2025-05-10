import javax.swing.*;
import java.awt.*;

public class Ball {
    private GameView northPole;
    private Image sunImage;
    private int x;
    private int y;
    private int diameter;
    // Variables for the changes in x and y of the ball
    private double dx, dy;

    public Ball(int diameter, GameView gv)
    {
        this.northPole = gv;
        sunImage =  new ImageIcon("Resources/SunImage-Photoroom.png").getImage();
        // Starts the ball at a random yet reasonable position
        this.x = (int) (Math.random() * 20) + 490;
        this.y = (int) (Math.random() * 200) + 500;
        this.diameter = diameter;
        this.dx = 0;
        this.dy = 0;
    }
    // Sets the direction of the ball
    public void setSpeed(double xSpeed, double ySpeed)
    {
        this.dx = xSpeed;
        this.dy = ySpeed;
    }
    public void setDiameter(int diameter)
    {
        this.diameter = diameter;
    }
    // Moves the ball's x and y and calls bounce
    public void move()
    {
        x += dx;
        y += dy;
        bounce();
    }
    // Checks if the ball hit the bottom of the screen to determine if the game is over
    public boolean checkHitBottom()
    {
        return this.y >= GameView.WINDOW_HEIGHT - diameter;
    }
    // Draws the ball at the specific x, y, and diameter
    public void draw(Graphics g)
    {
        g.drawImage(sunImage, x, y, diameter, diameter, northPole);
    }
    // Bounces the ball when contact is made with the top or sides of the screen
    public void bounce()
    {
        // Reverses the direction of the ball
        if ((x <= 0 && dx < 0) || (x >= GameView.WINDOW_WIDTH - diameter && dx > 0)) {
            dx = -dx;
        }
        if(y < diameter)
        {
            y = diameter + 1;
            dy = -dy;
        }
    }
    // Increases the speed of the ball based on a given variable
    public void increaseSpeed(double boost)
    {
        double maxSpeed = 20;

        if(Math.abs(dx) < maxSpeed)
        {
            dx += boost;
        }
        if(Math.abs(dy) < maxSpeed)
        {
            dy += boost;
        }
    }
    // Returns the bounds of the ball (space that the ball takes up)
    public Rectangle getBounds()
    {return new Rectangle(x, y, diameter, diameter);}
    public int getX()
    {return this.x;}
    public double getDx()
    {
        return dx;
    }
    public double getDy()
    {
        return dy;
    }
    public int getDiameter()
    {
        return diameter;
    }
    public int getY()
    {return this.y;}

}

