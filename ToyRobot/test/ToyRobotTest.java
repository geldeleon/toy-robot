import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test cases for Toy Robot simulator.
 * 
 * @author Gel De Leon Losito
 * @dateCreation Nov 1, 2014
 * 
 */
public class ToyRobotTest {

	@Test
	public void testAcceptCommandInvalidCommands() {
		System.out.println("testAcceptCommandInvalidCommands");
		ToyRobot toyRobot = new ToyRobot();

		// first command must be PLACE
		testInvalid(toyRobot, "MOVE");
		testInvalid(toyRobot, "LEFT");
		testInvalid(toyRobot, "RIGHT");
		testInvalid(toyRobot, "REPORT");

		// unacceptable commands
		testInvalid(toyRobot, "BAD");
		testInvalid(toyRobot, "BAD COMMAND");

		// should be uppercase
		testInvalid(toyRobot, "place 1,2,NORTH");
		testInvalid(toyRobot, "PLACE 1,2,north");
		testInvalid(toyRobot, "move");
		testInvalid(toyRobot, "left");
		testInvalid(toyRobot, "right");
		testInvalid(toyRobot, "report");

		// PLACE has no arguments
		testInvalid(toyRobot, "PLACE");

		// PLACE has missing arguments
		testInvalid(toyRobot, "PLACE 2,NORTH");
		testInvalid(toyRobot, "PLACE 2");

		// PLACE has excess arguments
		testInvalid(toyRobot, "PLACE 1,2,NORTH,EXCESS");
		// x position is not a number
		testInvalid(toyRobot, "PLACE x,2,NORTH");
		// y position is not a number
		testInvalid(toyRobot, "PLACE 1,y,NORTH");
		// x and y are not a number
		testInvalid(toyRobot, "PLACE x,y,NORTH");
		// invalid direction
		testInvalid(toyRobot, "PLACE 1,2,NORT");

		// robot is outside the 5x5 dimension
		testInvalid(toyRobot, "PLACE -1,0,NORTH");
		testInvalid(toyRobot, "PLACE 6,0,NORTH");
		testInvalid(toyRobot, "PLACE 0,-1,NORTH");
		testInvalid(toyRobot, "PLACE 0,6,NORTH");
	}

	private void testInvalid(ToyRobot toyRobot, String command) {
		System.out.println(command);
		assertFalse(toyRobot.acceptCommand(command));
	}

	@Test
	public void testAcceptCommandValid1() {
		System.out.println("testAcceptCommandValid1");
		ToyRobot toyRobot = new ToyRobot();
		testValid(toyRobot, "PLACE 0,0,NORTH");
		testValid(toyRobot, "MOVE");
		testValid(toyRobot, "REPORT");
	}

	@Test
	public void testAcceptCommandValid2() {
		System.out.println("testAcceptCommandValid2");
		ToyRobot toyRobot = new ToyRobot();
		testValid(toyRobot, "PLACE 0,0,NORTH");
		testValid(toyRobot, "LEFT");
		testValid(toyRobot, "REPORT");
	}

	@Test
	public void testAcceptCommandValid3() {
		System.out.println("testAcceptCommandValid3");
		ToyRobot toyRobot = new ToyRobot();
		testValid(toyRobot, "PLACE 1,2,EAST");
		testValid(toyRobot, "MOVE");
		testValid(toyRobot, "MOVE");
		testValid(toyRobot, "LEFT");
		testValid(toyRobot, "MOVE");
		testValid(toyRobot, "REPORT");
	}

	@Test
	public void testAcceptCommandValid4() {
		System.out.println("testAcceptCommandValid4");
		ToyRobot toyRobot = new ToyRobot();
		testValid(toyRobot, "PLACE 5,5,EAST");
		testValid(toyRobot, "MOVE");
		testValid(toyRobot, "LEFT");
		testValid(toyRobot, "REPORT");
		testValid(toyRobot, "MOVE");
		testValid(toyRobot, "REPORT");
		testValid(toyRobot, "LEFT");
		testValid(toyRobot, "MOVE");
		testValid(toyRobot, "REPORT");
	}

	@Test
	public void testAcceptCommandValid5() {
		System.out.println("testAcceptCommandValid5");
		ToyRobot toyRobot = new ToyRobot();
		testValid(toyRobot, "PLACE 0,0,WEST");
		testValid(toyRobot, "MOVE");
		testValid(toyRobot, "LEFT");
		testValid(toyRobot, "REPORT");
		testValid(toyRobot, "MOVE");
		testValid(toyRobot, "REPORT");
		testValid(toyRobot, "LEFT");
		testValid(toyRobot, "MOVE");
		testValid(toyRobot, "REPORT");
	}

	private void testValid(ToyRobot toyRobot, String command) {
		System.out.println(command);
		assertTrue(toyRobot.acceptCommand(command));
	}
}
