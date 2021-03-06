/**
 * This is Problem 1 for this assignment
 * @author Shangheng Chen
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {
    public void run() {
        move();
        move();
        turnRight();
        move();
        turnLeft();
        move();
        pickBeeper();
        turnLeft();
        turnLeft();
        move();
        turnRight();
        move();
        turnLeft();
        move();
        move();
        turnLeft();
        turnLeft();
    }

    public void turnRight() {
        for (int i = 0; i < 3; i += 1) {
            turnLeft();
        }
    }
}
