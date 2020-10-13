# User Guide

## Introduction

ATHENA (Automated Timetable Helper Encourager n' Assistant) is a desktop daily life planner optimized for use via a Command Line Interface (CLI).

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `ATHENA` from [here](https://github.com/AY2021S1-CS2113T-W12-2/tp/releases).

## Features 
Notes about the command format:
* Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in `add n/NAME`, NAME is a parameter which can be used as: `add n/Assignment1`.
* Parameters can be in any order.
e.g. if the command specifies `n/NAME t/TIME`, `t/TIME n/NAME` is also acceptable.
* Items in square brackets are optional. e.g `n/NAME  [d/DURATION]` can be used as `n/Task1 d/1` or as `n/Task1`.
* For some commands, if no parameters are specified, the command will execute using the default values for each parameter.
* For dates, the program follows the DD-MM-YYYY format.
* For time, the program follows the HHMM format.

### Adding a task: `add`
Adds a task to the planner.

Format: `add n/NAME t/TIME [d/DURATION] [D/DEADLINE] [r/RECURRENCE] [i/IMPORTANCE] [a/ADDITIONAL-NOTES]`

Parameters:
* `NAME` is the name of the task.
* `TIME` is the time to start doing this task (HHMM). For example, 1100.
* `DURATION` is the expected time taken to complete task (in hours)
   Default: 1 hour.
* `DEADLINE` is the date to do task by (DD-MM-YYYY). For example, 16-09-2020.
   Default: No deadline.
* `RECURRENCE` is TODAY or a specific date (DD-MM-YYYY).
   Default: TODAY.
* `IMPORTANCE` is one of HIGH, MEDIUM, LOW.
   Default: MEDIUM.
* `ADDITIONAL-NOTES` is the additional notes of the task.
   Default: No notes.

Example of usage: 

`add n/Assignment1 t/1100 D/16-09-2020 d/2 r/Today i/high a/Refer to lecture notes`

### Listing tasks: `list`
Shows a list of all tasks in the planner. Each task will be printed with a number to be used as an identifier for other commands.

Format: `list [f/FORECAST] [i/IMPORTANCE]`

Parameters:
* `FORECAST`: TODAY to show the tasks today, WEEK to show the tasks this week, ALL to show all tasks, or choose a specific date. 
  Default: TODAY.
* `IMPORTANCE`: One of HIGH, MEDIUM, LOW, ALL. 
  Default: ALL.
  
Example of usage: 

`list f/WEEK i/medium`

### Mark task as done: `done`
Marks the specified task from the planner as done.

Format: `done INDEX`

* `INDEX` refers to the index number shown in the displayed task list. It must be a positive integer.

Example of usage: 

`done 103`

### Edit task: `edit`
Allows users to edit the specified task from the planner.

Format: `edit INDEX [n/NAME] [t/TIME] [d/DURATION] [D/DEADLINE] [r/RECURRENCE] [i/IMPORTANCE] [a/ADDITIONAL-NOTES]`

Parameters:
* `INDEX` refers to the index number shown in the displayed task list. It must be a positive integer.
* `NAME` is the name of the task.
* `TIME` is the time to start doing this task (HHMM). For example, 1100.
* `DURATION` is the expected time taken to complete task (in hours).
* `DEADLINE` is the date to do task by (DD-MM-YYYY). For example, 16-09-2020.
* `RECURRENCE` is one of TODAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY.
* `IMPORTANCE` is one of HIGH, MEDIUM, LOW.
* `ADDITIONAL-NOTES` is the additional notes of the task.
   
Example of usage: 

`edit 103 n/Assignment1 t/1100 D/16-09-2020 d/2 r/today i/high a/Refer to lecture notes`

### Delete task: `delete`
Deletes the specified task from the planner.

Format: `delete INDEX`

* `INDEX` refers to the index number shown in the displayed task list. It must be a positive integer.
 
Example of usage: 

`delete 103`

### Exit program: `exit` 
Exits the program.

Format: `exit`

### Saving the data
ATHENA data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.  
   
## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: You can copy the data.csv file created next to ATHENA.jar to the other computer and place it next to ATHENA.jar there.

## Command Summary

* Help `help`
* Add task `add n/NAME t/TIME [d/DURATION] [D/DEADLINE] [r/RECURRENCE] [i/IMPORTANCE] [a/ADDITIONAL-NOTES]`
* List tasks `list [f/FORECAST] [i/IMPORTANCE]`
* Mark task as done `done INDEX`
* Edit task `edit INDEX [n/NAME] [t/TIME] [d/DURATION] [D/DEADLINE] [r/RECURRENCE] [i/IMPORTANCE] [a/ADDITIONAL-NOTES]`
* Delete task `delete INDEX`
* Exit program `exit`