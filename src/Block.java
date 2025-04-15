import java.awt.*;

public class Block {
    private int x, y;
    private int width;
    private final int height = 25;
    private boolean isHit;

    public Block(int x, int y, int width)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.isHit = false;
    }
    public void draw()
    {}

    public Rectangle getBounds()
    {}

    public void checkHit()
    {}

    public boolean getIsHit()
    {}

    public boolean destroy()
    {}
}
