package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.Assignment;
import model.AssignmentList;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;

// CITATION: UBC CPSC210 JsonSerializationDemo
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            AssignmentList al = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyAssignmentList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAssignmentList.json");
        try {
            AssignmentList al = reader.read();
            assertEquals(0, al.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralAssignmentList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralAssignmentList.json");
        try {
            AssignmentList al = reader.read();
            ArrayList<Assignment> assignments = al.getAssignments();
            assertEquals(3, al.size());
            checkAssignment("Homework 6", "PHIL 220", "November 2",
                    "Crowdmark", false, assignments.get(0));
            checkAssignment("Lab 6", "CPSC 210", "Oct 30",
                    "Github", true, assignments.get(1));
            checkAssignment("Research Proposal", "COGS 200", "November 5",
                    "Canvas", false, assignments.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }





}
