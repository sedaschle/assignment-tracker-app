// CONSOLE UI, NO LONGER USED



//package ui;
//
//import model.AssignmentList;
//import model.Assignment;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Scanner;
//
//// citation: CPSC 210 TellerApp example application
//
//// Assignment Tracker application
//public class AssignmentTracker {
//    private static final String JSON_STORE = "./data/assignmenttracker.json";
//    private AssignmentList all;
//    private Scanner input;
//    private JsonWriter jsonWriter;
//    private JsonReader jsonReader;
//
//    // EFFECTS: Runs the assignment tracker application
//    public AssignmentTracker() throws FileNotFoundException {
//        jsonWriter = new JsonWriter(JSON_STORE);
//        jsonReader = new JsonReader(JSON_STORE);
//        runTracker();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user input
//    private void runTracker() {
//        boolean keepGoing = true;
//        String command = null;
//
//        init();
//
//        while (keepGoing) {
//            displayMenu();
//            command = input.next();
//            command = command.toLowerCase();
//
//            if (command.equals("q")) {
//                keepGoing = false;
//            } else {
//                processCommand(command);
//            }
//        }
//        System.out.println("\nGoodbye!");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes main menu user command
//    private void processCommand(String command) {
//        if (command.equals("n")) {
//            enterAssignment();
//        } else if (command.equals("v")) {
//            viewAssignments();
//        } else if (command.equals("s")) {
//            saveAssignmentList();
//        } else if (command.equals("l")) {
//            loadAssignmentList();
//        } else {
//            System.out.println("Selection not valid...");
//        }
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: initializes accounts
//    private void init() {
//        all = new AssignmentList();
//        input = new Scanner(System.in);
//
//    }
//
//    // EFFECTS: displays menu of options to user
//    private void displayMenu() {
//        System.out.println("\nSelect from:");
//        System.out.println("\tn -> add new assignment");
//        System.out.println("\tv -> view assignments");
//        System.out.println("\ts -> save assignments to file");
//        System.out.println("\tl -> load assignments from file");
//        System.out.println("\tq -> quit");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: adds an assignment to tracker
//    private void enterAssignment() {
//        input.nextLine();
//        System.out.print("What is the name of this assignment? ");
//        String name = input.nextLine();
//        System.out.print("What course is this assignment for? ");
//        String course = input.nextLine();
//        System.out.print("What is the deadline of this assignment? ");
//        String deadline = input.nextLine();
//        System.out.print("What is the platform of submission? ");
//        String platform = input.nextLine();
//
//        all.addNewAssignment(new Assignment(name, course, deadline, platform));
//
//        System.out.println("\n You have created a new assignment called " + name + " due " + deadline
//                + " to be submitted on " + platform + " for the course " + course + ".");
//    }
//
//    // EFFECTS: returns list of assignments
//    private void viewAssignments() {
//        AssignmentList list = selectAssignments();
//        int index = 1;
//
//        if (list != null) {
//            for (Assignment a : list.getAssignments()) {
//                String name = a.getName();
//                String course = a.getCourse();
//                String deadline = a.getDeadline();
//                String platform = a.getPlatform();
//
//                System.out.println(index++ + ". " + name + " due " + deadline + " on " + platform
//                        + " for course " + course);
//            }
//            askModifications();
//        }
//    }
//
//    // EFFECTS: prompts user to see either all assignments, or only incomplete assignments
//    private AssignmentList selectAssignments() {
//        String selection = "";
//
//        while (!(selection.equals("i") || selection.equals("a"))) {
//            System.out.println("i for incomplete assignments");
//            System.out.println("a for all assignments");
//            selection = input.next();
//            selection = selection.toLowerCase();
//        }
//
//        if (selection.equals("i")) {
//            System.out.println("Incomplete Assignments");
//            if (all.incompleteAssignments().size() == 0) {
//                System.out.println("There are currently no incomplete assignments. Nice work!");
//                return null;
//            } else {
//                return all.incompleteAssignments();
//            }
//        } else if (all.size() == 0) {
//            System.out.println("You do not have any assignments. Add some by pressing n.");
//            return null;
//        } else {
//            System.out.println("All Assignments");
//            return all;
//        }
//    }
//
//    // EFFECTS: asks user if they would like to modify list
//    private void askModifications() {
//        System.out.println("Would you like to change any items on the list? y or n");
//
//        String selection = "";
//
//        while (!(selection.equals("y") || selection.equals("n"))) {
//            selection = input.next();
//            selection = selection.toLowerCase();
//        }
//
//        if (selection.equals("y")) {
//            executeModifications();
//        }
//    }
//
//    // EFFECTS: prompts user to choose to delete or complete an assignment
//    private void executeModifications() {
//        String selection = "";
//        while (!(selection.equals("c") || selection.equals("d"))) {
//            System.out.println("c -> mark assignment as complete");
//            System.out.println("d -> delete assignment");
//            selection = input.next();
//            selection = selection.toLowerCase();
//        }
//
//        if (selection.equals("c")) {
//            completeAssignment();
//        } else {
//            deleteAssignment();
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: allows user to mark an assignment as complete
//    private void completeAssignment() {
//        System.out.println("Enter the number of the assignment you have finished.");
//        int num = input.nextInt() - 1;
//
//        Assignment a = all.getAssAtI(num);
//        if (a != null) {
//            String name = a.getName();
//            System.out.println(name + " has been completed.");
//            a.markCompleted();
//        } else {
//            System.out.println("That assignment does not exist.");
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: allows user to delete an assignment from their list
//    private void deleteAssignment() {
//        System.out.println("Enter the number of the assignment you wish to delete.");
//        int num = input.nextInt() - 1;
//
//        Assignment a = all.getAssAtI(num);
//        if (a != null) {
//            String name = a.getName();
//            System.out.println(name + " was deleted.");
//            all.delete(a);
//        } else {
//            System.out.println("That assignment does not exist.");
//        }
//    }
//
//    // CITATION: UBC CPSC210 JsonSerializationDemo
//    // MODIFIES: this
//    // EFFECTS: saves the assignment list to file
//    private void saveAssignmentList() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(all);
//            jsonWriter.close();
//            System.out.println("Saved all assignments to " + JSON_STORE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE);
//        }
//    }
//
//    // CITATION: UBC CPSC210 JsonSerializationDemo
//    // MODIFIES: this
//    // EFFECTS: loads assignment list from file
//    private void loadAssignmentList() {
//        try {
//            all = jsonReader.read();
//            System.out.println("Loaded all assignments from from " + JSON_STORE);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
//    }
//
//}
