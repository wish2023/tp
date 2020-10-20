# Developer Guide

## Design & implementation

### Architecture

The ***Architecture Diagram*** given above explains the high-level design of the ATHENA. Given below is a quick overview of each component.

**`Main`** has one class called [`Athena`](https://github.com/AY2021S1-CS2113T-W12-2/tp/blob/master/src/main/java/athena/Athena.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

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

## Product scope
### Target user profile
* is a university student
* has a need to manage a significant number of tasks
* can type fast and prefers typing to mouse interactions
* is comfortable using the command line interface

### Value proposition
*ATHENA helps students to automate the process of organising their schedule. After the user inputs pre-allocated time slots for work and relaxation, ATHENA figures out the best theoretical timetable based on the user’s needs.
*It can be updated anytime during the week.
*ATHENA helps to reduce the amount of time and effort that users need to spend planning their time by finding free spaces to slot tasks in, with the goal of reducing dead space in the user’s timetable. 
*The planner will also make sure the user has enough time to eat, exercise and sleep. The user can set up ATHENA to follow a fixed weekly routine, and only needs to update a task list. ATHENA will then plan the timetable based on the importance and deadlines of the tasks in the list, making sure that the user is able to finish everything on time.

## User Stories

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

   1. Double-click the jar file Expected: Shows the command line interface with welcome message.
