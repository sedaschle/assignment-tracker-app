package ui.panels;

import javax.swing.*;

// Panel where details for new assignments are inputted
public class NewAssignmentPanel extends Panel {
    public JTextField assignmentName;
    public JTextField assignmentCourse;
    public JTextField assignmentDeadline;
    public JTextField assignmentPlatform;

    // initializes panel according to superclass
    public NewAssignmentPanel() {
        super();
    }

    // MODIFIES: this
    // EFFECTS: sets layout to boxlayout
    @Override
    public void setPanelLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    // MODIFIES: this
    // EFFECTS: calls to set layout, adds text fields to panel, assigns them associated labels
    @Override
    public void designLayout() {
        setPanelLayout();

        addTextAndLabel(assignmentName, "Assignment Name");
        addTextAndLabel(assignmentDeadline, "Deadline");
        addTextAndLabel(assignmentCourse, "Course");
        addTextAndLabel(assignmentPlatform, "Platform of Submission");
    }

    // MODIFIES: this
    // EFFECTS: initializes text fields of panel
    @Override
    public void makeElements() {
        assignmentName = new JTextField(10);
        assignmentDeadline = new JTextField(10);
        assignmentCourse = new JTextField(10);
        assignmentPlatform = new JTextField(10);
    }

    // getters:
    public JTextField getAssignmentName() {
        return assignmentName;
    }

    public JTextField getAssignmentDeadline() {
        return assignmentDeadline;
    }

    public JTextField getAssignmentCourse() {
        return assignmentCourse;
    }

    public JTextField getAssignmentPlatform() {
        return assignmentPlatform;
    }
}
