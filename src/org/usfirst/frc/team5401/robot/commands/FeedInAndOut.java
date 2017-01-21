package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FeedInAndOut extends Command {
	
	
	private int inOrOut;
	
    public FeedInAndOut(int direction) {//inOrOutInput should be -1 or 1 depending on what direction the feeder run in
        // Use requires() here to declare subsystem dependencies
        requires(Robot.infeed);
    	
    	//requires(Robot.infeed);
    	inOrOut = direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.infeed.feederDirection(inOrOut);
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
