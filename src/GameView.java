import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame{
    private Image aquariumImage;
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 800;
    private final int TITLE_BAR_HEIGHT = 23;
    private Game g;
    public GameView(Game g) {

        // Initialize instance variables.
        // TODO: initialize the View's instance variables.
        this.g = g;
        aquariumImage = new ImageIcon("Resources/bubbles.jpg").getImage();

        // Setup the window and the buffer strategy.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Atari Breakout");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        createBufferStrategy(2);
    }

}
