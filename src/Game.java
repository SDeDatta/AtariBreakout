import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Game implements ActionListener {
    private ArrayList<Block> blocks;
    private GameView window;
    private Bar bar;
    private Ball ball;
    private int score;

    public Game()
    {
        this.window = new GameView(this);
        for(int i = 0; i < 100; i++)
        {
            blocks.add(new Block(200,200, 50));
        }
        this.bar = new Bar(10);
        this.ball = new Ball(100, 100, 10);
        this.score = 0;
    }
    public void start()
    {}

    public void update()
    {}

    public void checkCollisions()
    {}

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public Ball getBall() {
        return ball;
    }

    public Bar getBar() {
        return bar;
    }

    public int getScore() {
        return score;
    }

    public boolean checkGameOver()
    {}
}

public static void main(String[] args) {

}
