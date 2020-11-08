# User Guide

- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
    - [View Help](#view-help-help)
    - [Add a task](#add-a-task-add-or-a)  
    - [List tasks](#list-tasks-list-or-l)  
    - [Edit task](#edit-task-edit-or-e)  
    - [Mark task as done](#mark-task-as-done-done-or-dn)  
    - [Delete task](#delete-task-delete-or-dl)
    - [View task](#view-task-view-or-v)
    - [Exit](#exit-program-exit-or-ex)
    - [Saving the data](#saving-the-data)
- [FAQ](#faq)    
- [Command Summary](#command-summary)  

## Introduction

Welcome and thank you for choosing ATHENA! ATHENA is your Automated Timetable Helper Encourager n' Assistant and is a desktop daily life planner optimized for use via a Command Line Interface (CLI).

ATHENA uses algorithmic optimisation to give you the best timetable that allows you to make the most of your time. With ATHENA, you can save time on planning your timetable and be more efficient with your time.

You can take a look at the table of contents above if you are looking for a specific command, or you can begin by looking at the **[Quick Start](#quick-start)** section below.

We hope you enjoy using ATHENA and start being more productive today!

## Quick Start

1. Ensure that you have **Java 11** or above installed.
2. Download the latest version of **ATHENA** [here](https://github.com/AY2021S1-CS2113T-W12-2/tp/releases).
3. Copy the downloaded Athena.jar into your **Desktop**.
4. Open the terminal/command prompt and enter `cd Desktop`.
5. Then, enter `java -jar Athena.jar`.
6. A welcome message as seen below will be shown:

*SCREENSHOT*

If you are new to the command line and require a guide: 
* Click [here](https://www.minitool.com/lib/cmd.html) if you use Windows
* Click [here](https://flaviocopes.com/cli-for-beginners/) if you use Mac
 

## Features 
Notes about the command format:
* Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in `add n/NAME`, NAME is a parameter which the user provides, such as: `add n/Assignment1`.
* Parameters can be in any order.
e.g. if the command specifies `n/NAME t/TIME`, `t/TIME n/NAME` is also acceptable.
* Any unknown parameter type entered by the user that does not fit the parameters accepted in a respective feature will be ignored.
e.g `add n/NAME p/PARAMETER`, since `p/` is an unknown parameter type for `add` command, `p/PARAMETER` is ignored.
* If user types in 2 or more parameter description for each parameter type, only the first description will be taken.
e.g. if user types `n/NAME1 n/NAME2`, only `NAME1` will be recorded as the task's name.
* Items in square brackets are optional. e.g in `n/NAME  [d/DURATION]`, the parameters can be provided as `n/Task1 d/1` or as `n/Task1`.
(Refer to the Intermediate Usage section of each command for more information)
* For some commands, if no parameters are specified, the command will execute using the default values for each parameter.
* For dates, the program follows the **DD-MM-YYYY** format.
* For time, the program follows the **HHMM** format.

### View Help: `help`
Prints out a message on how to use ATHENA.

#### Format: `help`

#### Expected output

*insert screenshot*