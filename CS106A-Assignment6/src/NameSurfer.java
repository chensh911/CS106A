// TODO: comment this file

import acm.program.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {
    private JTextField nameForType;
    private NameSurferDatabase database;
    private NameSurferGraph graph;

    public void init() {
        database = new NameSurferDatabase("res/names-data.txt");
        graph = new NameSurferGraph();
        add(graph);

        JLabel name = new JLabel("Name: ");
        add(name, NORTH);
        nameForType = new JTextField(TEXT_FIELD_WIDTH);
        nameForType.setActionCommand("Graph");
        nameForType.addActionListener(this);
        add(nameForType, NORTH);
        JButton graphButtom = new JButton("Graph");
        add(graphButtom, NORTH);
        JButton clear = new JButton("Clear");
        add(clear, NORTH);

        addActionListeners();
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Clear")) {
            graph.clear();
        } else if (command.equals("Graph")) {
            String name = convertName(nameForType.getText());
            NameSurferEntry entry = null;
            if (name != null) {
                entry = database.findEntry(name);
            }
            if (entry != null) {
                graph.addEntry(entry);
                graph.update();
            }
        }
    }


    private String convertName(String name) {
        if (name.equals("")) {
            return null;
        }
        String n = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        return n;
    }
}
