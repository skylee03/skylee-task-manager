---
permalink: /
redirect_from:
  - /about
layout: page
title: STM User Guide
---

Skylee Task Manager (STM) is a **cross-platform command-line app for managing tasks**. If you can type fast, STM can get your task management tasks done faster than traditional GUI apps.

- Table of Contents
{:toc}

---

## Quick start

1. Ensure you have Java `11` or above installed in your computer.
1. Download the latest `ip.jar` from [here](https://github.com/skylee03/skylee-task-manager/releases).
1. Copy the file to the folder you want to use as the _home folder_ for your STM.
1. Open a command terminal, `cd` into the folder you put the JAR file in, and use the `java -jar ip.jar` command to run the application.

    A welcome message similar to the below should appear in a few milliseconds.
    ```
    ____________________________________________________________
    Hello! I'm Skylee!
    What can I do for you?
    ____________________________________________________________
    ```

1. Type the command after the prompt `> ` and press Enter to execute it.
1. Refer to the [Features](#features) below for details of each command.

---

## Features

:information_source: **Notes about the command format:**

* Words in `UPPER_CASE` are the parameters to be supplied by the user.
  
  For example, in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo Add a brief User Guide`.

* Parameters cannot be in any order.
  
  For example, if the command specifies `DESCRIPTION /from START_TIME /to END_TIME`, `DESCRIPTION /to END_TIME /from START_TIME` is not acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `list` and `bye`) will be ignored.
  
  For example, if the command specifies `list 123`, it will be interpreted as `list`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

### Adding a todo: `todo`

* Adds a todo to the task list.

* Format: `todo DESCRIPTION​`

* Example: `todo Add a brief User Guide`

### Adding a deadline: `deadline`

* Adds a deadline to the task list.

* Format: `deadline DESCRIPTION /by TIME`
  
  :information_source: `TIME` must follow the ISO Date Time Format `yyyy-MM-dd'T'HH:mm:ss`.

* Example: `deadline Submit the final version 2023-10-06T23:59:59`

### Adding an event: `event`

* Adds an event to the task list.

* Format: `event DESCRIPTION /from START_TIME /to END_TIME`

  :information_source: `START_TIME` and `END_TIME` must follow the ISO Date Time Format `yyyy-MM-dd'T'HH:mm:ss`.

* Example: `event Final Exam /from 2023-11-28T13:00:00 /to 2023-11-28T14:29:59`

### Deleting a task: `delete`

* Deletes a task from the task list.

* Format: `delete TASK_ID`

  :information_source: `TASK_ID` refers to the index number shown in the displayed task list and **must be a positive integer**.

* Example: `delete 2`

### Marking a task: `mark`

* Marks a task in the task list.

* Format: `mark TASK_ID`

  :information_source: `TASK_ID` refers to the index number shown in the displayed task list and **must be a positive integer**.

* Example: `mark 3`

### Unmarking a task: `unmark`

* Unmarks a task in the task list.

* Format: `unmark TASK_ID`

  :information_source: `TASK_ID` refers to the index number shown in the displayed task list and **must be a positive integer**.

* Example: `unmark 1`

### Listing tasks: `list`

* Lists tasks in the task list.

* Format: `list`

### Finding tasks by keyword: `find`

* Lists tasks whose description contains the keyword. The search is case-insensitive.

* Format: `find KEYWORD`

* Example: `find final`

### Finding tasks by date: `date`

* Lists tasks occurring on the given date.

* Format: `date DATE`

  :information_source: `DATE` must follow the ISO Date Format `yyyy-MM-dd`.

* Example: `date 2023-09-26`

### Exiting: `bye`

* Saves the task list data and exits the app.

* Format: `bye`

### Saving the data

* Task list data are saved in the hard disk after the `bye` command. There is no way to save manually.

### Editing the data file

* Task list data are saved automatically as a text file `[JAR file location]/data/skylee.txt`. Advanced users are welcome to update data directly by editing that data file.

:warning: **Caution:**
If your changes to the data file makes its format invalid, STM will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.

---

## FAQ

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous STM home folder.

---

## Command summary

Action | Format
-|-
Add a todo | `todo DESCRIPTION`
Add a deadline | `todo DESCRIPTION /by TIME`
Add an event | `todo DESCRIPTION /from START_TIME /to END_TIME`
Delete a task | `delete TASK_ID`
Mark a task | `mark TASK_ID`
Unmark a task | `unmark TASK_ID`
List tasks | `list`
Find tasks by keyword | `find KEYWORD`
Find tasks by date | `date DATE`
Save & Exit | `bye`