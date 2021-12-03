package Levels;

import GameCore.LevelInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leonardo Rodin 207377151
 * a LevelInformation factory
 */
public class LevelFactory {

    /**
     * the user gives an index and gets a matching level.
     *
     * @param index the index of the level
     * @return the desired level
     */
    public static LevelInformation getLevel(int index) {
        if (index == 1) {
            return new Level1();
        } else if (index == 2) {
            return new Level2();
        } else if (index == 3) {
            return new Level3();
        } else if (index == 4) {
            return new Level4();
        }
        return null;
    }

    /**
     * @return a list of all the levels
     */
    public static List<LevelInformation> getAllLevels() {
        List<LevelInformation> levelInformationList = new ArrayList<>();
        levelInformationList.add(new Level1());
        levelInformationList.add(new Level2());
        levelInformationList.add(new Level3());
        levelInformationList.add(new Level4());
        return levelInformationList;
    }
}
