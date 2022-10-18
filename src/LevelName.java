import biuoop.DrawSurface;

/**
 * Class for a level Name.
 */
public class LevelName implements Sprite {

    private String text;

    /**
     * standard Constructor.
     *
     * @param text
     */
    public LevelName(String text) {
        this.text = text;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d
     */
    public void drawOn(DrawSurface d) {
        d.drawText(600, 20, text, 15);
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {

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