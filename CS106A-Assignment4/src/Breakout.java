// TODO: comment this program

import acm.graphics.*;     // GOval, GRect, etc.
import acm.program.*;      // GraphicsProgram
import acm.util.*;         // RandomGenerator
import java.awt.*;         // Color
import java.awt.event.*;   // MouseEvent

public class Breakout extends GraphicsProgram {
	private GRect paddle;
	private GOval ball;
	private GLabel myLabel;
	private double vx, vy;
	private int turnCount = 0;
	private int bricks = NBRICK_COLUMNS * NBRICK_ROWS;

	public void run() {
		// Set the window's title bar text
		setTitle("CS 106A Breakout");

		// Set the canvas size.  In your code, remember to ALWAYS use getWidth()
		// and getHeight() to get the screen dimensions, not these constants!
		setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
		generateLabel();

		stage1(); // Bricks
		stage2(); // Paddle
		stage3(); // Ball

		while (bricks != 0) {
			ball.move(vx, vy);
			if (ball.getX() <= 0 || ball.getX() >= getWidth()-BALL_RADIUS*2) {
				vx = -vx;
			} else if (ball.getY() <= 0) {
				vy = -vy;
			} else if (ball.getY() >= getHeight()-BALL_RADIUS*2) {
				turnCount += 1;
				generateLabel();
				if (turnCount == 3) {
					break;
				}
				remove(ball);
				generateBall();
			}
			GObject collider = getCollidingObject();
			if (collider != null) {
				if (collider != paddle) {
					remove(collider);
					bricks -= 1;
					generateLabel();
				}
				vy = -vy;
			}
			pause(10);
		}
		if (bricks == 0) {
			GLabel label = new GLabel("YOU WIN!");
			label.setFont(SCREEN_FONT);
			add(label,(getWidth()-label.getWidth())/2,(getHeight()-label.getHeight())/2);
		} else {
			GLabel label = new GLabel("GAME OVER");
			label.setFont(SCREEN_FONT);
			add(label,(getWidth()-label.getWidth())/2,(getHeight()-label.getHeight())/2);
		}
		remove(ball);
	}


	public void stage1() {
		Color[] colorArray = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN};
		for (int i = 0; i < NBRICK_ROWS; i += 1) {
			for (int j = 0; j < NBRICK_COLUMNS; j += 1) {
				double x = BRICK_SEP * 1.5 + (BRICK_WIDTH + BRICK_SEP) * j ;
				double y = BRICK_Y_OFFSET + (BRICK_HEIGHT + BRICK_SEP)* i;
				GRect ret = new GRect(x,y,BRICK_WIDTH,BRICK_HEIGHT);
				ret.setFilled(true);
				ret.setFillColor(colorArray[i/2]);
				ret.setColor(colorArray[i/2]);
				add(ret);
			}
		}
	}

	public void stage2() {
		double x = (CANVAS_WIDTH - PADDLE_WIDTH) / 2;
		double y = CANVAS_HEIGHT - PADDLE_Y_OFFSET - PADDLE_WIDTH;
		paddle = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
		addMouseListeners();
	}

	private void generateBall() {
		double x = getWidth()/2 - BALL_RADIUS;
		double y = getHeight()/2 - BALL_RADIUS;
		ball = new GOval(x, y, BALL_RADIUS*2, BALL_RADIUS*2);
		ball.setFilled(true);
		add(ball);
	}

	public void generateLabel() {
		if (myLabel != null) {
			remove(myLabel);
		}
		myLabel = new GLabel("Score: "+(NBRICK_COLUMNS * NBRICK_ROWS - bricks)+", Turns: " + (NTURNS - turnCount), 10, 20);
		myLabel.setFont(SCREEN_FONT);
		add(myLabel);
	}

	public void stage3() {
		generateBall();
		vx = RandomGenerator.getInstance().nextDouble(VELOCITY_X_MIN, VELOCITY_X_MAX);
		vy = VELOCITY_Y;
		if (RandomGenerator.getInstance().nextBoolean(0.5)) {
			vx = -vx;
		}
	}

	private GObject getCollidingObject() {
		double x = ball.getX();
		double y = ball.getY();
		GObject collision = getElementAt(x, y);
		if (collision != null) {
			return collision;
		}
		collision = getElementAt(x+2*BALL_RADIUS, y);
		if (collision != null) {
			return collision;
		}
		collision = getElementAt(x+2*BALL_RADIUS, y+2*BALL_RADIUS);
		if (collision != null) {
			return collision;
		}
		return getElementAt(x, y+2*BALL_RADIUS);
	}

	public void mouseMoved(MouseEvent e) {
		double x=e.getX();
		paddle.move(x-paddle.getX()- PADDLE_WIDTH / 2, 0);
	}

	/**
	 * Dimensions of the canvas, in pixels
	 * These should be used when setting up the initial size of the game,
	 * but in later calculations you should use getWidth() and getHeight()
	 * rather than these constants for accurate size information.
	 */
	public static final double CANVAS_WIDTH = 420;
	public static final double CANVAS_HEIGHT = 600;

	// Stage 1: Set up the Bricks

	/**
	 * Number of bricks in each row
	 */
	public static final int NBRICK_COLUMNS = 10;

	/**
	 * Number of rows of bricks
	 */
	public static final int NBRICK_ROWS = 10;

	/**
	 * Separation between neighboring bricks, in pixels
	 */
	public static final double BRICK_SEP = 4;

	/**
	 * Width of each brick, in pixels
	 */
	public static final double BRICK_WIDTH = Math.floor(
			(CANVAS_WIDTH - (NBRICK_COLUMNS + 1.0) * BRICK_SEP) / NBRICK_COLUMNS);

	/**
	 * Height of each brick, in pixels
	 */
	public static final double BRICK_HEIGHT = 8;

	/**
	 * Offset of the top brick row from the top, in pixels
	 */
	public static final double BRICK_Y_OFFSET = 70;


	// Stage 2: Create the Paddle

	/**
	 * Dimensions of the paddle
	 */
	public static final double PADDLE_WIDTH = 60;
	public static final double PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	public static final double PADDLE_Y_OFFSET = 30;


	// Stage 3: Create the Bouncing Ball

	/**
	 * Radius of the ball in pixels
	 */
	public static final double BALL_RADIUS = 10;

	/**
	 * The ball's vertical velocity.
	 */
	public static final double VELOCITY_Y = 3.0;

	/**
	 * The ball's minimum and maximum horizontal velocity; the bounds of the
	 * initial random velocity that you should choose (randomly +/-).
	 */
	public static final double VELOCITY_X_MIN = 1.0;
	public static final double VELOCITY_X_MAX = 3.0;

	/**
	 * Animation delay or pause time between ball moves (ms)
	 */
	public static final int DELAY = 1000 / 60;


	// Stage 4: Checking for Collisions (no new constants introduced)

	// Stage 5: Polish and Finishing Up

	/** Number of turns */
	public static final int NTURNS = 3;

	/** Font to use for on-screen text */
	public static final String SCREEN_FONT = "SansSerif-BOLD-18";
}
