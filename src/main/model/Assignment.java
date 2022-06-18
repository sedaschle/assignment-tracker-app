package model;

import org.json.JSONObject;
import persistence.Writeable;

// Represents an assignment having a name, course, deadline, and platform
public class Assignment implements Writeable {
    private String name;            // assignment name
    private String course;          // course name
    private String deadline;        // assignment deadline
    private String platform;        // platform of submission
    private boolean completed;      // has assignment been completed


    // EFFECTS: Sets all of assignments fields according to given variables, marks assignment as incomplete
    public Assignment(String assignmentName, String assignmentCourse, String assignmentDeadline,
                      String assignmentPlatform) {
        name = assignmentName;
        course = assignmentCourse;
        deadline = assignmentDeadline;
        platform = assignmentPlatform;
        completed = false;

    }

    // REQUIRES: assignment has not yet been marked as completed
    // MODIFIES: this
    // EFFECTS: changes completion status to TRUE to mark assignment as completed
    public void markCompleted() {
        completed = true;
    }

    // getters
    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getPlatform() {
        return platform;
    }

    public boolean getCompleted() {
        return completed;
    }

    // EFFECTS: override equals to compare assignments based on name
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        Assignment that = (Assignment) o;
        return name.equals(that.name);
    }

    // EFFECTS: override to create required JSON object
    // CITATION: CPSC 210 JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("course", course);
        json.put("deadline", deadline);
        json.put("platform", platform);
        json.put("completed", completed);
        return json;
    }
}


