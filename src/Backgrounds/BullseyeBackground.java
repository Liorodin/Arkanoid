package Backgrounds;

import Arkanoid.Block;
import Arkanoid.GameLevel;
import GameAnimation.Zone;
import GameCore.Sprite;
import Gui.geometry.Line;
import Gui.geometry.Rectangle;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Leonardo Rodin 207377151
 * Backgrounds.BullseyeBackground
 */
public class BullseyeBackground implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        int BLOCK_SIZE = (int) (Zone.WIDTH / 22);
        Block background = new Block(new Rectangle(0, 0, Zone.WIDTH, Zone.HEIGHT), Color.BLACK);
        background.drawOn(d);

        d.setColor(Color.BLUE);
        //the length of the lines in the bullseye drawing
        int yValue = GameLevel.BANNER_HEIGHT * 7;
        double bullseyeLineLength = (new Line((int) (Zone.WIDTH / 2), GameLevel.BANNER_HEIGHT + GameLevel.BORDER_DIM,
                (int) (Zone.WIDTH / 2), yValue - 5)).length();

        //draws upper line
        d.drawLine(Zone.WIDTH / 2, GameLevel.BANNER_HEIGHT, Zone.WIDTH / 2, yValue - 5);
        //draws left line
        d.drawLine(((Zone.WIDTH - BLOCK_SIZE) / 2 - 5), yValue + BLOCK_SIZE / 2,
                (int) ((Zone.WIDTH - BLOCK_SIZE) / 2 - 5 - bullseyeLineLength * 1.5), yValue + BLOCK_SIZE / 2);
        //draws right line
        d.drawLine(((Zone.WIDTH - BLOCK_SIZE) / 2 + 5 + BLOCK_SIZE), yValue + BLOCK_SIZE / 2,
                (int) ((Zone.WIDTH - BLOCK_SIZE) / 2 + 5 + BLOCK_SIZE + bullseyeLineLength * 1.5),
                yValue + BLOCK_SIZE / 2);
        //draws bottom line
        d.drawLine(Zone.WIDTH / 2, yValue + BLOCK_SIZE + 5, Zone.WIDTH / 2,
                (int) (yValue + BLOCK_SIZE + 5 + bullseyeLineLength * 1.5));

        //draws 3 rings across the center
        d.drawCircle(Zone.WIDTH / 2, (yValue + BLOCK_SIZE / 2), Zone.WIDTH / 15);
        d.drawCircle(Zone.WIDTH / 2, (yValue + BLOCK_SIZE / 2), Zone.WIDTH / 10);
        d.drawCircle(Zone.WIDTH / 2, (yValue + BLOCK_SIZE / 2), Zone.WIDTH / 7);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
