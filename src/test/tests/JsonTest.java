package tests;

import model.Assignment;
import model.AssignmentList;

import static org.junit.jupiter.api.Assertions.*;

// CITATION: UBC CPSC210 JsonSerializationDemo
public class JsonTest {
    protected void checkAssignment(String name, String course, String deadline, String platform,
                                   Boolean completed, Assignment assignment) {
        assertEquals(name, assignment.getName());
        assertEquals(course, assignment.getCourse());
        assertEquals(deadline, assignment.getDeadline());
        assertEquals(platform, assignment.getPlatform());
        assertEquals(completed, assignment.getCompleted());
    }
}
