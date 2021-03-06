/**
 * This is problem 3 of this assignment.
 * @author Shangheng Chen
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
	public static int status = 1;

	public void run() {
	    boolean changeSuccess = true;
	    while (changeSuccess) {
	        while(!frontIsBlocked()) {
	            moveAndPut();
            }
	        changeSuccess = leftChange();
	        if (!changeSuccess) {
	            if (status == 1) {
	                putBeeper();
                }
	            break;
            }
            while(!frontIsBlocked()) {
                moveAndPut();
            }
            changeSuccess = rightChange();
            if (status == 1 && !changeSuccess) {
                putBeeper();
            }
        }
    }

    public void moveAndPut() {
	    if (status == 1) {
	        putBeeper();
	        move();
	        status = 0;
        } else {
	        move();
	        status = 1;
        }
    }

    public boolean leftChange() {
	    turnLeft();
	    if (frontIsBlocked()) {
	        return false;
        }
	    moveAndPut();
	    turnLeft();

	    return true;
    }

    public boolean rightChange() {
	    turnRight();
	    if (frontIsBlocked()) {
	        return false;
        }
	    moveAndPut();
	    turnRight();

	    return true;
    }
}
