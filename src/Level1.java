import java.util.ArrayList;
import java.awt.Color;
import java.util.List;

/** Class for level 1. */
public class Level1 implements LevelInformation {

    private int numberOfBalls = 10;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed = 5;
    private int paddleWidth = 600;
    private String levelName = "Arie";
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
    public Level1(int width, int height, int border) {
        this.width = width;
        this.height = height;
        initialBallVelocities = new ArrayList<Velocity>() {

        };
        for (int i = 1; i <= 5; i++) {
            initialBallVelocities.add(Velocity.fromAngleAndSpeed(-10 * i, 2));
        }
        for (int i = 1; i <= 5; i++) {
            initialBallVelocities.add(Velocity.fromAngleAndSpeed(10 * i, 2));
        }
        blocks = new ArrayList<>();
        ballsLocations = new ArrayList<Point>();
        for (int i = 1; i <= 5; i++) {
            ballsLocations.add(new Point(400 - 20 * i, 400 + 10 * i));
        }
        for (int i = 1; i <= 5; i++) {
            ballsLocations.add(new Point(400 + 20 * i, 400 + 10 * i));
        }
        for (int i = 1; i <= 5; i++) {
            initialBallVelocities.add(Velocity.fromAngleAndSpeed(10 * i, 1.5));
        }
        for (int i = 0; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point(width - border - (i + 1) * 50 - 5, 250), 50, 20);
            Block b = new Block(rect, new Color((int) (Math.random() * 0x1000000)));
            blocks.add(b);
        }
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
        Block b = new Block(new Rectangle(new Point(0, 0), width, height), Color.CYAN);
        return b;
    }

    @Override
    public List<Block> blocks() {
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

    @Override
    public List<Point> ballsLocations() {
        return ballsLocations;
    }

}
