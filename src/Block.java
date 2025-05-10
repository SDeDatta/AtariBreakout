import javax.swing.*;
import java.awt.*;

public class Block {
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private Image iceImage;
    private GameView northPole;

    public Block(int x, int y, int width, int height, GameView northPole)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        iceImage = new ImageIcon("Resources/StockIcePhoto-Photoroom.png").getImage();
        this.northPole = northPole;

    }
    // Draws one block at a specific x, y, width, and height
    public void draw(Graphics g)
    {
        g.drawImage(iceImage, x, y, width, height, northPole);
    }
    // Returns the bounds of the block (space that the block takes up)
    public Rectangle getBounds()
    {return new Rectangle(x, y, width, height);}
    public int getWidth()
    {
        return this.width;
    }
}
