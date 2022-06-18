package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.ArrayList;

// Represents a list of assignments
public class AssignmentList implements Writeable {
    public ArrayList<Assignment> assignments;

    public AssignmentList() {
        assignments = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds assignment to assignments list if assignment is not already there
    public void addNewAssignment(Assignment a) {
        if (!assignments.contains(a)) {
            assignments.add(a);
        }
    }

    // MODIFIES: this
    // EFFECTS: remove given assignment from assignments list
    public void delete(Assignment a) {
        assignments.remove(a);
    }

    // EFFECTS: if the assignment is contained within the list (there is an assignment with the same NAME already
    //          in list), return true. Otherwise return false
    public boolean contains(Assignment a) {
        return assignments.contains(a);
    }

    // EFFECTS: returns the size of the list
    public int size() {
        return assignments.size();
    }

    // EFFECTS: returns list of only incomplete assignments
    public AssignmentList incompleteAssignments() {
        AssignmentList incomplete = new AssignmentList();
        for (Assignment a : assignments) {
            if (!a.getCompleted()) {
                incomplete.addNewAssignment(a);
            }
        }
        return incomplete;
    }


    // EFFECTS: returns item at index i of list, if index i is unoccupied return null
    public Assignment getAssAtI(int i) {
        if ((assignments.size() - 1) < i) {
            return null;
        } else {
            return assignments.get(i);
        }
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }


    // CITATION: CPSC 210 JsonSerializationDemo
    // EFFECTS: creates a JSON object out of assignments in list
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("assignments", assignmentsToJson());
        return json;
    }

    // CITATION: CPSC 210 JsonSerializationDemo
    // EFFECTS: returns assignments in list as a JSON array
    private JSONArray assignmentsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Assignment a : assignments) {
            jsonArray.put(a.toJson());
        }
        return jsonArray;
    }
}




