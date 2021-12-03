package GameAnimation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Leonardo Rodin 207377151
 * GameAnimation.KeyPressStoppableAnimation decorator class
 */
public class KeyPressStoppableAnimation implements Animation {

    // Fields
    private KeyboardSensor keyboard;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * constructor.
     *
     * @param sensor    the  keyboard sensor
     * @param key       the key that will stop the animation
     * @param animation the desired animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        keyboard = sensor;
        this.key = key;
        this.animation = animation;
        stop = false;
        isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (!(keyboard.isPressed(key))) {
            isAlreadyPressed = false;
        }
        if (keyboard.isPressed(key) && !isAlreadyPressed) {
            stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
}
