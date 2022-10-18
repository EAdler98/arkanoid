import java.awt.Color;

import biuoop.DrawSurface;

/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private int countFrom;
    private double numOfSeconds;
    private SpriteCollection sprites;
    private double passedTime;
    private long startTime;

    /**
     * Constructor.
     *
     * @param numOfSeconds
     * @param countFrom
     * @param gameScreen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        sprites = gameScreen;
        this.startTime = System.currentTimeMillis();
        passedTime = 0;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        passedTime = (System.currentTimeMillis() - startTime);
        int number = (int) Math.ceil(countFrom - (passedTime / 1000) * (float) countFrom / numOfSeconds);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        d.setColor(Color.darkGray);
        d.drawText(370, 300, Integer.toString(number), 150);

    }

    @Override
    public boolean shouldStop() {
        if (passedTime / 1000 >= numOfSeconds) {
            return true;
        }
        return false;
    }
}