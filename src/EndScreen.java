import biuoop.DrawSurface;

/**
 * Class for showing end screen.
 */
public class EndScreen implements Animation {
    private String text;

    /**
     * Constructor.
     *
     * @param text message to show.
     */
    public EndScreen(String text) {
        this.text = text;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(20, 200, text, 40);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}