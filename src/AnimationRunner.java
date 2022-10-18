import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.DrawSurface;

/**
 * Class for Annimation runner.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond = 60;
    private Sleeper sleeper;

    /**
     * run the animation.
     *
     * @param animation
     * @param gui
     */
    public void run(Animation animation, GUI gui) {
        this.gui = gui;
        sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * @return GUI.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * set GUI.
     *
     * @param title
     * @param width
     * @param height
     */
    public void setGui(String title, int width, int height) {
        gui = new GUI(title, width, height);
    }
}