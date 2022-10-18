import java.util.List;
import biuoop.KeyboardSensor;

/**
 * Calss for GameFlow.
 */
public class GameFlow {

    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private ScoreTrackingListener score;
    private Counter counter;

    /**
     * Constructor.
     *
     * @param ar
     * @param ks
     * @param counter
     * @param score
     * @param width
     * @param height
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, Counter counter, ScoreTrackingListener score, int width,
            int height) {
        animationRunner = ar;
        this.score = score;
        this.counter = counter;
        animationRunner.setGui("Ärkanold", width, height);
        keyboardSensor = animationRunner.getGui().getKeyboardSensor();

    }

    /**
     * run the levels in levels list.
     *
     * @param levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.counter);
            level.initialize();
            while (!level.shouldStop()) {
                level.run();
            }

            if (level.getremainingBalls() == 0) {

                animationRunner.run(
                        new KeyPressStoppableAnimation(keyboardSensor,
                                "space",
                                new EndScreen("Game Over. Your score is " + score.getCurrentScore().getValue())),
                        animationRunner.getGui());
                System.exit(0);
            }

        }
        animationRunner.run(
                new KeyPressStoppableAnimation(keyboardSensor,
                        "space",
                        new EndScreen("You Win! Your score is " + score.getCurrentScore().getValue())),
                animationRunner.getGui());

    }
}