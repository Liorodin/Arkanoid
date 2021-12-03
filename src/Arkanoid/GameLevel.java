package Arkanoid;

import GameAnimation.Animation;
import GameAnimation.AnimationRunner;
import GameAnimation.KeyPressStoppableAnimation;
import GameCore.Collidable;
import GameCore.Counter;
import GameCore.Sprite;
import GameCore.Velocity;
import Gui.geometry.Rectangle;
import GameAnimation.Zone;

import GameCore.LevelInformation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;

/**
 * @author Leonardo Rodin 207377151
 * game setups all the following and runs the.
 */
public class GameLevel implements Animation {

    // Finals
    public static final int COLOR_RANGE = 256;
    public static final int BANNER_HEIGHT = Zone.HEIGHT / 30;
    public static final int BORDER_DIM = Zone.HEIGHT / 40;

    //fields
    private biuoop.KeyboardSensor keyboardSensor;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter scoreCounter;
    private AnimationRunner runner;
    private LevelInformation levelInformation;
    private boolean running;
    private Counter lifeCounter;
    private Paddle paddle;

    /**
     * creates a level for the game.
     *
     * @param levelInformation the information of the level
     * @param kr               the keyboard sensor
     * @param ar               the AnimationRunner that will run the animations
     * @param scoreCounter     the score counter
     * @param lifeCounter      the life counter
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor kr, AnimationRunner ar, Counter scoreCounter,
                     Counter lifeCounter) {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        this.levelInformation = levelInformation;
        blockCounter = new Counter();
        ballCounter = new Counter();
        this.lifeCounter = lifeCounter;
        this.scoreCounter = scoreCounter;
        runner = ar;
        keyboardSensor = kr;
    }

    /**
     * @param c the collidable needed to be add
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * @param s the sprite needed to be add
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * removes a collidable object from the game.
     *
     * @param c the object wanted to be removed
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * @return how many lives left for the player
     */
    public int getLifeCount() {
        return lifeCounter.getValue();
    }

    /**
     * @return how many blocks left to remove
     */
    public int getBlockCount() {
        return blockCounter.getValue();
    }

    /**
     * @return the Counter of balls
     */
    public Counter getBallCounter() {
        return ballCounter;
    }

    /**
     * removes a GameCore.Sprite object from the game.
     *
     * @param s the sprite wanted to be remove
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * adds visual borders to the game.
     */
    private void addBordersToGame() {
        Block background = new Block(new Rectangle(0, 0, Zone.WIDTH, Zone.HEIGHT));
        //adds a background image
        Sprite backImage = levelInformation.getBackground();
        backImage.addToGame(this);
        //adds a ball remover for the background
        background.addHitListener(new BallRemover(this, ballCounter));
        environment.addCollidable(background);
        //adds a banner on top for inner messages

        Block banner = new Block(new Rectangle(0, 0, Zone.WIDTH, GameLevel.BANNER_HEIGHT), Color.WHITE);
        sprites.addSprite(banner);

        //creates upper border
        Block upperBlock = new Block(new Rectangle(0, BANNER_HEIGHT, Zone.WIDTH,
                BORDER_DIM), Color.gray);
        upperBlock.addToGame(this);
        //creates left border
        Block leftBlock = new Block(new Rectangle(0, BANNER_HEIGHT, BORDER_DIM,
                Zone.HEIGHT - BANNER_HEIGHT), Color.gray);
        leftBlock.addToGame(this);
        //creates right border
        Block rightBlock = new Block(new Rectangle(Zone.WIDTH - BORDER_DIM, BANNER_HEIGHT,
                BORDER_DIM, Zone.HEIGHT - BANNER_HEIGHT), Color.gray);
        rightBlock.addToGame(this);
    }

    /**
     * used to create a paddle in the middle with the new rectangle.
     *
     * @return a rectangle
     */
    private Rectangle createInMiddle() {
        int x = Zone.WIDTH / 2 - levelInformation.paddleWidth() / 2;
        //creates the shape of the paddle
        Rectangle paddleRec = new Rectangle(x, Zone.HEIGHT - 20, levelInformation.paddleWidth(), 20);
        paddleRec.setColor(Color.yellow);
        return paddleRec;
        //return new Paddle(paddleRec, KEYBOARD, LEVEL_INFORMATION.paddleSpeed());
    }

    /**
     * adds a new paddle to the game.
     */
    private void addPaddleToGame() {
        paddle = new Paddle(this, createInMiddle(), keyboardSensor, levelInformation.paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * moves paddle to the middle of the screen.
     */
    public void movePaddleToMiddle() {
        paddle.setNewRectangle(createInMiddle());
    }

    /**
     * adds visual balls to the game.
     */
    public void addBallsToGame() {
        //gets the velocity of each ball from LEVEL_INFORMATION
        List<Velocity> initialBallVelocities = levelInformation.initialBallVelocities();
        //creates balls accordingly to LEVEL_INFORMATION
        int ballRadius = 5;
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            //creates the ball above the paddle
            Ball ball = new Ball((int) (Zone.WIDTH / 2), Zone.HEIGHT - levelInformation.paddleHeight() * 1.5,
                    ballRadius, Color.white);
            ball.setVelocity(initialBallVelocities.get(i));
            ball.setGameEnvironment(environment);
            ball.addToGame(this);
        }
        //adds the amount of balls the ball counter of the level
        ballCounter.increase(initialBallVelocities.size());
    }

    /**
     * Initialize a new game: create the Blocks and Arkanoid.Ball (and Arkanoid.Paddle) and add them to the game.
     */
    public void initialize() {
        addBordersToGame();
        addBallsToGame();
        addPaddleToGame();
        BlockRemover blockRemover = new BlockRemover(this, blockCounter);
        //adds a score indicator
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(scoreCounter);
        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreCounter);
        scoreIndicator.addToGame(this);
        //adds a life indicator
        LivesIndicator livesIndicator = new LivesIndicator(lifeCounter);
        livesIndicator.addToGame(this);
        //adds a name indicator
        NameIndicator nameIndicator = new NameIndicator(levelInformation.levelName());
        nameIndicator.addToGame(this);
        //gets all the block needed for the level from LEVEL_INFORMATION
        List<Block> blockList = levelInformation.blocks();

        //adds blocks to the game level
        if (blockList != null) {
            for (Block block : blockList) {
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTrackingListener);
                block.addToGame(this);
            }
        }
        //adds the amount of blocks that is needed to be removed to the block counter of the level
        blockCounter.increase(levelInformation.numberOfBlocksToRemove());
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //activates sprites
        sprites.drawAllOn(d);
        sprites.notifyAllTimePassed();
        //bonus for level completion
        if (blockCounter.getValue() == 0) {
            scoreCounter.increase(100);
        }
        //checks for stop condition
        if (ballCounter.getValue() == 0) {
            lifeCounter.decrease(1);
        }
        if (ballCounter.getValue() == 0 || blockCounter.getValue() == 0) {
            running = false;
        }
        //checks for pause condition
        if (keyboardSensor.isPressed("p")) {
            runner.run(new KeyPressStoppableAnimation(keyboardSensor, "space", new PauseScreen()));
        }
    }

    @Override
    public boolean shouldStop() {
        return !running;
    }

    /**
     * Runs the game -- start the animation loop.
     */
    public void run() {
        //starts a countdown animation the counts from 3 to 1, holds on each number for 2 seconds
        runner.run(new CountdownAnimation(2, 3, sprites, levelInformation));
        running = true;
        // use our runner to run the current animation -- which is one turn of the game
        runner.run(this);
        //System.out.println("Your score is: " + SCORE_COUNTER.getValue());
    }
}
