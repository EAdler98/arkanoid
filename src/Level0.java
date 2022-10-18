import java.util.ArrayList;
import java.awt.Color;
import java.util.List;

/** Class for level 0. */
public class Level0 implements LevelInformation {

    private int numberOfBalls = 1;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed = 5;
    private int paddleWidth = 150;
    private String levelName = "Direct hit";
    private List<Block> blocks;
    private List<Point> ballsLocations;
    private int width, height;

    /**
     * Constructor.
     *
     * @param width
     * @param height
     * @param border
     */
    public Level0(int width, int height, int border) {
        this.width = width;
        this.height = height;
        initialBallVelocities = new ArrayList<Velocity>() {
            {
                add(new Velocity(0, -1));
            }
        };
        blocks = new ArrayList<>();
        ballsLocations = new ArrayList<Point>() {
            {
                add(new Point(400, 300));
            }
        };

        Rectangle rect = new Rectangle(new Point(375, 100), 50, 50);
        Block b = new Block(rect, Color.RED);
        blocks.add(b);

    }

    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return initialBallVelocities;
    }

    @Override
    public int paddleSpeed() {
        return paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return paddleWidth;
    }

    @Override
    public String levelName() {
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        Block b = new Block(new Rectangle(new Point(0, 0), width, height), Color.WHITE);
        return b;
    }

    @Override
    public List<Block> blocks() {
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public List<Point> ballsLocations() {
        return ballsLocations;
    }

}
