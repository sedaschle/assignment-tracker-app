package persistence;

import org.json.JSONObject;

// CITATION: CPSC 210 JsonSerializationDemo
public interface Writeable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
