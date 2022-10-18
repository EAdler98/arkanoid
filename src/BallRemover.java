/**
 * a BallRemover is in charge of removing balls from the game, as well as
 * keeping count
 * of the number of blocks that remain.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructor.
     *
     * @param game
     * @param remainingBalls
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }

    /**
     * @return remining ball.
     */
    public Counter getremainingBalls() {
        return remainingBalls;
    }
}