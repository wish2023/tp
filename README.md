# ATHENA

ATHENA aims to help students automate the process of organising their schedule.
After the user inputs pre-allocated time slots for work and relaxation, ATHENA figures out the best timetable based on the user’s needs.
The cool thing is, it can be updated anytime during the week.

ATHENA helps to reduce the amount of time and effort that users need to spend planning their time by finding free spaces to slot tasks in,
with the goal of reducing dead space (unused free time) in the user’s timetable. The planner will also make sure the user has enough time to eat, exercise and sleep.

The user can set up ATHENA to follow a fixed weekly routine, and only needs to update a task list.
ATHENA will then plan the timetable based on the importance and deadlines of the tasks in the list, making sure that the user is able to finish everything on time.

Refer to our [User Guide](https://ay2021s1-cs2113t-w12-2.github.io/tp/UserGuide.html) to learn more about how you can use ATHENA.
If you want to work on ATHENA, refer to our [Developer Guide](https://ay2021s1-cs2113t-w12-2.github.io/tp/DeveloperGuide.html) to learn more about the project. 

## Setting up in Intellij

Prerequisites: JDK 11 (use the exact version), update Intellij to the most recent version.

1. **Configure Intellij for JDK 11**, as described [here](https://se-education.org/guides/tutorials/intellijJdk.html).
1. **Import the project _as a Gradle project_**, as described [here](https://se-education.org/guides/tutorials/intellijImportGradleProject.html).
1. **Verify the set up**: After the importing is complete, locate `src/java/athena/Athena.java`, right-click it, and choose `Run Athena.main()`. If the setup is correct, you should see something like the one below:
   ```
   > Task :compileJava
   > Task :processResources NO-SOURCE
   > Task :classes
   
   > Task :Athena.main()
        ___   .__________.  __    __   _______  .__   __.      ___
    /   \  |          | |  |  |  | |   ____| |  \ |  |     /   \
   /  ^  \ `---|  |---` |  |__|  | |  |__    |   \|  |    /  ^  \
  /  /_\  \    |  |     |   __   | |   __|   |  . `  |   /  /_\  \
 /  _____  \   |  |     |  |  |  | |  |____  |  |\   |  /  _____  \
/__/     \__\  |__|     |__|  |__| |_______| |__| \__| /__/     \__\

Hello! I'm the Goddess of Wisdom and War, the mighty ATHENA!
...
Okay okay I'm not a Goddess but I am your Automated Timetable Helper Encourager n' Assistant!
What can I do? Are you challenging me to a duel?
...
Oh you mean in terms of tasks? Just type "help" to witness my mighty repertoire!
So, what would you like to do today?
   ```
   Type something and press enter to see what ATHENA can do.

## Build automation using Gradle

* This project uses Gradle for build automation and dependency management. It includes a basic build script as well (i.e. the `build.gradle` file).
* If you are new to Gradle, refer to the [Gradle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/gradle.html).

## Testing

### JUnit tests

* All tests are located under `src/test`.
* If you are new to JUnit, refer to the [JUnit Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/junit.html).

## Checkstyle

* A sample CheckStyle rule configuration is provided in this project.
* If you are new to Checkstyle, refer to the [Checkstyle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/checkstyle.html).

## CI using GitHub Actions

The project uses [GitHub actions](https://github.com/features/actions) for CI. When you push a commit to this repo or PR against it, GitHub actions will run automatically to build and verify the code as updated by the commit/PR.

## Documentation

`/docs` folder contains a skeleton version of the project documentation.

Steps for publishing documentation to the public:
1. If you are using this project template for an individual project, go your fork on GitHub.<br>
   If you are using this project template for a team project, go to the team fork on GitHub.
1. Click on the `settings` tab.
1. Scroll down to the `GitHub Pages` section.
1. Set the `source` as `master branch /docs folder`.
1. Optionally, use the `choose a theme` button to choose a theme for your documentation.
