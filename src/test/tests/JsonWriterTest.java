package tests;

import model.Assignment;
import model.AssignmentList;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.ArrayList;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// CITATION: UBC CPSC210 JsonSerializationDemo
class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            AssignmentList al = new AssignmentList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            AssignmentList al = new AssignmentList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAssignmentList.json");
            writer.open();
            writer.write(al);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAssignmentList.json");
            al = reader.read();
            assertEquals(0, al.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            AssignmentList al = new AssignmentList();
            al.addNewAssignment(new Assignment("Homework 6", "PHIL 220",
                    "November 2", "Crowdmark"));
            Assignment a = new Assignment("Lab 6", "CPSC 210",
                    "Oct 30", "Github");
            a.markCompleted();
            al.addNewAssignment(a);
            al.addNewAssignment(new Assignment("Research Proposal", "COGS 200",
                    "November 5", "Canvas"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralAssignmentList.json");
            writer.open();
            writer.write(al);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralAssignmentList.json");
            al = reader.read();
            ArrayList<Assignment> assignments = al.getAssignments();
            assertEquals(3, assignments.size());
            checkAssignment("Homework 6", "PHIL 220", "November 2",
                    "Crowdmark", false, assignments.get(0));
            checkAssignment("Lab 6", "CPSC 210", "Oct 30",
                    "Github", true, assignments.get(1));
            checkAssignment("Research Proposal", "COGS 200", "November 5",
                    "Canvas", false, assignments.get(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
