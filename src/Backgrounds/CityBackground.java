package Backgrounds;

import Arkanoid.Block;
import Arkanoid.GameLevel;
import GameAnimation.Zone;
import GameCore.Sprite;
import Gui.geometry.Point;
import Gui.geometry.Rectangle;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Leonardo Rodin 207377151
 * Backgrounds.CityBackground
 */
public class CityBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        Block background = new Block(new Rectangle(0, 0, Zone.WIDTH, Zone.HEIGHT), new Color(0, 115, 0));
        background.drawOn(d);

        Point buildingP = new Point((int) (Zone.WIDTH / 17), (int) (Zone.HEIGHT / 5 * 3.5));
        d.fillRectangle((int) buildingP.getX(), (int) buildingP.getY(), Zone.WIDTH / 9,
                Zone.HEIGHT - (int) buildingP.getY());

        //draws little windows inside the building
        int windowWidth = Zone.WIDTH / 80;
        int windowHeight = (int) ((Zone.HEIGHT - (int) buildingP.getY()) / 6.5);
        int startingWindowY = (int) buildingP.getY() + Zone.HEIGHT / 70;
        for (int i = 0; i < 5; i++, startingWindowY += windowHeight + Zone.HEIGHT / 60) {
            int startingWindowX = (int) buildingP.getX() + Zone.WIDTH / 100;
            for (int j = 0; j < 4; j++, startingWindowX += windowWidth + Zone.WIDTH / 70) {
                d.setColor(Color.WHITE);
                d.fillRectangle(startingWindowX, startingWindowY, windowWidth, windowHeight);
            }
        }

        //draws an extension to the building
        d.setColor(Color.DARK_GRAY);
        int miniBuildingHeight = Zone.HEIGHT / 10;
        int miniBuildingWidth = Zone.WIDTH / 30;
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle((int) (buildingP.getX() + Zone.WIDTH / 18 - miniBuildingWidth / 2),
                (int) (buildingP.getY() - miniBuildingHeight), miniBuildingWidth, miniBuildingHeight);
        d.setColor(Color.GRAY);
        d.fillRectangle((int) ((buildingP.getX() + Zone.WIDTH / 18 - miniBuildingWidth / 2) * 1.125),
                (int) (buildingP.getY() - miniBuildingHeight), miniBuildingWidth / 3, -miniBuildingHeight * 3);
        d.setColor(new Color(255, 160, 120));
        int yLight = (int) buildingP.getY() - miniBuildingHeight * 4;
        d.fillCircle((int) buildingP.getX() + Zone.WIDTH / 18, yLight, 13);
        d.setColor(new Color(255, 50, 60));
        d.fillCircle((int) buildingP.getX() + Zone.WIDTH / 18, yLight, 9);
        d.setColor(Color.white);
        d.fillCircle((int) buildingP.getX() + Zone.WIDTH / 18, yLight, 3);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
