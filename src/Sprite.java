
import biuoop.DrawSurface;

/**
 * an interface represnting sprites which respond to time.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * add the sprites to the game.
     *
     * @param g
     */
    void addToGame(GameLevel g);
}