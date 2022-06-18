package ui.panels;

import javax.swing.*;

// Represents an abstract Panel with some set default behaviour
public abstract class Panel extends JPanel {
    // creates a new JPanel, calls to make and place elements, sets border
    public Panel() {
        new JPanel();
        makeElements();
        designLayout();
        border();
    }

    // MODIFIES: this
    // EFFECTS: sets the layout of the panel
    public abstract void setPanelLayout();

    // MODIFIES: this
    // EFFECTS: adds specified elements to panel in preferred layout
    public abstract void designLayout();

    // MODIFIES: this
    // EFFECTS: initializes panel elements (JButtons, etc)
    public abstract void makeElements();

    // MODIFIES: this
    // EFFECTS: sets the border of the panel
    public void border() {
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    // MODIFIES: this
    // EFFECTS: format helper, adds struts and JSeparator in specified orientation to the panel
    public void spaceAndSeparate(String s) {
        if (s.equals("H")) {
            add(Box.createHorizontalStrut(5));
            add(new JSeparator(SwingConstants.VERTICAL));
            add(Box.createHorizontalStrut(5));
        } else {
            add(Box.createVerticalStrut(5));
            add(new JSeparator(SwingConstants.HORIZONTAL));
            add(Box.createVerticalStrut(5));
        }
    }

    // MODIFIES: this
    // EFFECTS: format helper, adds JTextField, assigns it a label, and calls to SpaceAndSeperate
    public void addTextAndLabel(JTextField t, String l) {
        add(t);
        add(new JLabel(l));
        spaceAndSeparate("V");
    }

    // EFFECTS: creates a new button with label and action command s and sets enabled as specified
    public JButton newButton(String s, boolean b) {
        JButton button = new JButton(s);
        button.setActionCommand(s);
        button.setEnabled(b);

        return button;
    }
}

