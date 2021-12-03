import GameAnimation.AnimationRunner;
import GameAnimation.Zone;
import GameAnimation.GameFlow;
import GameCore.LevelInformation;
import Levels.LevelFactory;
import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;

/**
 * runs an "arkanoid" like game.
 */
public class Ass6Game {

    private static final int FPS = 60;

    /**
     * @param args command line args
     */
    public static void main(String[] args) {
        int levelCount = 4;
        GUI gui = new GUI("Arkanoid", Zone.WIDTH, Zone.HEIGHT);
        AnimationRunner ar = new AnimationRunner(gui, FPS);
        GameFlow gameFlow = new GameFlow(ar, gui.getKeyboardSensor());
        List<LevelInformation> levelList = new ArrayList<>();
        if (args.length == 0) {
            levelList = LevelFactory.getAllLevels();
        } else {
            for (String arg : args) {
                int index = -1;
                try {
                    index = Integer.parseInt(arg);
                } catch (Exception ignored) {
                    System.out.print("");
                }
                if (index > 0 && index <= levelCount) {
                    levelList.add(LevelFactory.getLevel(index));
                }
            }
        }
        //checks if the list was created
        if (levelList.isEmpty()) {
            System.out.println("No levels were able to be added");
            gui.close();
        }
        gameFlow.runLevels(levelList);
        gui.close();
    }
}