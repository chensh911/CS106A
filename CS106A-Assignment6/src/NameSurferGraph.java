// Author Shangheng Chen

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	private ArrayList<NameSurferEntry> nameWithRanks = new ArrayList<>();

	public NameSurferGraph() {
		addComponentListener(this);
		update();
	}


	public void clear() {
		nameWithRanks = new ArrayList<>();
		removeAll();
		drawBackground();
	}


	public void addEntry(NameSurferEntry entry) {
		nameWithRanks.add(entry);
	}

	public void update() {
		removeAll();
		drawBackground();
		drawRankLine();
	}

	private void drawRankLine() {
		Color[] color = {Color.black, Color.red, Color.blue, Color.magenta};
		int i = 0;
		for (NameSurferEntry entry : nameWithRanks) {
			Color entryColor = color[i%4];
			i++;
			int width = getWidth() / 11;
			double yIncrements = (getHeight() - (GRAPH_MARGIN_SIZE*2.0)) / MAX_RANK;
			double rankPre = entry.getRank(0)*yIncrements+GRAPH_MARGIN_SIZE;
			String namePlusRanking = entry.getName()+" "+entry.getRank(0);
			if (entry.getRank(0) == 0) {
				rankPre = getHeight() - GRAPH_MARGIN_SIZE;
				namePlusRanking = entry.getName() + " *";
			}
			GLabel label = new GLabel(namePlusRanking, 0, rankPre);
			label.setColor(entryColor);

			add(label);

			for (int j = 1; j < 11; j++) {
				double rankCur = entry.getRank(j)*yIncrements+GRAPH_MARGIN_SIZE;
				namePlusRanking = entry.getName()+" "+entry.getRank(j);
				if (entry.getRank(j) == 0) {
					rankCur = getHeight() - GRAPH_MARGIN_SIZE;
					namePlusRanking = entry.getName() + " *";
				}
				GLine line = new GLine(width*(j-1), rankPre, width*j, rankCur);
				line.setColor(entryColor);
				add(line);

				label = new GLabel(namePlusRanking, 0, rankCur);
				label.setColor(entryColor);
				label = new GLabel(entry.getName()+" "+entry.getRank(j), width*j, rankCur);
				label.setColor(entryColor);
				add(label);

				rankPre = rankCur;
			}
		}
	}

	private void drawBackground() {
		int width = getWidth() / 11;
		// add upper and lower margin lines
		add(new GLine(0, getHeight()-GRAPH_MARGIN_SIZE, getWidth(), getHeight()-GRAPH_MARGIN_SIZE));
		add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));

		// draw vertical lines and decade labels
		double verticalStart = 0;
		int startDate = START_DECADE;
		for(int i = 0; i < 11; i++) {
			add(new GLine(verticalStart, 0, verticalStart, getHeight()));
			add(new GLabel(Integer.toString(startDate), verticalStart, getHeight()-(GRAPH_MARGIN_SIZE/4)));
			// increment date value
			startDate += 10;
			// increment horizontal decade divider
			verticalStart += width;
		}
	}

	/* Implementation of the ComponentListener interface for updating when the window is resized */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
