/** Class for tracking the score. */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * standard constructor.
     *
     * @param scoreCounter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);

    }

    /**
     * @return the current score.
     */
    public Counter getCurrentScore() {
        return currentScore;
    }
}
