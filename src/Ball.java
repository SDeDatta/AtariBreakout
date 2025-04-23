import javax.swing.*;
import java.awt.*;

public class Ball {
    private GameView northPole;
    private Image sunImage;
    private int x;
    private int y;
    private int diameter;
    private int dx, dy;
    private static final int MAX_SPEED = 20;
    private Game g;

    public Ball(int x, int y, int diameter, Game g)
    {
        sunImage =  new ImageIcon("Resources/SunImage-Photoroom.png").getImage();
        this.g = g;
        this.x = (int) (Math.random() * 20) + 490;
        this.y = (int) (Math.random() * 250) + 500;
        this.diameter = diameter;
        //this.dx = MAX_SPEED - (int) (Math.random() * MAX_SPEED * 2);
        this.dx = 10;
        this.dy = 10;
        //this.dy = MAX_SPEED - (int) (Math.random() * MAX_SPEED * 2);
    }
    public void move()
    {
        x += dx;
        y += dy;
        bounce();
    }
    public boolean checkHitBottom()
    {
        if(this.y >= 800 - diameter)
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
        if ((x <= 0 && dx < 0) || (x >= 1000 - diameter&& dx > 0)) {
            dx = -dx;
        }
        if(y <= diameter)
        {
            dy = -dy;
        }
        if(getBounds().intersects(g.getBar().getBounds()))
        {
            dy = -dy;
        }

        for(int i = 0; i < g.getBlocks().size(); i++)
        {
            Block b = g.getBlocks().get(i);
            if(getBounds().intersects(b.getBounds()))
            {
                dy = -dy;
                g.getBlocks().remove(i);
                break;
            }
        }
    }
    public Rectangle getBounds()
    {return new Rectangle(x, y, diameter, diameter);}
    public int getX()
    {return this.x;}
    public int getY()
    {return this.y;}

}

