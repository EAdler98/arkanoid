import java.util.ArrayList;
import java.awt.Color;
import java.util.List;

/** Class for level 3. */
public class Level3 implements LevelInformation {

    private int numberOfBalls = 3;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed = 5;
    private int paddleWidth = 150;
    private String levelName = "Hodaya";
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
    public Level3(int width, int height, int border) {
        this.width = width;
        this.height = height;
        initialBallVelocities = new ArrayList<Velocity>() {
            {
                add(new Velocity(0, 2));
            }
            {
                add(new Velocity(1.3, -1.8));
            }
            {
                add(new Velocity(1.3, -1.8));
            }
        };
        blocks = new ArrayList<>();
        ballsLocations = new ArrayList<Point>() {
            {
                add(new Point(400, 320));
            }
            {
                add(new Point(500, 320));
            }
            {
                add(new Point(300, 320));
            }
        };
        for (int i = 0; i < 7; i++) {
            Color color = new Color((int) (Math.random() * 0x1000000));
            for (int j = 0; j < 15; j++) {
                Rectangle rect = new Rectangle(
                        new Point(border + j * (width - 2 * border) / 15, 130 + 20 * i), (width - 2 * border) / 15, 20);
                Block b = new Block(rect, color);
                blocks.add(b);

            }
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
        Block b = new Block(new Rectangle(new Point(0, 0), width, height), Color.WHITE);
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
