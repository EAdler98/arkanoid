import biuoop.KeyboardSensor;
import biuoop.GUI;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;

/**
 * a class represintng a paddle.
 */
public class Paddle implements Sprite, Collidable {
    private Rectangle rect;
    private biuoop.KeyboardSensor keyboard;
    private int leftBorder, rightBorder = 0;
    private java.util.List<Ball> balls;
    private int speed;

    /**
     * constructor for paddle.
     *
     * @param rect        - size and location of the paddle
     * @param leftBorder  - left boundary of the paddle
     * @param rightBorder - right boundary of the paddle
     * @param gui
     * @param speed       - the step of the paddle for each press
     */
    public Paddle(Rectangle rect, int leftBorder, int rightBorder, GUI gui, int speed) {
        this.rect = rect;
        keyboard = gui.getKeyboardSensor();
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        balls = new ArrayList<Ball>(); // we want the paddle to be aware to the balls.
        this.speed = speed;
    }

    /**
     * add ball to the paddle's list.
     *
     * @param ball
     */
    public void addBall(Ball ball) {
        balls.add(ball);
    }

    /**
     * move thbe paddle one step left.
     */
    public void moveLeft() {
        if (rect.getUpperLeft().getX() <= leftBorder) {
            return; // don't move the paddle if the paddles is on the edge
        }

        Point newLocation = new Point(rect.getUpperLeft().getX() - speed, rect.getUpperLeft().getY());
        for (Ball b : balls) {
            // if the ball is in paddle's height and adjacant from the left border
            if (b.getX() + b.getSize() + speed >= rect.getUpperLeft().getX() && b.getX() <= rect.getUpperRight().getX()
                    && b.getY() + b.getSize() - 5 > rect.getUpperLeft().getY()) {
                Velocity v = b.getVelocity();
                // if (b.getX() - b.getSize() <= leftBorder
                // && b.getX() + b.getSize() >= rect.getUpperLeft().getX()) {
                // return; // if the ball is between the paddle in the border - don't move the
                // paddle
                // }
                // let the paddle push the ball to the left
                if (rect.getUpperLeft().getX() - b.getSize() - speed >= leftBorder) {
                    b.setX(Math.max((int) rect.getUpperLeft().getX() - b.getSize() - speed, leftBorder + b.getSize()));
                }
                // update the velocity
                if (v.getDx() <= 0) {
                    b.setVelocity(new Velocity(-speed, v.getDy()));

                } else {
                    b.setVelocity(new Velocity(-v.getDx(), v.getDy()));
                }
            }
        }

        rect = new Rectangle(newLocation, rect.getWidth(), rect.getHeight());

    }

    /**
     * move thbe paddle one step right.
     */
    public void moveRight() {
        if (rect.getUpperRight().getX() >= rightBorder) {
            return;
        }
        Point newLocation = new Point(rect.getUpperLeft().getX() + speed, rect.getUpperLeft().getY());
        for (Ball b : balls) {
            // if the ball is in paddle's height and adjacant from the right side
            if (b.getX() - b.getSize() - speed <= rect.getUpperRight().getX() && b.getX() >= rect.getUpperLeft().getX()
                    && b.getY() + b.getSize() - 5 > rect.getUpperLeft().getY()) {
                Velocity v = b.getVelocity();
                // if (b.getX() + b.getSize() >= rightBorder
                // && b.getX() - 2 * b.getSize() <= rect.getUpperRight().getX()) {
                // return; // if the ball is between the paddle in the border - don't move the
                // paddle
                // }
                // let the paddle push the ball to the right
                if (rect.getUpperRight().getX() + b.getSize() + speed < rightBorder) {
                    b.setX(Math.min((int) rect.getUpperRight().getX() + b.getSize() + speed,
                            rightBorder - b.getSize()));
                }
                // update the velocity
                if (v.getDx() <= 0) {
                    b.setVelocity(new Velocity(-v.getDx(), v.getDy()));

                } else {
                    b.setVelocity(new Velocity(speed, v.getDy()));
                }
            }
        }
        rect = new Rectangle(newLocation, rect.getWidth(), rect.getHeight());
    }

    /**
     * Sprite.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * draw the paddle.
     *
     * @param surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.MAGENTA);
        surface.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight()); // draw the rect required
    }

    /**
     * collidable.
     *
     * @return rect
     */
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    @Override
    public Velocity hit(Ball b, Point collisionPoint, Velocity currentVelocity) {
        Velocity v = horizontalHit(collisionPoint, currentVelocity);
        v = vreticalHit(collisionPoint, v);
        return v;
    }

    /**
     * taking care of the vertical hits os the paddle.
     *
     * @param collisionPoint
     * @param currentVelocity
     * @return updated velocity
     */
    public Velocity vreticalHit(Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        double speed = Math.sqrt(dx * dx + dy * dy);
        if (rect.getUpperHorizontal().isPointInSegment(collisionPoint)
                || rect.getLowerHorizontal().isPointInSegment(collisionPoint)) {
            if (collisionPoint.getX() <= rect.getUpperLeft().getX() + 0.2 * rect.getWidth()) {
                return Velocity.fromAngleAndSpeed(300, speed);
            }
            if (collisionPoint.getX() <= rect.getUpperLeft().getX() + 0.4 * rect.getWidth()) {
                return Velocity.fromAngleAndSpeed(330, speed);
            }
            if (collisionPoint.getX() <= rect.getUpperLeft().getX() + 0.6 * rect.getWidth()) {
                return Velocity.fromAngleAndSpeed(0, speed);
            }
            if (collisionPoint.getX() <= rect.getUpperLeft().getX() + 0.8 * rect.getWidth()) {
                return Velocity.fromAngleAndSpeed(30, speed);
            } else {
                return Velocity.fromAngleAndSpeed(60, speed);
            }
        }
        return new Velocity(dx, dy);
    }

    /**
     * taking care of the horizonatl hits os the paddle.
     *
     * @param collisionPoint
     * @param currentVelocity
     * @return updated velocity
     */
    public Velocity horizontalHit(Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        if (rect.getLeftVertical().isPointInSegment(collisionPoint)) {
            if (collisionPoint.getX() <= rect.getUpperLeft().getX()) {
                dx = -dx;
            } else {
                dx = -dx;
            }
        }
        if (rect.getRightVertical().isPointInSegment(collisionPoint)) {
            if (collisionPoint.getX() >= rect.getUpperRight().getX()) {
                dx = -dx;
            } else {
                dx = -dx;
            }
        }
        return new Velocity(dx, dy);
    }

    /**
     * Add this paddle to the game.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}