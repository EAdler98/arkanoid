import java.util.List;

/**
 * Level inforamtion interface.
 */
public interface LevelInformation {
    /**
     * @return number of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return list of balls velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return paddle speed
     */
    int paddleSpeed();

    /**
     * @return paddlewidth
     */
    int paddleWidth();

    /**
     * @return the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and
     * location.
     *
     * @return list of blocks
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return number of blocks
     */
    int numberOfBlocksToRemove();

    /**
     *
     * @return list of balls locations.
     */
    List<Point> ballsLocations();
}