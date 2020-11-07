### Delete task: `delete` or `dl`
This command deletes the specified task from the timetable. ATHENA will ensure to leave the index of other tasks untouched.

The guide for this command is split into two sections:
* [Basic Usage (delete)](#basic-usage-delete) (For users new to command line applications)
* [Intermediate Usage (delete)](#intermediate-usage-delete) (For users comfortable with command line applications)

#### Basic Usage (delete)

In this section, users new to command line applications should find it easy to use as long as they follow the descriptions in the sections below. 
Users who are comfortable with the basic usage may move on to the [Intermediate](#intermediate-usage-delete) stage to experience how ATHENA was intended to be used.

##### Command Format

`delete TASK-ID`

##### Parameters
The `delete` command requires 1 parameter only.

* `TASK-ID` refers to the number shown beside the task that the user wants to delete in the displayed task list. It must be a non-negative integer.

##### Example Usage

Firstly, use the [list command](#list-tasks-list-or-l) to get the `TASK-ID` of the task to be deleted.
The following shows the output from ATHENA after entering `delete 0`.
You should expect to see a message to confirm that the task with `TASK-ID` of `0` is deleted. 
ATHENA also prints the command required to restore the deleted task.

*SCREENSHOT*

#### Intermediate Usage (delete)
The `delete` command supports shortcuts for intermediate users.

##### Supported Shortcuts

The command on the left in the table below is the shortcut of the corresponding command on the right.

| Shortcut | Expanded command |
|----------|------------------|
| `dl`     | `delete`  |

##### Example usage

Firstly, use the [list command](#list-tasks-list-or-l) to get the `TASK-ID` of the task to be deleted.

The user can just type `dl` instead of `delete`.
The screenshot below shows the output from ATHENA after entering `dl 0`.

*insert screenshot*


### View task: `view` or `v`
This command views the specified task details from the timetable.

The guide for this command is split into two sections:
* [Basic Usage (view)](#basic-usage-view) (For users new to command line applications)
* [Intermediate Usage (view)](#intermediate-usage-view) (For users comfortable with command line applications)

#### Basic Usage (view)

In this section, users new to command line applications should find it easy to use as long as they follow the descriptions in the sections below. 
Users who are comfortable with the basic usage may move on to the [Intermediate](#intermediate-usage-view) stage to experience how ATHENA was intended to be used.

##### Command Format

`view TASK-ID`

##### Parameters
The `view` command requires 1 parameter only.

* `TASK-ID` refers to the number shown beside the task that the user wants to view in the displayed task list. It must be a non-negative integer.

##### Example Usage

Firstly, use the [list command](#list-tasks-list-or-l) to get the `TASK-ID` of the task to be viewed.
The following shows the output from ATHENA after entering `view 0`.
You should expect to see the details of your task with `TASK-ID` of `0`.

*SCREENSHOT*

#### Intermediate Usage (view)
The `view` command supports shortcuts for intermediate users.

##### Supported Shortcuts

The command on the left in the table below is the shortcut of the corresponding command on the right.

| Shortcut | Expanded command |
|----------|------------------|
| `v`     | `view`  |

##### Example usage

Firstly, use the [list command](#list-tasks-list-or-l) to get the `TASK-ID` of the task to be edited.

The user can just type `v` instead of `view`.
The screenshot below shows the output from ATHENA after entering `v 0`.

*insert screenshot*


### Exit program: `exit` or `ex`
This command exits the program.

#### Format: `exit` or `ex`

#### Expected output:
The screenshot below shows the output from ATHENA after entering `exit`.

*insert screenshot*