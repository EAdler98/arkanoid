import biuoop.DrawSurface;

/**
 * Class for a sprite indicating the score.
 */
public class ScoreIndicator implements Sprite {

    private String text;
    private Counter score;

    /**
     * standard Constructor.
     *
     * @param score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
        text = "score: " + score.getValue();
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d
     */
    public void drawOn(DrawSurface d) {
        d.drawText(100, 20, text, 15);
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
        text = "score: " + score.getValue();
    }

    /**
     * add the sprites to the game.
     *
     * @param g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
