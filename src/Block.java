import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * a calss representation of a block.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private List<HitListener> hitListeners;
    private Rectangle rect;
    private java.awt.Color color;

    /**
     * A standrat constructor for a block with default color.
     *
     * @param rect
     */
    public Block(Rectangle rect) {
        this.rect = rect;
        this.color = Color.gray;
        hitListeners = new ArrayList<>();
    }

    /**
     * A standrat constructor for a block with color.
     *
     * @param color
     * @param rect
     */
    public Block(Rectangle rect, java.awt.Color color) {
        this.rect = rect;
        this.color = color;
        hitListeners = new ArrayList<>();
    }

    /**
     * remove block from the game.
     *
     * @param game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * @return the "collision shape" of the object
     */
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity v = horizontalHit(collisionPoint, currentVelocity);
        v = vreticalHit(collisionPoint, v);
        this.notifyHit(hitter);
        return v;
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * notify all the listeners about the hit.
     *
     * @param hitter
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * check for a hit in the top or buttom lines of the rectangle.
     *
     * @param collisionPoint
     * @param currentVelocity
     * @return the new vertical velocity of the object
     */
    public Velocity vreticalHit(Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        if (rect.getUpperHorizontal().isPointInSegment(collisionPoint)
                || rect.getLowerHorizontal().isPointInSegment(collisionPoint)) {
            dy = -dy;
        }
        return new Velocity(dx, dy);
    }

    /**
     * check for a hit in the top or side lines of the rectangle.
     *
     * @param collisionPoint
     * @param currentVelocity
     * @return the new horizontal velocity of the object
     */
    public Velocity horizontalHit(Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        if (rect.getLeftVertical().isPointInSegment(collisionPoint)
                || rect.getRightVertical().isPointInSegment(collisionPoint)) {
            dx = -dx;
        }
        return new Velocity(dx, dy);
    }

    /**
     * draw on a surface.
     *
     * @param d
     */
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle(
                (int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight()); // fill the rect required
        d.setColor(Color.gray);
        d.drawRectangle(
                (int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight()); // draw the rect required
    }

    /**
     * time passed.
     */
    @Override
    public void timePassed() {

    }

    @Override
    /**
     * add this block to the game.
     *
     * @param game the game to add to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

}
