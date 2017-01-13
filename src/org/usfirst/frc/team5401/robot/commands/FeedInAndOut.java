package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FeedInAndOut extends Command {
	//int inOrOutVariable
	
    public FeedInAndOut(double feedInOrOutInput) {//inOrOutInput should be -1 or 1 depending on what direction the feeder run in
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	//requires(Robot.infeed);
    	//inOrOutVariable = feedInOrOutInput;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Set motor to feedInOrOutVariable
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
    }
}
