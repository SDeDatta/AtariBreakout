import java.awt.*;

public class Bar {
    private int x;
    private int width;
    private final int height = 10;
    private int dx;
    private int y;

    public Bar(int dx)
    {
        //Sets the bar to the middle of the screen and close to the bottom
        x = 500;
        y = 750;
        // Initializes the width and height of the bar
        this.width = 0;
        // Variable that represents the change in x
        this.dx = dx;
    }
    public void moveLeft()
    {
        // Changes the position of the bar based on dx
        // Ensures the bar doesn't go off the left side of the screen
        if(x + dx > 0)
        {
            dx -= 10;
            this.x += dx;
        }
    }
    public void moveRight()
    {
        // Ensures the bar doesn't go off the right side of the screen
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
    // Draws the bar at the specific x, y, width, and height
    public void draw(Graphics g)
    {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(this.x, this.y, width, height);
    }
    // Returns the bounds of the bar (space that the bar takes up)
    public Rectangle getBounds()
    {return new Rectangle(x, y, width, height);}
    public int getWidth()
    {
        return width;
    }
    public void setWidth(int width)
    {
        this.width = width;
    }
    public int getX()
    {return this.x;}
    public int getY()
    {return this.y;}
}

