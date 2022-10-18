import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Pause screen.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Cnstructor.
     *
     * @param k
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
        KeyPressStoppableAnimation kps = new KeyPressStoppableAnimation(k, "paused -- press space to continue", this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}