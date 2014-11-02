toy-robot
=========

The application simulates a Toy Robot that moves within an area of 5x5 units.
Valid commands are:
PLACE - put the robot on x,y position facing to either NORTH, SOUTH, EAST or WEST
MOVE - move the robot forward by 1 unit
LEFT - turn the robot 90 degrees to the left
RIGHT - turn the robot 90 degrees to the right
REPORT - tell the current position of the robot and the direction it is currently facing 
       in standard output 

Commands are entered in standard input.

=======================
Java requirement: JDK6+

To run the application via Windows command line:
1) Download the toy-robot from github:
git clone https://github.com/geldeleon/toy-robot.git 

2) Open the batch file "toyRobot.bat" and edit the following lines to point to your JDK bin and toyRobot lib path:
SET PATH=%PATH%;C:\Program Files\Java\jdk1.6.0_45\bin
cd G:\git\toy-robot\ToyRobot\lib

3) Save and run the batch file.

To run the test:
1) Open the file "toyRobotTest.bat" and edit same lines as #2 above.
2) Save and run the batch file.

=======================
Normal run:
G:\git\toy-robot\ToyRobot\lib>java -cp toyRobot.jar ToyRobot
PLACE 0,0,NORTH
LEFT
REPORT
OUTPUT: 0,0,WEST

With invalid input:
G:\git\toy-robot\ToyRobot\lib>java -cp toyRobot.jar ToyRobot
BAD COMMAND
Invalid command

TEST RESULTS:
G:\git\toy-robot\ToyRobot\lib>java -cp toyRobot.jar;junit-4.10.jar org.junit.run
ner.JUnitCore ToyRobotTest
JUnit version 4.10
.testAcceptCommandInvalidCommands
MOVE
Invalid command
LEFT
Invalid command
RIGHT
Invalid command
REPORT
Invalid command
BAD
Invalid command
BAD COMMAND
Invalid command
place 1,2,NORTH
Invalid command
PLACE 1,2,north
Invalid direction = north
move
Invalid command
left
Invalid command
right
Invalid command
report
Invalid command
PLACE
Missing arguments[x,y,direction] for PLACE command!
PLACE 2,NORTH
Invalid number of arguments for PLACE command
PLACE 2
Invalid number of arguments for PLACE command
PLACE 1,2,NORTH,EXCESS
Invalid number of arguments for PLACE command
PLACE x,2,NORTH
Invalid position: x = x, y = 2
PLACE 1,y,NORTH
Invalid position: x = 1, y = y
PLACE x,y,NORTH
Invalid position: x = x, y = y
PLACE 1,2,NORT
Invalid direction = NORT
PLACE -1,0,NORTH
Position X outside the square is not allowed
PLACE 6,0,NORTH
Position X outside the square is not allowed
PLACE 0,-1,NORTH
Position Y outside the square is not allowed
PLACE 0,6,NORTH
Position Y outside the square is not allowed
.testAcceptCommandValid1
PLACE 0,0,NORTH
MOVE
REPORT
OUTPUT: 0,1,NORTH
.testAcceptCommandValid2
PLACE 0,0,NORTH
LEFT
REPORT
OUTPUT: 0,0,WEST
.testAcceptCommandValid3
PLACE 1,2,EAST
MOVE
MOVE
LEFT
MOVE
REPORT
OUTPUT: 3,3,NORTH
.testAcceptCommandValid4
PLACE 5,5,EAST
MOVE
LEFT
REPORT
OUTPUT: 5,5,NORTH
MOVE
REPORT
OUTPUT: 5,5,NORTH
LEFT
MOVE
REPORT
OUTPUT: 4,5,WEST
.testAcceptCommandValid5
PLACE 0,0,WEST
MOVE
LEFT
REPORT
OUTPUT: 0,0,SOUTH
MOVE
REPORT
OUTPUT: 0,0,SOUTH
LEFT
MOVE
REPORT
OUTPUT: 1,0,EAST

Time: 0.016

OK (6 tests)


