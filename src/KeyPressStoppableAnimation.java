import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Class for Key Press Stoppable Animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private String key;
    private KeyboardSensor sensor;
    private boolean running = true;
    private boolean isAlreadyPressed = true;
    private Animation animation;

    /**
     * Constructor.
     *
     * @param sensor
     * @param key
     * @param animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.sensor = sensor;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);

        if (sensor.isPressed(key)) {
            if (!isAlreadyPressed) {
                this.running = false;
            }
        } else {
            isAlreadyPressed = false;
        }

    }

    @Override
    public boolean shouldStop() {
        return !running;
    }
}