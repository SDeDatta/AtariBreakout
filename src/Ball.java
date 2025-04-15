import java.awt.*;

public class Ball {
    private int x;
    private int y;
    private int radius;
    private int dx, dy;
    private static final int MAX_SPEED = 8;

    public Ball(int x, int y, int radius)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.dx = MAX_SPEED - (int) (Math.random() * MAX_SPEED * 2);
        this.dy = MAX_SPEED - (int) (Math.random() * MAX_SPEED * 2);
    }
    public void move()
    {}
    public void draw()
    {}
    public void bounce()
    {}
    public Rectangle getBounds()
    {}
    public int getX()
    {}
    public int getY()
    {}

}

