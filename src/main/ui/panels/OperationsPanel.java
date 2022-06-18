package ui.panels;

import javax.swing.*;

// Panel where add, view, delete, and complete buttons are located
public class OperationsPanel extends Panel {
    public JButton addAssignmentButton;
    public JButton viewAssignmentButton;
    public JButton deleteAssignmentButton;
    public JButton completeAssignmentButton;

    public ArrayList<JButton> buttons; 

    // initializes panel according to superclass
    public OperationsPanel() {
        super();
    }

    // MODIFIES: this
    // EFFECTS: sets panel layout to boxlayout
    @Override
    public void setPanelLayout() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    }

    // MODIFIES: this
    // EFFECTS: adds buttons to panel, inserts spacers between them
    @Override
    public void designLayout() {
        setPanelLayout();

        add(addAssignmentButton);
        spaceAndSeparate("V");
        add(viewAssignmentButton);
        spaceAndSeparate("V");
        add(deleteAssignmentButton);
        spaceAndSeparate("V");
        add(completeAssignmentButton);
        spaceAndSeparate("V");
    }

    // MODIFIES: this
    // EFFECTS: initializes buttons, sets add button to true and the rest to false
    @Override
    public void makeElements() {
        addAssignmentButton = newButton("Add New Assignment", true);
        deleteAssignmentButton = newButton("Delete Assignment", false);
        viewAssignmentButton = newButton("View Assignment Details", false);
        completeAssignmentButton = newButton("Complete Assignment", false);

        buttons = new ArrayList<addAssignmentButton, deleteAssignmentButton, viewAssignmentButton, completeAssignmentButton>;

        System.out.println(buttons.size());
    }

    // getters:
    public JButton getAddAssignmentButton() {
        return addAssignmentButton;
    }

    public JButton getViewAssignmentButton() {
        return viewAssignmentButton;
    }

    public JButton getDeleteAssignmentButton() {
        return deleteAssignmentButton;
    }

    public JButton getCompleteAssignmentButton() {
        return completeAssignmentButton;
    }
}
