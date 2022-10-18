/**
 * a BlockRemover is in charge of removing blocks from the game, as well as
 * keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * standard constructor.
     *
     * @param game
     * @param removedBlocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
    }

    /**
     * @return remining blocks.
     */
    public Counter getremainingBlocks() {
        return remainingBlocks;
    }
}