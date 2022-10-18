import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * a class for palying the game.
 * responslibe for intializing the game and running the farmes.
 */
public class GameLevel implements Animation {
    private AnimationRunner runner;
    private boolean running;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private GUI gui;
    private Counter sCounter;
    private LevelInformation levelInformation;
    private ScoreTrackingListener scoreTrackingListener;
    private final int border = 20;
    private final int width = 800;
    private final int height = 600;

    /**
     * Constructor.
     *
     * @param levelInformation
     * @param keyboardSensor
     * @param animationRunner
     * @param counter
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboardSensor, AnimationRunner animationRunner,
            Counter counter) {
        this.levelInformation = levelInformation;
        sCounter = counter;
        this.scoreTrackingListener = new ScoreTrackingListener(sCounter);
        runner = animationRunner;
        gui = runner.getGui();

    }

    /**
     * add collidable object to the screen.
     *
     * @param c
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * add sprite object to the screen.
     *
     * @param s
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle),
     * and add them to the game.
     */
    public void initialize() {

        Counter remainBlocks = new Counter();
        blockRemover = new BlockRemover(this, remainBlocks);
        blockRemover.getremainingBlocks().increase(levelInformation.numberOfBlocksToRemove());
        Counter remainingBalls = new Counter();
        remainingBalls.increase(levelInformation.numberOfBalls());
        ballRemover = new BallRemover(this, remainingBalls);
        sprites = new SpriteCollection();
        addSprite(levelInformation.getBackground());
        environment = new GameEnvironment();
        // creation of the borders
        Block top = new Block(new Rectangle(new Point(0, 25), width, border), Color.gray);
        Block buttom = new Block(new Rectangle(new Point(0, height + 10), width, border), Color.gray);
        Block right = new Block(new Rectangle(new Point(width - border, 0), border, height), Color.gray);
        Block left = new Block(new Rectangle(new Point(0, 0), border, height), Color.gray);
        top.addToGame(this);
        buttom.addToGame(this);
        right.addToGame(this);
        left.addToGame(this);
        buttom.addHitListener(ballRemover);
        LevelName levelName = new LevelName(levelInformation.levelName());
        levelName.addToGame(this);
        // adding score indicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(sCounter);
        scoreIndicator.addToGame(this);
        // adding blocks
        for (Block b : levelInformation.blocks()) {
            b.addToGame(this);
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTrackingListener);
        }

    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, sprites), runner.getGui()); // countdown before turn starts.
        this.createBallsOnTopOfPaddle(); // or a similar method
        this.running = true;
        this.runner.run(this, gui);
    }

    private void createBallsOnTopOfPaddle() {
        Rectangle paddlRectangle = new Rectangle(new Point(width / 2 - levelInformation.paddleWidth() / 2, 570),
                levelInformation.paddleWidth(), 30); // put the padlle in the middle
        Paddle paddle = new Paddle(paddlRectangle, border, width - border, gui, levelInformation.paddleSpeed());
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            Ball b = new Ball(levelInformation.ballsLocations().get(i), 10, Color.BLACK, environment);
            b.setVelocity(levelInformation.initialBallVelocities().get(i));
            b.addToGame(this);
            paddle.addBall(b);
        }
        paddle.addToGame(this);

    }

    /**
     * @return true if should stop.
     */
    public boolean shouldStop() {
        if (blockRemover.getremainingBlocks().getValue() == 0 || ballRemover.getremainingBalls().getValue() == 0) {
            return true;
        }
        this.running = false;
        return false;
    }

    /**
     * @return the number of remaining balls.
     */
    public int getremainingBalls() {
        return ballRemover.getremainingBalls().getValue();
    }

    /**
     * draw one frame one the screen.
     *
     * @param d
     */
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (blockRemover.getremainingBlocks().getValue() == 0) {
            sCounter.increase(100);
        }

        if (gui.getKeyboardSensor().isPressed("p")) {
            this.runner.run(
                    new KeyPressStoppableAnimation(gui.getKeyboardSensor(),
                            "space",
                            new EndScreen("paused -- press space to continue.")),
                    gui);
        }

    }

    /**
     * add the given collidable to the game.
     *
     * @param c
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * remove the given collidable from the environment.
     *
     * @param s
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }
}