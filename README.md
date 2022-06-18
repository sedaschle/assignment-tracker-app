# assignment-tracker-app
an app to keep assignments in check.



This personal project is an assignment tracker to help deal with some of the newfound struggles of online learning. The user will be able to input every assignment, along with its due date, the platform of submission, and what class it is for. They will then be able to view this information in a number of ways, and check off assignments as completed.
This application will be primarily used for students, but could also be slightly modified for other uses. I am making this application because I have found that it is very hard to keep track of all the small and large assignments assigned in online learning, and especially which platforms are used for which class.

## Objectives
- I want to be able to add an assignment and its information to the list
- I want to be able to mark an assignment as completed
- I want to be able to delete an assignment from the list
- I want to be able to see only incomplete assignments
- I want to be able to save all assignments in the assignment tracker
- I want to be able to load, view, and modify previously inputted assignments


I chose to implement a type hierarchy in the app. I created an abstract Panel class that encompasses default behaviour for the panels, and extended it with specified behaviours in each subclass. Superclass: Panel, Subclasses: DisplayDetailsPanel, NewAssignmentPanel, OperationsPanel, and SaveLoadPanel.

## Further Extensions
- If I had more time to work on this project, I would refactor it so that the GUI stores a set of type Panel, rather than having a field for each subclass of panel. This would reduce the associations that the GUI class has, however, it would still have a dependency relationship with each panel subclass. 
- I could also reduce coupling between the Panel subclasses as well. There is some common functionality between some of them that could fit into the abstract class. As part of this, I would also make a new Panel class for the JScrollPane panel where the assignment list is displayed. 
- I would also spend some time refactoring the ui package to ensure that all of the classes adhere to the cohesion principle. Right now, my GUI class is responsible for more than one task. For example, I could extract a class to house the save and load methods. 
