import javax.swing.*;
import java.awt.*;

public class Block {
    private int x, y;
    private int width;
    private final int height = 25;
    private boolean isHit;
    private Image iceImage;
    private GameView northPole;

    public Block(int x, int y, int width, GameView northPole)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.isHit = false;
        iceImage = new ImageIcon("Resources/IceBlock.jpg").getImage();
        this.northPole = northPole;

    }
    public void draw(Graphics g)
    {
        g.drawImage(iceImage, x, y, iceImage.getWidth(northPole), iceImage.getHeight(northPole), northPole);
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
