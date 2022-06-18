package ui.panels;

import javax.swing.*;

// Panel where assignment details are displayed
public class DisplayDetailsPanel extends Panel {
    public JTextArea displayArea;

    // initializes panel according to superclass
    public DisplayDetailsPanel() {
        super();
    }

    // sets panel layout to box layout
    @Override
    public void setPanelLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    // MODIFIES: this
    // EFFECTS: adds displayArea to panel, calls to set layout
    @Override
    public void designLayout() {
        add(displayArea);
        setPanelLayout();
    }

    // MODIFIES: this
    // EFFECTS: initializes display area, sets editable to true
    @Override
    public void makeElements() {
        displayArea = new JTextArea(15, 20);
        displayArea.setEditable(true);
    }

    // getters:
    public JTextArea getDisplayArea() {
        return displayArea;
    }
}
