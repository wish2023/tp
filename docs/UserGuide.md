# User Guide

- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
    - [View Help](#view-help-help)
    - [Add a task](#add-a-task-add-or-a)  
    - [List tasks](#list-tasks-list-or-l)  
    - [Mark task as done](#mark-task-as-done-done-or-dn)  
    - [Edit task](#edit-task-edit-or-e)  
    - [Delete task](#delete-task-delete-or-dl)
    - [View task](#view-task-view-or-v)
    - [Exit](#exit-program-exit-or-ex)
    - [Saving the data](#saving-the-data)
- [FAQ](#faq)    
- [Command Summary](#command-summary)  

## Introduction

Welcome and thank you for choosing ATHENA! ATHENA is your Automated Timetable Helper Encourager n' Assistant and is a desktop daily life planner optimized for use via a Command Line Interface (CLI).

ATHENA uses algorithmic optimisation to give you the best timetable that allows you to make the most of your time. With ATHENA, you can save time on planning your timetable and be more efficient with your time!

You can take a look at the table of contents above if you are looking for a specific command, or you can begin by looking at the **[Quick Start](#Quick-Start)** section below!

We hope you enjoy using ATHENA and start being more productive today!

## Quick Start

1. Ensure that you have **Java 11** or above installed.
2. Download the latest version of **ATHENA** from [here](https://github.com/AY2021S1-CS2113T-W12-2/tp/releases).
3. Copy the downloaded Athena.jar into your **Desktop**.
4. Open the terminal/command prompt and enter `cd Desktop`.
5. Then, enter `java -jar Athena.jar`.
6. A welcome message as seen below will be shown:


![welcome screenshot](screenshots/athena%20welcome.jpg)
 

## Features 
Notes about the command format:
* Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in `add n/NAME`, NAME is a parameter which can be used as: `add n/Assignment1`.
* Parameters can be in any order.
e.g. if the command specifies `n/NAME t/TIME`, `t/TIME n/NAME` is also acceptable.
* Any unknown parameter type entered by the user that does not fit the parameters accepted in a respective feature will be ignored.
e.g `add n/NAME p/PARAMETER`, since `p/` is an unknown parameter type for `add` command, `p/PARAMETER` is ignored.
* If user types in 2 or more parameter description for each parameter type, only the first description will be taken.
e.g. if user types `n/NAME1 n/NAME2`, only `NAME1` will be recorded as the task's name.
* Items in square brackets are optional. e.g `n/NAME  [d/DURATION]` can be used as `n/Task1 d/1` or as `n/Task1`.
* For some commands, if no parameters are specified, the command will execute using the default values for each parameter.
* For dates, the program follows the **DD-MM-YYYY** format.
* For time, the program follows the **HHMM** format.

### View Help: `help`
Prints out a message on how to use ATHENA.

#### Format: `help`

#### Expected output

![help command screenshot](screenshots/athena%20help.jpg)


### Add a task: `add` or `a`
This command adds a task to the planner.

The guide for this command is split into three sections:

* [Basic Usage](#basic-usage) (For users new to command line applications)
* [Intermediate Usage](#intermediate-usage) (For users comfortable with command line applications)
* [Advanced Usage](#advanced-usage) (Extension to *Intermediate Usage*)

#### Basic Usage

##### Command Format

`add n/NAME t/TIME d/DURATION D/DEADLINE r/RECURRENCE i/IMPORTANCE a/ADDITIONAL-NOTES`

##### Parameters
The `add` command accepts 7 parameters.

* `NAME` is the name of the task.

* `TIME` is the time to start doing this task (**HHMM**). For example, 1100.

* `DURATION` is the expected time taken to complete task (in hours)

* `DEADLINE` is the date to do task by (**DD-MM-YYYY**). For example, 16-09-2020.

* `RECURRENCE` is one of **TODAY**, **MONDAY**, **TUESDAY**, **WEDNESDAY**, **THURSDAY**, **FRIDAY**, **SATURDAY**, **SUNDAY** or a specific date (**DD-MM-YYYY**).

   This is where the user can input a specific date to do the task on.
   If it is not **TODAY** or a specific date, it will be treated as occuring every week. For example, **MONDAY** means happening every Monday.
   
* `IMPORTANCE` is one of **HIGH**, **MEDIUM**, **LOW**.

* `ADDITIONAL-NOTES` is the additional notes of the task.

##### Example Usage

The following shows the output from ATHENA after entering `add n/Assignment1 t/1100 D/16-09-2020 d/2 r/Today i/high a/Refer to lecture notes`.
You should expect to see a message to confirm that the task is added.

![add command screenshot](screenshots/athena%20add.jpg)

#### Intermediate Usage

##### Command Format

`add n/NAME [t/TIME] [d/DURATION] [D/DEADLINE] [r/RECURRENCE] [i/IMPORTANCE] [a/ADDITIONAL-NOTES]`

##### Parameters
The `add` command accepts 7 parameters.

* `NAME` is the name of the task. It is a compulsory parameter the user has to input.

This parameter `TIME` is optional only if the task is non-recurring. Else, the user must input a start time for the task.
* `TIME` is the time to start doing this task (**HHMM**). For example, 1100.

   Default: Allocated by Athena (only available for non-recurring tasks)

The following parameters are optional. If they are left empty, ATHENA will use the default value assigned.
* `DURATION` is the expected time taken to complete task (in hours).

   Default: 1 hour.
* `DEADLINE` is the date to do task by (**DD-MM-YYYY**). For example, 16-09-2020.

   Default: No deadline.
* `RECURRENCE` is one of **TODAY**, **MONDAY**, **TUESDAY**, **WEDNESDAY**, **THURSDAY**, **FRIDAY**, **SATURDAY**, **SUNDAY** or a specific date (**DD-MM-YYYY**).

   This is where the user can input a specific date to do the task on. 
   If it is not **TODAY** or a specific date (**DD-MM-YYYY**), it will be treated as occuring every week. For example, **MONDAY** means happening every Monday.
   
   Default: **TODAY**.
* `IMPORTANCE` is one of **HIGH**, **MEDIUM**, **LOW**.

   Default: **MEDIUM**.
* `ADDITIONAL-NOTES` is the additional notes of the task.

   Default: No notes.

##### Example Usage

The following shows the output from ATHENA after entering `add n/Assignment2 t/1400 i/high`.
You should expect to see a message to confirm that the task is added with some of the parameter's default value assigned.

*TODO: Insert screenshot*

#### Advanced Usage

This section is an extension to the [Intermediate Usage](#intermediate-usage) section.

The `add` command supports shortcuts for advanced users.

##### Supported Shortcuts

The following commands on the left will be expanded to the corresponding commands on the right.

| Shortcut | Expanded command |
|----------|------------------|
| `a`     | `add`  |

##### Example usage

The user can just type `a` instead of `add`.
The following shows the output from ATHENA after entering `a n/Assignment3 t/1900 D/16-09-2020 d/2 a/Refer to notes`.
You should expect to see a message to confirm that the task is added with some of the parameter's default value assigned.

*TODO: Update screenshot*


### List tasks: `list` or `l`
Shows your tasks organized in a timetable. Each task will be printed with a number to be used as an identifier for other commands (e.g. `edit`, `done`, `delete`).

#### Format
`list [f/FORECAST] [i/IMPORTANCE]`

#### Parameters
* `FORECAST`: **DAY** to show the tasks today, **WEEK** to show the tasks this week, **ALL** to show all tasks. 

  Default: **WEEK**.
* `IMPORTANCE`: One of **HIGH**, **MEDIUM**, **LOW**, **ALL**. 

  Default: **ALL**.

#### Shortcut format
* `l3` = `list i/HIGH`
* `l2` = `list i/MEDIUM`
* `l1` = `list i/LOW`
* `lw` = `list f/WEEK`
* `ld` = `list f/DAY`
* `la` = `list f/ALL`
  
#### Example usage 

* `l`
* `ld`
* `list f/WEEK i/medium`
* `l2 f/WEEK`

#### Expected output

When you input `l`:

![l command screenshot](screenshots/athena%20l.jpg)

When you input `ld`:

![ld command screenshot](screenshots/athena%20ld.jpg)


### Mark task as done: `done` or `dn`
Mark the specified task from the planner as done.

#### Format
`done INDEX`

* `INDEX` refers to the index number shown in the displayed task list. It must be a positive integer.

#### Example usage

* `done 0`
* `dn 0` 

#### Expected output

![done command screenshot](screenshots/athena%20done.jpg)


### Edit task: `edit` or `e`
This command edits a specific task in the planner.

The guide for this command is split into three sections:

* [Basic Usage](#basic-usage) (For users new to command line applications)
* [Intermediate Usage](#intermediate-usage) (For users comfortable with command line applications)
* [Advanced Usage](#advanced-usage) (Extension to *Intermediate Usage*)

#### Basic Usage

##### Command Format

`edit TASK-ID n/NAME t/TIME d/DURATION D/DEADLINE r/RECURRENCE i/IMPORTANCE a/ADDITIONAL-NOTES`

##### Parameters
The `edit` command accepts 8 parameters.

* `TASK-ID` refers to the number shown beside the task that the user wants to edit in the displayed task list. It must be a non-negative integer.

At least one parameter shown below have to be edited:
* `NAME` is the name of the task.

* `TIME` is the time to start doing this task (**HHMM**). For example, 1100.

* `DURATION` is the expected time taken to complete task (in hours).

* `DEADLINE` is the date to do task by (**DD-MM-YYYY**). For example, 16-09-2020.

* `RECURRENCE` is one of **TODAY**, **MONDAY**, **TUESDAY**, **WEDNESDAY**, **THURSDAY**, **FRIDAY**, **SATURDAY**, **SUNDAY** or a specific date (**DD-MM-YYYY**).

* `IMPORTANCE` is one of **HIGH**, **MEDIUM**, **LOW**.

* `ADDITIONAL-NOTES` is the additional notes of the task.

##### Example Usage

Firstly, use [list command](#list-tasks-list-or-l) to get the `TASK-ID` of the task to be edited.

The following shows the output from ATHENA after entering `edit 1 n/Assignment2 t/1800 D/17-09-2020 d/1 r/16-09-2020 i/low a/Refer to notes`.
You should expect to see a message to confirm that the task with index 1 is edited.

![edit command screenshot](screenshots/athena%20edit.jpg)

#### Intermediate Usage

##### Command Format

`edit TASK-ID [n/NAME] [t/TIME] [d/DURATION] [D/DEADLINE] [r/RECURRENCE] [i/IMPORTANCE] [a/ADDITIONAL-NOTES]`

##### Parameters
The `edit` command accepts 8 parameters.

* `TASK-ID` refers to the number shown beside the task that the user wants to edit in the displayed task list. It must be a non-negative integer.

At least one parameter shown below have to be edited:
* `NAME` is the name of the task.

* `TIME` is the time to start doing this task (**HHMM**). For example, 1100.

* `DURATION` is the expected time taken to complete task (in hours).

* `DEADLINE` is the date to do task by (**DD-MM-YYYY**). For example, 16-09-2020.

* `RECURRENCE` is one of **TODAY**, **MONDAY**, **TUESDAY**, **WEDNESDAY**, **THURSDAY**, **FRIDAY**, **SATURDAY**, **SUNDAY** or a specific date (**DD-MM-YYYY**).

* `IMPORTANCE` is one of **HIGH**, **MEDIUM**, **LOW**.

* `ADDITIONAL-NOTES` is the additional notes of the task.

##### Example Usage

Firstly, use [list command](#list-tasks-list-or-l) to get the `TASK-ID` of the task to be edited.

The following shows the output from ATHENA after entering `edit 1 t/1800`.
You should expect to see a message to confirm that the time of task with `TASK-ID` '1' is edited.

*TODO: Insert screenshot*

#### Advanced Usage

This section is an extension to the [Intermediate Usage](#intermediate-usage) section.

The `edit` command supports shortcuts for advanced users.

##### Supported Shortcuts

The following commands on the left will be expanded to the corresponding commands on the right.

| Shortcut | Expanded command |
|----------|------------------|
| `e`     | `edit`  |

##### Example usage

Firstly, use [list command](#list-tasks-list-or-l) to get the `TASK-ID` of the task to be edited.

The user can just type `e` instead of `edit`.
The following shows the output from ATHENA after entering `e 1 n/Assignment2 t/1800 D/17-09-2020 d/1 r/today i/high a/Refer to lecture notes`.
You should expect to see a message to confirm that the time of task with `TASK-ID` '1' is edited.

*TODO: Update screenshot*


### Delete task: `delete` or `dl`
Deletes the specified task from the planner.

#### Format: `delete INDEX`

* `INDEX` refers to the index number shown in the displayed task list. It must be a positive integer.
 
#### Example usage

* `delete 1`
* `dl 1`

#### Expected output

![delete command screenshot](screenshots/athena%20delete.jpg)


### View task: `view` or `v`
Views the specified task details from the planner.

#### Format
`view INDEX`

* `INDEX` refers to the index number shown in the displayed task list. It must be a positive integer.
 
#### Example of usage 

* `view 0`
* `v 0`

#### Expected output

![view command screenshot](screenshots/athena%20view.jpg)


### Exit program: `exit` or `ex`
Exits the program.

#### Format: `exit` or `ex`

#### Expected output:

![exit command screenshot](screenshots/athena%20exit.jpg)


### Saving the data
Your tasks are automatically saved in *data.csv* which is located next to the program jar file. There is no need to save manually.  
   
## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: You can copy the *data.csv* file created next to ATHENA.jar to the other computer and place it next to ATHENA.jar there.

## Command Summary

| Action            | Format                                                                                                      |
| :---------------- | :---------------------------------------------------------------------------------------------------------- |
| Help              | `help`                                                                                                      |
| Add task          | `add n/NAME [t/TIME] [d/DURATION] [D/DEADLINE] [r/RECURRENCE] [i/IMPORTANCE] [a/ADDITIONAL-NOTES]`            |
| List tasks        | `list [f/FORECAST] [i/IMPORTANCE]`                                                                          |
| Mark task as done | `done INDEX`                                                                                                |
| Edit task         | `edit TASK-ID [n/NAME] [t/TIME] [d/DURATION] [D/DEADLINE] [r/RECURRENCE] [i/IMPORTANCE] [a/ADDITIONAL-NOTES]` |
| Delete task       | `delete INDEX`                                                                                              |
| View task         | `view INDEX`                                                                                                |
| Exit program      | `exit`                                                                                                      |
