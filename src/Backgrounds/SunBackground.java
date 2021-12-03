package Backgrounds;

import Arkanoid.Block;
import Arkanoid.GameLevel;
import GameAnimation.Zone;
import GameCore.Sprite;
import Gui.geometry.Point;
import Gui.geometry.Rectangle;
import Levels.Level2;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Leonardo Rodin 207377151
 * Backgrounds.SunBackground
 */
public class SunBackground implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        Block background = new Block(new Rectangle(0, 0, Zone.WIDTH, Zone.HEIGHT), Color.WHITE);
        background.drawOn(d);
        Point sunCenter = new Point(Level2.BLOCK_WIDTH * 3, (int) (Zone.HEIGHT / 4.5));
        int startingX = (int) (GameLevel.BORDER_DIM);
        int startingY = (int) (Zone.HEIGHT / 2.5);
        d.setColor(new Color(238, 232, 170));
        int finalX = startingX + Level2.BLOCK_WIDTH * 14;
        while (startingX < finalX) {
            d.drawLine((int) sunCenter.getX(), (int) sunCenter.getY(), startingX, startingY);
            startingX += 7;
        }

        d.fillCircle((int) sunCenter.getX(), (int) sunCenter.getY(), (int) (Zone.HEIGHT / 8.5));
        d.setColor(new Color(218, 165, 32));
        d.fillCircle((int) sunCenter.getX(), (int) sunCenter.getY(), (int) (Zone.HEIGHT / 10));
        d.setColor(new Color(255, 215, 0));
        d.fillCircle((int) sunCenter.getX(), (int) sunCenter.getY(), (int) (Zone.HEIGHT / 12));
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
