# P0
First Revature Project. A solo project based on Scala and MySQL

Concept - A Lane Runner Type Game
I would like to make a table based game in which the player chooses 1 of 3 “lanes” to run down. Along the lane the player will “collect” points, buffs, and debuffs. The trick is that the user does not know what is on each path, but is given an opportunity to perform a transformation on the lanes before picking one, and can swap lanes during traversal.

Implementation - 3 “lane” Tables
The three “lanes” that the player chooses betweens and transforms is in actuality a 3 column 200 row MySQL table. The points, buffs, and debuffs will be represented by numbers 0-9. The lanes may be transformed in certain ways, some examples being reordering the rows removing 5 rows altogether. This is an example of how the numbers may affect the player:

0: No points
1: 1 Point Awarded
2: 2 Points Awarded
3: 3 Points Awarded
4: 4 Points Awarded
5: 5 Points Awarded
6: Buff all even number of points awarded by 1.5x
7: Buff all odd number of points awarded by 1.5x
8: Debuff all even number of points awarded by 0.5x
9: Debuff all odd number of points awarded by 0.5x

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

### Project 0
A Scala CLI (Command Line Interface) application.  Data should be  persisted to MongoDB OR MySql.  The functionality of the application beyond that is up to you, but here are a few suggestions:
- journal app
- banking app
- business management system
- simple text-based game
- productivity tool
- ...?

## Application Requirements
- Your application must be able to and store the data contained within those files in a database
- Your application should have a CLI where users can interact with the application while it is running
- Other than those requirements, the kind of application you have is up to you.

## Tech Stack
- Scala 2.11 onwards
- Database
- sbt
- git SCM (+ GitHub)

## Presentation
- 5 minute live demo.  Present the application, not the code.

## Due Date
- Project 0 will be presented 

Finishing your MVP (Minimum Viable Product) as early as possible before iterating new features upon the project is highly recommended.  Plan ahead, and be sure to reach out to others when you need guidance (or offer your own to those in need).  You are *REQUIRED* to be able to explain your project functionality and write your own code, but feel free to have in-depth discussions with others.

## Git Help

For this project, we only need basic Git and GitHub usage.  You should do these steps once, to set up:
- Make a Github Account
- Create a repository
- Use the git clone command on your local machine to clone the repository to your local machine

You should run the following commands frequently, while working on your project, in your git repo:
- git status (just to see what has changed)
- git add .
- git commit -m "a message describing the work you've done goes here"
- git push

If you receive an error message that you aren't in a git repo, cd to your cloned git repository on your local machine.
