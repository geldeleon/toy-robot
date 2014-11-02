#update PATH to include the JDK bin directory
SET PATH=%PATH%;C:\Program Files\Java\jdk1.6.0_45\bin

#change to your toyRobot lib directory
cd G:\git\toy-robot\ToyRobot\lib
 
java -cp toyRobot.jar;junit-4.10.jar org.junit.runner.JUnitCore ToyRobotTest
pause