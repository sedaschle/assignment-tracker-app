package persistence;

import model.Assignment;
import model.AssignmentList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Citation: much of this code comes from CPSC 210 JsonSerializationDemo
// Represents a reader that reads assignments from JSON data stored in file
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads assignments from file and loads them;
    // throws IOException if an error occurs reading data from file
    public AssignmentList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAssignmentList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses assignment list from JSON object and returns it
    private AssignmentList parseAssignmentList(JSONObject jsonObject) {
        AssignmentList al = new AssignmentList();
        addAssignments(al, jsonObject);
        return al;
    }

    // MODIFIES: al
    // EFFECTS: parses assignments from JSON object and adds them to assignment list
    private void addAssignments(AssignmentList al, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("assignments");
        for (Object json : jsonArray) {
            JSONObject nextAssignment = (JSONObject) json;
            addAssignment(al, nextAssignment);
        }
    }

    // MODIFIES: al
    // EFFECTS: parses assignment from JSON object and adds it to assignment list
    private void addAssignment(AssignmentList al, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String course = jsonObject.getString("course");
        String deadline = jsonObject.getString("deadline");
        String platform = jsonObject.getString("platform");
        Boolean completed = jsonObject.getBoolean("completed");
        Assignment assignment = new Assignment(name, course, deadline, platform);
        al.addNewAssignment(assignment);
        if (completed) {
            assignment.markCompleted();
        }
    }
}
