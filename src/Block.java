import javax.swing.*;
import java.awt.*;

public class Block {
    private int x, y;
    private int width;
    private int height;
    private boolean isHit;
    private Image iceImage;
    private GameView northPole;

    public Block(int x, int y, int width, int height, GameView northPole)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isHit = false;
        iceImage = new ImageIcon("Resources/StockIcePhoto-Photoroom.png").getImage();
        this.northPole = northPole;

    }
    public void draw(Graphics g)
    {
        g.drawImage(iceImage, x, y, width, height, northPole);
    }

    public Rectangle getBounds()
    {return null;}

    public void checkHit()
    {}

    public boolean getIsHit()
    {return false;}

    public boolean destroy()
    {return false;}
}
