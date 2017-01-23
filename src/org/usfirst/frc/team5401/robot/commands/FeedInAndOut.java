package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FeedInAndOut extends Command {
	
	
	private int inOrOut; //inOrOutInput should be -1 or 1 depending on what direction the feeder run in
	
	private final double TRIGGER_THRESHOLD;
	
    public FeedInAndOut() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.infeed);
    	
    	inOrOut  = 0;

    	TRIGGER_THRESHOLD = .1;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.infeed.feederDirection(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.oi.readRightTrigger_Operator() > TRIGGER_THRESHOLD){
    		inOrOut = 1;
    	} else if (Robot.oi.readLeftTrigger_Operator() > TRIGGER_THRESHOLD){
    		inOrOut = -1;
    	} else {
    		inOrOut = 0;
    	}
    	
    	Robot.infeed.feederDirection(inOrOut);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.infeed.feederDirection(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
