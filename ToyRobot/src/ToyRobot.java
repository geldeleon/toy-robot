import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Simulation of a Toy Robot moving on a 5x5 dimension. It accepts commands from
 * standard input. Commands such as PLACE, MOVE, LEFT, RIGHT and REPORT are
 * accepted, ignored otherwise.
 * 
 * @author Gel De Leon Losito
 * @dateCreation Nov 1, 2014
 * 
 */
public class ToyRobot {

	public static final String CMD_PLACE = "PLACE";
	public static final String CMD_MOVE = "MOVE";
	public static final String CMD_LEFT = "LEFT";
	public static final String CMD_RIGHT = "RIGHT";
	public static final String CMD_REPORT = "REPORT";

	public static final String NORTH = "NORTH";
	public static final String SOUTH = "SOUTH";
	public static final String EAST = "EAST";
	public static final String WEST = "WEST";

	/**
	 * number of units the robot can move forward at a time
	 */
	private int movement = 1;

	/**
	 * x start position of the robot
	 */
	private int xOrigin = 0;

	/**
	 * y start position of the robot
	 */
	private int yOrigin = 0;

	/**
	 * maximum number of units the robot can move west or east
	 */
	private int xLimit = 5;

	/**
	 * maximum number of units the robot can move north or south
	 */
	private int yLimit = 5;

	/**
	 * current X position of the robot
	 */
	private int xPos;

	/**
	 * current Y position of the robot
	 */
	private int yPos;

	/**
	 * direction the robot is currently facing
	 */
	private String direction;

	/**
	 * Flag that indicates whether the line is the first valid command.
	 */
	private boolean isFirst = true;

	/**
	 * Create a robot with default settings.
	 */
	public ToyRobot() {
	}

	/**
	 * Create a robot with custom settings.
	 * 
	 * @param movement
	 * @param xOrigin
	 * @param yOrigin
	 * @param xLimit
	 * @param yLimit
	 */
	public ToyRobot(int movement, int xOrigin, int yOrigin, int xLimit,
			int yLimit) {
		this.movement = movement;
		this.xOrigin = xOrigin;
		this.yOrigin = yOrigin;
		this.xLimit = xLimit;
		this.yLimit = yLimit;
	}

	/**
	 * Accept PLACE with valid arguments, MOVE, LEFT, RIGHT or REPORT as valid
	 * command.
	 * 
	 * @param command
	 * @return true if the command is performed, false otherwise
	 */
	public boolean acceptCommand(String command) {

		boolean valid = false;

		final int IDX_CMD = 0;
		final int IDX_CMD_ARGS = 1;

		final int IDX_PLACE_X = 0;
		final int IDX_PLACE_Y = 1;
		final int IDX_PLACE_DIR = 2;
		final int TOT_PLACE_ARGS = 3;

		String[] commandArr = command.split(" ");
		command = commandArr[IDX_CMD];

		if (command.equals(CMD_PLACE)) {
			if (commandArr.length > 1 && commandArr[IDX_CMD_ARGS] != null
					&& !"".equals(commandArr[IDX_CMD_ARGS])) {
				String[] args = commandArr[1].split(",");
				// total allowed arguments for PLACE is 3
				if (args.length == TOT_PLACE_ARGS) {
					try {
						xPos = Integer.parseInt(args[IDX_PLACE_X]);
						// do not allow the robot to be placed outside the
						// square
						if (!(xPos >= xOrigin && xPos <= xLimit)) {
							System.out
									.println("Position X outside the square is not allowed");
						} else {

							yPos = Integer.parseInt(args[IDX_PLACE_Y]);
							if (!(yPos >= yOrigin && yPos <= yLimit)) {
								System.out
										.println("Position Y outside the square is not allowed");

							} else {

								if (isDirectionValid(args[IDX_PLACE_DIR])) {
									direction = args[IDX_PLACE_DIR];

									// x-y coordinates and direction are
									// valid
									place(xPos, yPos, direction);
									valid = true;
								} else {
									System.out.println("Invalid direction = "
											+ args[IDX_PLACE_DIR]);

								}
							}
						}
					} catch (NumberFormatException nfe) {
						System.out.println("Invalid position: x = "
								+ args[IDX_PLACE_X] + ", y = "
								+ args[IDX_PLACE_Y]);
					}
				} else {
					System.out
							.println("Invalid number of arguments for PLACE command");
				}
			} else {
				System.out
						.println("Missing arguments[x,y,direction] for PLACE command!");
			}

		} else {
			if (!isFirst && commandArr.length == 1) {
				if (command.equals(CMD_MOVE)) {
					move();
					valid = true;

				} else if (command.equals(CMD_LEFT)) {
					left();
					valid = true;

				} else if (command.equals(CMD_RIGHT)) {
					right();
					valid = true;

				} else if (command.equals(CMD_REPORT)) {
					report();
					valid = true;
				}
			} else {
				System.out.println("Invalid command");
			}

		}
		if (valid) {
			isFirst = false;
		}

		return valid;
	}

