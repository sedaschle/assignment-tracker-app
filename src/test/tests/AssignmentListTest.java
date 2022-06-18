package tests;

import static org.junit.jupiter.api.Assertions.*;

import model.AssignmentList;
import model.Assignment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AssignmentListTest {
    private AssignmentList testAssignmentList;
    private Assignment a1;
    private Assignment a2;
    private Assignment a3;
    private Assignment a4;
    private Assignment a5;


    @BeforeEach
    void runBefore() {
        a1 = new Assignment("Proposal","COGS 200",
                "November 8", "Canvas");
        a2 = new Assignment("Podcast","PSYC 102",
                "October 16", "Canvas");
        a3 = new Assignment("Homework 5","PHIL 220",
                "October 21", "Crowdmark");
        a4 = new Assignment("Deliverable 1","CPSC 210",
                "October 14", "Github");
        a5 = new Assignment("Deliverable 1","cpsc 210",
                "October 27", "Github");
        a2.markCompleted();
        a3.markCompleted();
        testAssignmentList = new AssignmentList();
    }

    @Test
    void testAddAssignment() {
        assertEquals(0, testAssignmentList.size());
        testAssignmentList.addNewAssignment(a1);
        assertEquals(1, testAssignmentList.size());
        assertTrue(testAssignmentList.contains(a1));
    }

    @Test
    void testAddMultipleAssignments() {
        assertEquals(0, testAssignmentList.size());
        testAssignmentList.addNewAssignment(a1);
        testAssignmentList.addNewAssignment(a3);
        testAssignmentList.addNewAssignment(a2);
        testAssignmentList.addNewAssignment(a4);
        assertEquals(4, testAssignmentList.size());
        assertTrue(testAssignmentList.contains(a1));
        assertTrue(testAssignmentList.contains(a2));
        assertTrue(testAssignmentList.contains(a3));
        assertTrue(testAssignmentList.contains(a4));
    }

    @Test
    void testDoesNotAddDoubles() {
        testAssignmentList.addNewAssignment(a1);
        testAssignmentList.addNewAssignment(a3);
        testAssignmentList.addNewAssignment(a2);
        testAssignmentList.addNewAssignment(a1);
        assertEquals(3, testAssignmentList.size());
    }


    @Test
    void testDoesNotAddDoublesOnlySameName() {
        testAssignmentList.addNewAssignment(a1);
        testAssignmentList.addNewAssignment(a3);
        testAssignmentList.addNewAssignment(a4);
        assertEquals(3, testAssignmentList.size());
        testAssignmentList.addNewAssignment(a5);
        assertTrue(a4.equals(a5));
        assertFalse(a4.equals(a3));
        assertEquals(3, testAssignmentList.size());
    }

    @Test
    void testDeleteOneAssignment() {
        testAssignmentList.addNewAssignment(a1);
        assertEquals(1, testAssignmentList.size());
        testAssignmentList.delete(a1);
        assertEquals(0, testAssignmentList.size());
    }

    @Test
    void testDeleteMultipleAss() {
        testAssignmentList.addNewAssignment(a1);
        testAssignmentList.addNewAssignment(a2);
        testAssignmentList.addNewAssignment(a3);
        testAssignmentList.addNewAssignment(a4);
        assertEquals(4, testAssignmentList.size());
        testAssignmentList.delete(a4);
        testAssignmentList.delete(a2);
        testAssignmentList.delete(a1);
        assertEquals(1, testAssignmentList.size());
    }

    @Test
    void testDeleteAssNotThere() {
        testAssignmentList.addNewAssignment(a1);
        testAssignmentList.addNewAssignment(a2);
        testAssignmentList.addNewAssignment(a3);
        assertEquals(3, testAssignmentList.size());
        testAssignmentList.delete(a4);
        assertEquals(3, testAssignmentList.size());
    }

    @Test
    void testIncompleteNone() {
        testAssignmentList.addNewAssignment(a2);
        testAssignmentList.addNewAssignment(a3);
        assertEquals(0, testAssignmentList.incompleteAssignments().size());
    }

    @Test
    void testIncompleteMultiple() {
        testAssignmentList.addNewAssignment(a1);
        testAssignmentList.addNewAssignment(a2);
        testAssignmentList.addNewAssignment(a3);
        testAssignmentList.addNewAssignment(a4);
        assertEquals(2, testAssignmentList.incompleteAssignments().size());
        assertTrue(testAssignmentList.incompleteAssignments().contains(a1));
        assertTrue(testAssignmentList.incompleteAssignments().contains(a4));
    }

    @Test
    void testGetAssignments() {
        testAssignmentList.addNewAssignment(a1);
        testAssignmentList.addNewAssignment(a2);
        ArrayList<Assignment> assignments = testAssignmentList.getAssignments();
        assertTrue(assignments.contains(a1));
        assertTrue(assignments.contains(a2));
        assertEquals(2, assignments.size());
    }

    @Test
    void testGetAssAtI() {
        testAssignmentList.addNewAssignment(a1);
        testAssignmentList.addNewAssignment(a2);
        testAssignmentList.addNewAssignment(a3);
        assertEquals(a3, testAssignmentList.getAssAtI(2));
    }

    @Test
    void testGetAssAtISmall() {
        testAssignmentList.addNewAssignment(a1);
        testAssignmentList.addNewAssignment(a2);
        testAssignmentList.addNewAssignment(a3);
        assertEquals(a2, testAssignmentList.getAssAtI(1));
    }

    @Test
    void testGetAssNotThere() {
        testAssignmentList.addNewAssignment(a1);
        testAssignmentList.addNewAssignment(a2);
        testAssignmentList.addNewAssignment(a3);
        assertNull(testAssignmentList.getAssAtI(3));
    }
}
