/**
 * This is problem 2 of this assignment.
 * @author Shangheng Chen
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {
	
    public void run() {
        repairRow();
        while (!frontIsBlocked()) {
            nextRow();
            repairRow();
        }
    }

    public void nextRow() {
        move();
        turnLeft();
        while (!frontIsBlocked()) {
            move();
        }
        turnLeft();
        turnLeft();
    }

    public void repairRow() {
        int cnt = 0;
        do {
            if (cnt == 0) {
                cnt = 4;
                if (noBeepersPresent()) {
                    putBeeper();
                }
            }
            cnt--;
            move();
        } while (!frontIsBlocked());
        if (cnt == 0 && noBeepersPresent()) {
            putBeeper();
        }
        turnLeft();
    }
}
