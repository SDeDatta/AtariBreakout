import javax.swing.*;
import java.awt.*;

public class Ball {
    private GameView northPole;
    private Image sunImage;
    private int x;
    private int y;
    private int diameter;
    private double dx, dy;
    private static final int MAX_SPEED = 20;

    public Ball(int x, int y, int diameter)
    {
        sunImage =  new ImageIcon("Resources/SunImage-Photoroom.png").getImage();
        this.x = (int) (Math.random() * 20) + 490;
        this.y = (int) (Math.random() * 250) + 500;
        this.diameter = diameter;
        this.dx = 0;
        this.dy = 0;
    }
    public void setSpeed(double xSpeed, double ySpeed)
    {
        this.dx = xSpeed;
        this.dy = ySpeed;
    }

    public void setDiameter(int diameter)
    {
        this.diameter = diameter;
    }
    public void move()
    {
        x += dx;
        y += dy;
        bounce();
    }
    public boolean checkHitBottom()
    {
        if(this.y >= GameView.WINDOW_HEIGHT - diameter)
        {
            return true;
        }
        return false;
    }
    public void draw(Graphics g)
    {
        g.drawImage(sunImage, x, y, diameter, diameter, northPole);
    }
    public void bounce()
    {
        if ((x <= 0 && dx < 0) || (x >= GameView.WINDOW_WIDTH - diameter && dx > 0)) {
            dx = -dx;
        }
        if(y < diameter)
        {
            y = diameter;
            dy = -dy;
        }
    }

    public void reset()
    {
        this.dx = 5;
        this.dy = 9;
    }

    public void increaseSpeed(double boost)
    {
        double speedBoost = boost;
        double maxSpeed = 20;

        if(Math.abs(dx) < maxSpeed)
        {
            dx += speedBoost;
        }
        if(Math.abs(dy) < maxSpeed)
        {
            dy += speedBoost;
        }
    }
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
    public void setDx(double dx)
    {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public int getY()
    {return this.y;}

}

