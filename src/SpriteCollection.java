import java.util.ArrayList;
import biuoop.DrawSurface;

/**
 * a class for represnting sprite collection.
 */
public class SpriteCollection {

    private java.util.List<Sprite> sprites;

    /**
     * constructor. intialize sprites list.
     */
    public SpriteCollection() {
        sprites = new ArrayList<Sprite>();
    }

    /**
     * asd sprite.
     *
     * @param s sprite
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * remove sprite.
     *
     * @param s
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).timePassed();
        }

    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}