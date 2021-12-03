package Backgrounds;

import Arkanoid.GameLevel;
import GameAnimation.Zone;
import GameCore.Sprite;
import Gui.geometry.Point;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Leonardo Rodin 207377151
 * Backgrounds.Cloud a cloud visual effect
 */
public class Cloud implements Sprite {

    //fields
    private Point cloudPoint;

    /**
     * constructor.
     *
     * @param start the start of the cloud
     */
    public Cloud(Point start) {
        cloudPoint = start;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(224, 224, 224));
        int x = (int) (cloudPoint.getX()) - Zone.WIDTH / 80;
        for (int i = 0; i < 10; i++, x += Zone.WIDTH / 80) {
            d.drawLine(x, (int) (cloudPoint.getY()), x - Zone.WIDTH / 32, Zone.HEIGHT);
        }
        d.fillCircle((int) (cloudPoint.getX()), (int) (cloudPoint.getY()), Zone.WIDTH / 40);
        d.fillCircle((int) (cloudPoint.getX()) + Zone.WIDTH / 53, (int) (cloudPoint.getY()) + Zone.WIDTH / 32,
                Zone.WIDTH / 40);
        d.setColor(new Color(192, 192, 192));
        d.fillCircle((int) (cloudPoint.getX()) + Zone.WIDTH / 26, (int) (cloudPoint.getY()) - Zone.WIDTH / 80,
                Zone.WIDTH / 32);
        d.setColor(new Color(160, 160, 160));
        d.fillCircle((int) (cloudPoint.getX()) + Zone.WIDTH / 16, (int) (cloudPoint.getY()) + Zone.WIDTH / 53,
                Zone.WIDTH / 40);
        d.setColor(new Color(128, 128, 128));
        d.fillCircle((int) (cloudPoint.getX()) + Zone.WIDTH / 13, (int) (cloudPoint.getY()) - Zone.WIDTH / 53,
                Zone.WIDTH / 26);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
