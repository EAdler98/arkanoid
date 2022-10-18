import java.util.ArrayList;
import java.util.List;

/**
 * class for running the game.
 */
public class Ass6Game {
    /**
     * main function to run the game.
     *
     * @param args
     */
    public static void main(String[] args) {
        int width = 800, height = 600, border = 20;
        List<LevelInformation> levels;
        if (args.length == 1) {
            levels = new ArrayList<>();
            levels.add(new Level0(width, height, border));
            levels.add(new Level1(width, height, border));
            levels.add(new Level2(width, height, border));
            levels.add(new Level3(width, height, border));

        } else {
            levels = new ArrayList<>();
            for (int i = 0; i < args.length; i++) {
                try {
                    int level = Integer.parseInt(args[i]);
                    switch (level) {
                        case 1:
                            levels.add(new Level0(width, height, border));
                            break;
                        case 2:
                            levels.add(new Level1(width, height, border));
                            break;
                        case 3:
                            levels.add(new Level2(width, height, border));
                            break;
                        case 4:
                            levels.add(new Level3(width, height, border));
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        }
        AnimationRunner ar = new AnimationRunner();
        Counter counter = new Counter();
        ScoreTrackingListener stl = new ScoreTrackingListener(counter);
        GameFlow game = new GameFlow(ar, null, counter, stl, width, height);
        game.runLevels(levels);

    }
}
