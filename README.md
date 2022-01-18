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
