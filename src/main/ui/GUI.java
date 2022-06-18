package ui;

import model.Assignment;
import model.AssignmentList;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.panels.*;
import ui.panels.Panel;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;


// CITATION: base implementation of this code uses Java Swing ListDemo
// Represents a GUI for the assignment tracker application
public class GUI extends JPanel implements ListSelectionListener {

    private static final String JSON_STORE = "./data/assignmenttracker.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    private JList list;
    private DefaultListModel assignmentData;
    private AssignmentList assignments;

    public OperationsPanel operationsPanel;
    public SaveLoadPanel saveLoadPanel;
    public DisplayDetailsPanel displayDetailsPanel;
    public NewAssignmentPanel newAssignmentPanel;

    private JScrollPane listScrollPane;

    // CITATION: UBC CPSC210 JsonSerializationDemo
    // EFFECTS: runs the assignment tracker application
    public GUI() {
        super(new BorderLayout());
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runGUI();
    }

    // EFFECTS: calls to create GUI elements
    private void runGUI() {
        initializeLists();
        placePanels();
    }

    // EFFECTS: calls to set up JList, Default List and Assignments list
    public void initializeLists() {
        createData();
        createJList();
        createAssignmentList();
    }


    // MODIFIES: this
    // EFFECTS: creates assignment data list
    public void createData() {
        assignmentData = new DefaultListModel();
    }

    // MODIFIES: this
    // EFFECTS: creates JList and puts it in a scroll pane
    public void createJList() {
        list = new JList(assignmentData);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        listScrollPane = new JScrollPane(list);
    }

    // MODIFIES: this
    // EFFECTS: creates a new assignment list to store actual assignments
    public void createAssignmentList() {
        assignments = new AssignmentList();
    }


    // MODIFIES: this
    // EFFECTS: adds component panels to main frame
    public void placePanels() {
        add(listScrollPane, BorderLayout.CENTER);
        add(makeDisplayDetailsPanel(), BorderLayout.LINE_END);
        add(makeOperationsPanel(), BorderLayout.PAGE_END);
        add(makeSaveLoadPanel(), BorderLayout.PAGE_START);
    }

    // MODIFIES: this
    // EFFECTS: creates a DisplayDetailsPanel
    public Panel makeDisplayDetailsPanel() {
        displayDetailsPanel = new DisplayDetailsPanel();
        return displayDetailsPanel;
    }

    // MODIFIES: this, saveLoadPanel
    // EFFECTS: creates a SaveLoadPanel and assigns buttons listeners
    public Panel makeSaveLoadPanel() {
        saveLoadPanel = new SaveLoadPanel();
        saveLoadPanel.getSaveAssignmentButton().addActionListener(new SaveListener());
        saveLoadPanel.getLoadAssignmentButton().addActionListener(new LoadListener());
        return saveLoadPanel;
    }

    // MODIFIES: this
    // EFFECTS: creates a NewAssignmentPanel
    public Panel makeNewAssignmentPanel() {
        newAssignmentPanel = new NewAssignmentPanel();
        return newAssignmentPanel;
    }

    // MODIFIES: this, operationsPanel
    // EFFECTS: creates an OperationsPanel and assigns buttons listeners
    public Panel makeOperationsPanel() {
        operationsPanel = new OperationsPanel();
        AddAssListener addAssListener = new AddAssListener(operationsPanel.getAddAssignmentButton());
        operationsPanel.getAddAssignmentButton().addActionListener(addAssListener);
        operationsPanel.getViewAssignmentButton().addActionListener(new ViewAssListener());
        operationsPanel.getDeleteAssignmentButton().addActionListener(new DeleteAssListener());
        operationsPanel.getCompleteAssignmentButton().addActionListener(new CompleteAssListener());

        return operationsPanel;
    }


    // MODIFIES: this, ta
    // EFFECTS: fetches and displays details of inputted assignment on ta, sets editable to false
    public void displayAssDetails(Assignment a, JTextArea ta) {
        ta.setText("");

        String name = a.getName();
        String deadline = a.getDeadline();
        String course = a.getCourse();
        String platform = a.getPlatform();
        boolean completed = a.getCompleted();

        ta.append("Assignment Name: " + name + "\n");
        ta.append("Deadline: " + deadline + "\n");
        ta.append("Course: " + course + "\n");
        ta.append("Platform: " + platform + "\n");
        ta.append("Completed? " + completed + "\n");
        ta.setEditable(false);
    }

