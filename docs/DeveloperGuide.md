# Developer Guide

## Design & implementation

### Architecture

The ***Architecture Diagram*** given above explains the high-level design of the ATHENA. Given below is a quick overview of each component.

**`Main`** has one class called [`Athena`](https://github.com/AY2021S1-CS2113T-W12-2/tp/blob/master/src/main/java/athena/Athena.java). It is responsible for,
* At app launch: Initializes the components and connects them up with each other.
* At app shut down: Shuts down the components.

The rest of the App consists of these components.

* [**`Ui`**](#ui-component): The UI of ATHENA.
* [**`Parser`**](#parser-component): Parses user input and command executor.
* [**`TaskList`**](#tasklist-component): The list that stores the user's tasks.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

### UI component

**API** :
[`Ui.java`](https://github.com/AY2021S1-CS2113T-W12-2/tp/blob/master/src/main/java/athena/Ui.java)

### Parser component

**API** :
[`Parser.java`](https://github.com/AY2021S1-CS2113T-W12-2/tp/blob/master/src/main/java/athena/Parser.java)

### TaskList component

**API** :
[`TaskList.java`](https://github.com/AY2021S1-CS2113T-W12-2/tp/blob/master/src/main/java/athena/TaskList.java)

### Storage component

**API** : 
[`Storage.java`](https://github.com/AY2021S1-CS2113T-W12-2/tp/blob/master/src/main/java/athena/Storage.java)

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

---

## Other Guides: Documentation, logging, testing, configuration, dev-ops
* [Documentation guide](./Documentation.md)
* [Testing guide](./Testing.md)
* [DevOps guide](./DevOps.md)

---

## Appendix: Requirements
### Product scope
#### Target user profile
* is a university student
* has a need to manage a significant number of tasks
* can type fast and prefers typing to mouse interactions
* is comfortable using the command line interface

#### Value proposition
* ATHENA helps students to automate the process of organising their schedule. After the user inputs pre-allocated time slots for work and relaxation, ATHENA figures out the best theoretical timetable based on the user’s needs.
* It can be updated anytime during the week.
* ATHENA helps to reduce the amount of time and effort that users need to spend planning their time by finding free spaces to slot tasks in, with the goal of reducing dead space in the user’s timetable. 
* The planner will also make sure the user has enough time to eat, exercise and sleep. The user can set up ATHENA to follow a fixed weekly routine, and only needs to update a task list. ATHENA will then plan the timetable based on the importance and deadlines of the tasks in the list, making sure that the user is able to finish everything on time.

### User Stories

|Version | As a ...                    | I want to ...                                    | So that I can ...                                                      |
| -------| --------------------------- | ------------------------------------------------ | ---------------------------------------------------------------------- |
| `v1.0` | forgetful student           | upload my tasks for the week                     | remember to do them                                                    |
| `v1.0` | student                     | mark my tasks as done                            | know that I have done them                                             |
| `v1.0` | student                     | get reminded to do the tasks that are due soon   | I will be on time                                                      |
| `v1.0` | student                     | edit the tasks I added                           | update accordingly to small changes                                    |
| `v1.0` | student                     | delete the tasks I added                         | remove tasks that are not needed to do anymore                         |
| `v1.0` | student                     | set my task according to importance              | complete the more important tasks first                                |
| `v1.0` | student                     | leave some notes for a task                      | remember about it                                                      |
| `v2.0` | student                     | know a planner that tells me what time to rest   | I don’t exhaust myself                                                 |
| `v2.0` | student                     | see an overview of the week ahead                | make sure that I am staying on task                                    |

## Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` installed.
2.  A user with above average typing speed for regular English text should be able to use the features of ATHENA faster using commands than using the mouse.

## Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X

## Instructions for manual testing

Given below are instructions to test the app manually.

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   2. Double-click the jar file Expected: Shows the command line interface with welcome message.

2. Shutdown ATHENA

   1. Test case: `exit`<br>
      Expected: A farewell message by ATHENA will be shown.   
    
### Adding a task

* Add a task to the list.

   1. Test case: `add n/Assignment1 t/1100 D/16-09-2020 d/2 r/Today i/high a/Refer to lecture notes`<br>
      Expected: First task is added to the list. Details of the added task is shown.

   2. Test case: `add t/1100 D/16-09-2020`<br>
      Expected: No task is added. Error details is shown.
      
### Deleting a task

* Deleting a task while all tasks are shown.

   1. Prerequisites: List all tasks using the `list` command.

   2. Test case: `delete 1`<br>
      Expected: Task with index 1 is deleted from the list. Details of the deleted task is shown.

   3. Test case: `delete -1`<br>
      Expected: No task is deleted. Error details is shown.

   4. Other incorrect delete commands to try: `delete`, `delete x` (where x is larger than the list size)<br>
      Expected: Similar to previous.
 
### Marking a task as done

* Marking a task as done while all tasks are shown.

   1. Prerequisites: List all tasks using the `list` command.

   2. Test case: `done 1`<br>
      Expected: Task with index 1 is marked as done in the list. Details of the task is shown.

   3. Test case: `done -1`<br>
      Expected: No task is marked as done. Error details is shown.

   4. Other incorrect delete commands to try: `done`, `done x` (where x is larger than the list size)<br>
      Expected: Similar to previous.
      
### Viewing the full details of a task

* Viewing a task details while all tasks are shown.

   1. Prerequisites: List all tasks using the `list` command.

   2. Test case: `view 1`<br>
      Expected: Details of the task with index 1 in the list is shown.

   3. Test case: `view -1`<br>
      Expected: No task details is shown. Error details is shown.

   4. Other incorrect delete commands to try: `view`, `view x` (where x is larger than the list size)<br>
      Expected: Similar to previous.

### Editing a task

* Editing a task details while all tasks are shown.

   1. Prerequisites: List all tasks using the `list` command.

   2. Test case: `edit 1 n/new name`<br>
      Expected: Name of the task with index 1 in the list will be changed to `new name`.

   3. Test case: `edit -1`<br>
      Expected: No task will be edited. Error details is shown.

   4. Other incorrect delete commands to try: `edit`, `edit x` (where x is larger than the list size)<br>
      Expected: Similar to previous.    

### Listing all tasks

* Listing all the tasks with or without filters.

   1. Test case: `list`<br>
      Expected: All the tasks will be listed.

   2. Test case: `list i/HIGH f/TODAY`<br>
      Expected: All the tasks today with high importance will be shown.
      
### Help

* Guide on the use of ATHENA.

   1. Test case: `help`<br>
      Expected: A guide on how to use ATHENA will be shown. 
                                