import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Bar {
    private int x;
    private int speed;
    private final int width = 50;
    private final int height = 10;
    private int dx;
    private int dy;

    public Bar(int speed, int dx, int dy)
    {
        x = 375;
        this.dx = dx;
        this.dy = dy;
        this.speed = speed;
    }
    public void moveLeft()
    {
        // Change the speed
        dx += -10;
    }

        this.x += dx;
    }
    public void moveRight()
    {}
    public void draw(Graphics g)
    {
        g.setColor(Color.BLUE);
        g.fillRect(this.x, 700, 100, 10);
    }
    public Rectangle getBounds()
    {return null;}
    public int getX()
    {return 0;}
    public int getY()
    {return 0;}
    public int getSize()
    {return 0;}
    public void shiftX(int shift, int xLow, int xHigh) {
        if (x - width/2 + shift <= xLow && shift < 0) {
            x = xLow + width/2;
        }
        else if (x + width/2 + shift >= xHigh && shift > 0) {
            x = xHigh - width/2;
        }
        else {
            x += shift;
        }
    }
}