    // CITATION: UBC CPSC210 JsonSerializationDemo
    // MODIFIES: this, JSON_STORE
    // EFFECTS: writes assignment list to file
    private void saveAssignmentList() {
        try {
            jsonWriter.open();
            jsonWriter.write(assignments);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null,
                    "Saved all assignments to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to write to file: " + JSON_STORE, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // CITATION: UBC CPSC210 JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: loads assignment list from file
    private void loadAssignmentList() {
        try {
            assignments = jsonReader.read();
            for (Assignment a : assignments.getAssignments()) {
                String name = a.getName();
                assignmentData.addElement(name);
            }
            JOptionPane.showMessageDialog(null,
                    "Loaded all assignments from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to read from file: " + JSON_STORE, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // MODIFIES: this
    // EFFECTS: disables DELETE, VIEW, and COMPLETE buttons and wipes assignment details pane when there are
    //          no assignments in list, enables those buttons once an assignment is added
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {

            if (list.getSelectedIndex() == -1) {
                operationsPanel.getDeleteAssignmentButton().setEnabled(false);
                operationsPanel.getViewAssignmentButton().setEnabled(false);
                operationsPanel.getCompleteAssignmentButton().setEnabled(false);
                displayDetailsPanel.getDisplayArea().setText("");

            } else {
                operationsPanel.getDeleteAssignmentButton().setEnabled(true);
                operationsPanel.getViewAssignmentButton().setEnabled(true);
                operationsPanel.getCompleteAssignmentButton().setEnabled(true);
            }
        }
    }

    // CITATION: https://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel
    // Panel with specified image displayed
    public static class ImagePanel extends JPanel {
        private BufferedImage image;

        // EFFECTS: prints stack trace if image file cannot be found
        public ImagePanel() {
            try {
                image = ImageIO.read(new File("data/borat.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        // MODIFIES: this
        // EFFECTS: draws image onto JPanel
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
        }
    }


    // Listener for DELETE button
    class DeleteAssListener implements ActionListener {
        // MODIFIES: this
        // EFFECTS: if selection is valid, deletes selected item from assignments and scroll pane
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            assignmentData.remove(index);
            Assignment a = assignments.getAssAtI(index);
            assignments.delete(a);

            int size = assignmentData.getSize();

            if (size == 0) {
                operationsPanel.getDeleteAssignmentButton().setEnabled(false);

            } else {
                if (index == assignmentData.getSize()) {
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    // Listener for VIEW button
    class ViewAssListener implements ActionListener {
        // EFFECTS: if selection is valid, calls to load details of selected assignment
        @Override
        public void actionPerformed(ActionEvent e) {
            if (list.getSelectedValue() == null) {
                return;
            } else {
                int index = list.getSelectedIndex();
                Assignment a = assignments.getAssAtI(index);

                displayAssDetails(a, displayDetailsPanel.getDisplayArea());

                int size = assignmentData.getSize();
                if (size == 0) {
                    operationsPanel.getViewAssignmentButton().setEnabled(false);
                }
            }
        }
    }


    // Listener for SAVE button
    class SaveListener implements ActionListener {
        // EFFECTS: calls to save assignment list when pressed
        @Override
        public void actionPerformed(ActionEvent e) {
            saveAssignmentList();
        }
    }

    // Listener for LOAD button
    class LoadListener implements ActionListener {
        // EFFECTS: calls to load assignment list when pressed
        @Override
        public void actionPerformed(ActionEvent e) {
            loadAssignmentList();
        }
    }

    // Listener for COMPLETE button
    class CompleteAssListener implements ActionListener {
        // MODIFIES: this, displayDetailsPanel
        // EFFECTS: marks selected assignment as COMPLETE if selection is valid, opens a pop-up congratulatory image
        @Override
        public void actionPerformed(ActionEvent e) {
            if (list.getSelectedValue() == null) {
                return;
            } else {
                int index = list.getSelectedIndex();
                Assignment a = assignments.getAssAtI(index);

                a.markCompleted();

                UIManager.put("OptionPane.minimumSize", new Dimension(270, 230));
                JOptionPane.showMessageDialog(null, new ImagePanel(),
                        "Good Work", JOptionPane.PLAIN_MESSAGE);

                displayAssDetails(a, displayDetailsPanel.getDisplayArea());

                int size = assignmentData.getSize();
                if (size == 0) {
                    operationsPanel.getDeleteAssignmentButton().setEnabled(false);
                }
            }
        }
    }


    // Listener for ADD button, as well as text fields opened when ADD is clicked
    class AddAssListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private final JButton button;

        // REQUIRES: button parameter !null
        // MODIFIES: this
        // EFFECTS: assigns listener to specified button
        public AddAssListener(JButton button) {
            this.button = button;
        }

        // MODIFIES: this
        // EFFECTS: creates a JOptionPane to get user input after add assignment button is pressed,
        //          creates new assignment with inputted details (only name field needs to be inputted)
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(new JFrame(), makeNewAssignmentPanel());

            String name = newAssignmentPanel.getAssignmentName().getText();
            String deadline = newAssignmentPanel.getAssignmentDeadline().getText();
            String course = newAssignmentPanel.getAssignmentCourse().getText();
            String platform = newAssignmentPanel.getAssignmentPlatform().getText();

            if (name.equals("") || alreadyInList(name)) {
                return;
            } else {
                assignmentData.addElement(newAssignmentPanel.getAssignmentName().getText());
                Assignment a = new Assignment(name, course, deadline, platform);
                assignments.addNewAssignment(a);
            }
        }

        // EFFECTS: checks to see if assignment is already in ScrollPane based on assignment name
        protected boolean alreadyInList(String name) {
            return assignmentData.contains(name);
        }

        // MODIFIES: this
        // EFFECTS: Enables OKAY button upon document event
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        // MODIFIES: this
        // EFFECTS: Disables OKAY button if text field is empty
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        // MODIFIES: this
        // EFFECTS: Enables OKAY button once text is inputted
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        // MODIFIES: this
        // EFFECTS: sets OKAY button to enabled if not already enabled
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        // EFFECTS: checks to make sure text field is not empty
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }
}
