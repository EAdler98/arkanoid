import biuoop.DrawSurface;

/**
 * Animation interface.
 */
public interface Animation {
    /**
     * draw one frame one the screen.
     *
     * @param d
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return true if the animation shold stop. otherwise false.
     */
    boolean shouldStop();
}