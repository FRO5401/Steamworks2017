package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FeederControl extends Command {
	
	private int upDown;
	private int inOrOut;
	
    public FeederControl() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.infeed);
        
        upDown = 0;
        inOrOut = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	upDown  = Robot.oi.getXboxLeftY_Operator();
    	inOrOut = Robot.oi.getTriggers_Operator();
    	
    	Robot.infeed.armUpDown(upDown);
    	Robot.infeed.feederDirection(inOrOut);
    	Robot.infeed.reportPressure(); //pressure sensor
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
