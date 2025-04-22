import javax.swing.*;
import java.awt.*;

public class Ball {
    private GameView northPole;
    private Image sunImage;
    private int x;
    private int y;
    private int diameter;
    private int dx, dy;
    private static final int MAX_SPEED = 8;
    private Game g;

    public Ball(int x, int y, int diameter, Game g)
    {
        sunImage =  new ImageIcon("Resources/SunImage-Photoroom.png").getImage();
        this.g = g;
        this.x = (int) (Math.random() * 1000);
        this.y = (int) (Math.random() * 500 + 500);
        this.diameter = diameter;
        this.dx = MAX_SPEED - (int) (Math.random() * MAX_SPEED * 2);
        this.dy = MAX_SPEED - (int) (Math.random() * MAX_SPEED * 2);
    }
    public void move()
    {}
    public void draw(Graphics g)
    {
        g.drawImage(sunImage, x, y, diameter, diameter, northPole);
    }
    public void bounce()
    {
        if ((x <= 0 && dx < 0) || (x >= 1000 - diameter&& dx > 0)) {
            dx = -dx;
        }
        if(y >= 0 && y < 800)
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
    {return null;}
    public int getX()
    {return 0;}
    public int getY()
    {return 0;}

}

