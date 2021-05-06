# Universe Exploration
Universe Exploration is a strategy game where one must travel through star systems in search for civilized life.
In order to keep exploring, you must look for resources in order to keep on going.

# Requirements:
Following need to be manually installed:
* Gradle 4.7. (or just use the Gradle wrapper)
* Java 8

# Setup
Import Gradle project to IDE of your selection (or run command-line gradle build if that's your thing). At least
IntelliJ and Eclipse have both imported this project without any trouble.

# Running project
Easiest way is using IDE where very few setup steps are needed. These instructions are for IntelliJ Idea. Other IDEs
are yours to find out. Previously this project was aimed to be GWT compiled and run through browser. Due to
technological limitations browser is no longer supported. Following instructions apply to Android and Desktop.

## Tests
Test are run using *JUnit* and *JMockit*.

## Desktop
Create new *Application* runtime configuration. Select module *Desktop*. As for the run directory, one must select
Android assets folder (e.g. *universe-exploration\android\assets*).

Main class:
* com.universe.exploration.desktop.DesktopLauncher

## Android
Setup run configuration with *Android* module. You can also setup an emulator using Android com.universe.exploration.tools:
https://developer.android.com/studio/run/emulator

Recommended way of running is hooking up a real device to the computer and running UE on that one
instead of an emulator. Depending on your setup Android emulator might not run sufficiently enough. 

# Project status
Universe Exploration is still a work-in-progress. The source code is open for fork, reviews and so forth.
There are many lacking features.
