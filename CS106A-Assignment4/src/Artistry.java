// Part 1


import acm.graphics.*;
import acm.program.*;
import acm.util.RandomGenerator;

import java.awt.*;

public class Artistry extends GraphicsProgram {
	public void run() {
		setSize(300, 400);

		setBackground(new Color(1, 1, 1));

		GOval moon = new GOval(10,10,100,100);
		moon.setFilled(true);
		moon.setFillColor(new Color(255, 255, 204));
		moon.setColor(new Color(255, 255, 204));
		add(moon);

		for (int i = 0; i < 30; i += 1) {
			Double x = RandomGenerator.getInstance().nextDouble(0.0, 300.0);
			Double y = RandomGenerator.getInstance().nextDouble(0.0, 400.0);
			Double size = RandomGenerator.getInstance().nextDouble(5, 15);
			GOval star = new GOval(x, y, size, size);
			star.setFilled(true);
			star.setFillColor(Color.WHITE);
			add(star);
		}

		GLabel myName = new GLabel("Shangheng Chen", 200, 390);
		myName.setColor(Color.YELLOW);
		add(myName);
	}
}
