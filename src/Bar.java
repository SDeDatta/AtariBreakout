import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Bar {
    private int x;
    private int width;
    private int height;
    private int dx;
    private int dy;
    private int y;

    public Bar(int dx, int dy)
    {
        x = 500;
        y = 750;
        this.width = 50;
        this.height = 10;
        this.dx = dx;
        this.dy = dy;
    }
    public void moveLeft()
    {
        // Change the speed
        if(x + dx > 0)
        {
            dx -= 10;
            this.x += dx;
        }
    }
    public void moveRight()
    {
        if(x + dx + width < GameView.WINDOW_WIDTH)
        {
            dx += 10;
            this.x += dx;
        }
    }
    public void setVelocity(int velocity)
    {
        dx = velocity;
    }
    public void draw(Graphics g)
    {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(this.x, y, width, height);
    }
    public Rectangle getBounds()
    {return new Rectangle(x, y, width, height);}
    public int getX()
    {return 0;}
    public int getWidth()
    {
        return width;
    }
    public void setWidth(int width)
    {
        this.width = width;
    }
    public int getY()
    {return 0;}
    public int getSize()
    {return 0;}
}

