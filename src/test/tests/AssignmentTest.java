package tests;

import static org.junit.jupiter.api.Assertions.*;

import model.Assignment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AssignmentTest {
    private Assignment testAssignment;

    @BeforeEach
    void runBefore() {
        testAssignment = new Assignment("Homework 4", "PHIL 220",
                "October 25", "Crowdmark");
    }

    @Test
    void testConstructor() {
        assertEquals("Homework 4", testAssignment.getName());
        assertEquals("PHIL 220", testAssignment.getCourse());
        assertEquals("October 25", testAssignment.getDeadline());
        assertEquals("Crowdmark", testAssignment.getPlatform());
        assertFalse(testAssignment.getCompleted());
    }

    @Test
    void testMarkCompleted() {
        assertFalse(testAssignment.getCompleted());
        testAssignment.markCompleted();
        assertTrue(testAssignment.getCompleted());
    }






}
