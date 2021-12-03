package Backgrounds;

import Arkanoid.Block;
import Arkanoid.GameLevel;
import GameAnimation.Zone;
import GameCore.Sprite;
import Gui.geometry.Point;
import Gui.geometry.Rectangle;
import biuoop.DrawSurface;

import java.awt.Color;

public class RainyBackground implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        Block background = new Block(new Rectangle(0, 0, Zone.WIDTH, Zone.HEIGHT), new Color(0, 128, 255));
        background.drawOn(d);
        Cloud cloud1 = new Cloud(new Point((int) (Zone.WIDTH / 10), (int) (Zone.HEIGHT / 1.8)));
        cloud1.drawOn(d);
        Cloud cloud2 = new Cloud(new Point((int) (Zone.WIDTH / 1.5), (int) (Zone.HEIGHT / 1.3)));
        cloud2.drawOn(d);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