	/**
	 * Place the robot on position X, Y and facing either NORTH, SOUTH, EAST OR
	 * WEST.
	 * 
	 * @param xPos
	 * @param yPos
	 * @param direction
	 */
	private void place(int xPos, int yPos, String direction) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.direction = direction;
	}

	/**
	 * Move the robot forward by specified unit (MOVEMENT) within specified
	 * dimension (X_LIMIT by Y_LIMIT).
	 */
	private void move() {
		int pos = 0;
		if (direction.equals(NORTH)) {
			pos = yPos + movement;
			if (pos <= yLimit) {
				yPos = pos;
			}

		} else if (direction.equals(EAST)) {
			pos = xPos + movement;
			if (pos <= xLimit) {
				xPos = pos;
			}

		} else if (direction.equals(SOUTH)) {
			pos = yPos - movement;
			if (pos >= yOrigin) {
				yPos = pos;
			}

		} else if (direction.equals(WEST)) {
			pos = xPos - movement;
			if (pos >= xOrigin) {
				xPos = pos;
			}
		}
	}

	/**
	 * Rotate the robot by 90 degrees to the left.
	 */
	private void left() {
		if (direction.equals(NORTH)) {
			direction = WEST;

		} else if (direction.equals(EAST)) {
			direction = NORTH;

		} else if (direction.equals(SOUTH)) {
			direction = EAST;

		} else if (direction.equals(WEST)) {
			direction = SOUTH;
		}
	}

	/**
	 * Rotate the robot by 90 degrees to the right.
	 */
	private void right() {
		if (direction.equals(NORTH)) {
			direction = EAST;

		} else if (direction.equals(EAST)) {
			direction = SOUTH;

		} else if (direction.equals(SOUTH)) {
			direction = WEST;

		} else if (direction.equals(WEST)) {
			direction = NORTH;
		}
	}

	/**
	 * Write on the console the current coordinates and face of the robot.
	 */
	private void report() {
		StringBuilder output = new StringBuilder("OUTPUT: ");
		output.append(xPos);
		output.append(",");
		output.append(yPos);
		output.append(",");
		output.append(direction);
		System.out.println(output.toString());
	}

	/**
	 * Entry point of the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			// Get input from the console
			InputStreamReader in = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(in);

			// create a robot that moves forward by 1 unit within an area of 5x5
			// units
			ToyRobot toyRobot = new ToyRobot();

			String command;
			while ((command = br.readLine()) != null) {
				if (command != null && !"".equals(command.trim())) {
					toyRobot.acceptCommand(command.trim());
				}
			}

		} catch (IOException io) {
			System.out.println("Error reading commands");
			io.printStackTrace();
		}
	}

	/**
	 * @param direction
	 * @return true if the direction is valid, false otherwise
	 */
	private static boolean isDirectionValid(String direction) {
		if (direction.equals(NORTH) || direction.equals(SOUTH)
				|| direction.equals(EAST) || direction.equals(WEST)) {
			return true;
		}
		return false;
	}
}
