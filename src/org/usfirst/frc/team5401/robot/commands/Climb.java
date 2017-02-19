package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Climb extends Command {

	private int input;
	
    public Climb(int direction) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    	input = direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (input == 1) {
    		Robot.climber.climbUp();	
    	} else if (input == 0) {
    		Robot.climber.climbStop();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.climber.climbStop();
    	System.out.println("Climb Interrupted");
    }
}
