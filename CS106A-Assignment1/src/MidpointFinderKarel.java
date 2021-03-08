/*
 * This is the last question for this assignment
 * @author Shangheng Chen
 */

import stanford.karel.*;

public class MidpointFinderKarel extends SuperKarel {
	
    public void run() {
        putBeeper();
        while(true) {
            if (!putRight()) {
                break;
            }
            if (!putLeft()) {
                break;
            }
        }
    }

    public boolean putRight() {
        if (frontIsBlocked()) {
            turnLeft(); // final: up
            return false;
        }
        move();
        if (beepersPresent()) {
            pickBeeper();
            turnLeft();
            turnLeft();
            move();
            turnRight(); // final: up
            return false;
        } else {
            while(beepersPresent() | frontIsClear()) {
                if (beepersPresent()) {
                    pickBeeper();
                    turnLeft();
                    turnLeft();
                    move();
                    turnRight();
                    turnRight(); // right
                    break;
                }
                move();
            }
            putBeeper();
            turnLeft();
            turnLeft(); // left
        }
        return true;
    }

    public boolean putLeft() {
        move();
        if (beepersPresent()) {
            turnRight(); // final: up
            return false;
        }
        while (!beepersPresent()) {
            move();
        }
        pickBeeper();
        turnLeft();
        turnLeft();
        move();
        putBeeper();
        return true;
    }
}
