package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Climb extends Command {

	private boolean input;
	
    public Climb() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    	
    	input = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	input = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	input = true;
    	
    	if (input){
    		Robot.climber.climbUp();
    	} else {
    		Robot.climber.climbStop();
    		input = false;
    	}    	

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !input;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.climber.climbStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.climber.climbStop();
    }
}
