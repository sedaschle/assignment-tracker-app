package ui.panels;

import javax.swing.*;

// Panel where save and load buttons are located
public class SaveLoadPanel extends Panel {
    public JButton saveAssignmentButton;
    public JButton loadAssignmentButton;

    // initializes panel according to superclass
    public SaveLoadPanel() {
        super();
    }

    // MODIFIES: this
    // EFFECTS: sets panel layout to boxlayout
    @Override
    public void setPanelLayout() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    }

    // MODIFIES: this
    // EFFECTS: adds save and load buttons to panels, inserts spacer in between
    @Override
    public void designLayout() {
        setPanelLayout();

        add(saveAssignmentButton);
        spaceAndSeparate("H");
        add(loadAssignmentButton);
    }

    // MODIFIES: this
    // EFFECTS: initializes save assignments button and load assignments button, enables both
    @Override
    public void makeElements() {
        saveAssignmentButton = newButton("Save Assignments", true);
        loadAssignmentButton = newButton("Load From File", true);
    }

    // getters:
    public JButton getSaveAssignmentButton() {
        return saveAssignmentButton;
    }

    public JButton getLoadAssignmentButton() {
        return loadAssignmentButton;
    }
}
