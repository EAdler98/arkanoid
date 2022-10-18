import java.util.ArrayList;
import java.awt.Color;
import java.util.List;

/** Class for level 2. */
public class Level2 implements LevelInformation {

    private int numberOfBalls = 2;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed = 5;
    private int paddleWidth = 150;
    private String levelName = "Meni";
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
    public Level2(int width, int height, int border) {
        this.width = width;
        this.height = height;
        initialBallVelocities = new ArrayList<Velocity>() {
            {
                add(new Velocity(2.7, -2.7));
            }
            {
                add(new Velocity(-2.7, -2.7));
            }

        };
        blocks = new ArrayList<>();
        ballsLocations = new ArrayList<Point>() {
            {
                add(new Point(500, 300));
            }
            {
                add(new Point(300, 300));
            }
        };
        for (int i = 0; i < 12; i++) {
            Rectangle rect = new Rectangle(new Point(width - border - (i + 1) * 50, 150), 50, 20);
            Block b = new Block(rect, Color.ORANGE);
            blocks.add(b);

        }
        for (int i = 0; i < 11; i++) {
            Rectangle rect = new Rectangle(new Point(width - border - (i + 1) * 50, 172), 50, 20);
            Block b = new Block(rect, Color.blue);
            blocks.add(b);
        }
        for (int i = 0; i < 10; i++) {
            Rectangle rect = new Rectangle(new Point(width - border - (i + 1) * 50, 194), 50, 20);
            Block b = new Block(rect, Color.RED);
            blocks.add(b);
        }
        for (int i = 0; i < 9; i++) {
            Rectangle rect = new Rectangle(new Point(width - border - (i + 1) * 50, 216), 50, 20);
            Block b = new Block(rect, Color.GREEN);
            blocks.add(b);
        }
        for (int i = 0; i < 8; i++) {
            Rectangle rect = new Rectangle(new Point(width - border - (i + 1) * 50, 238), 50, 20);
            Block b = new Block(rect, Color.YELLOW);
            blocks.add(b);
        }
        for (int i = 0; i < 7; i++) {
            Rectangle rect = new Rectangle(new Point(width - border - (i + 1) * 50, 260), 50, 20);
            Block b = new Block(rect, Color.BLACK);
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
        Block b = new Block(new Rectangle(new Point(0, 0), width, height), Color.LIGHT_GRAY);
        return b;
    }

    @Override
    public List<Block> blocks() {
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 12 + 11 + 10 + 9 + 8 + 7;
    }

    @Override
    public List<Point> ballsLocations() {
        return ballsLocations;
    }

}
