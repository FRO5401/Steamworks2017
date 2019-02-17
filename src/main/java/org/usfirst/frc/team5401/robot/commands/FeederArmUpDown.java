package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *For Autonomous Use Only
 *upDown == 1, arm goes out
 *upDown == -1, arm goes in
 */
public class FeederArmUpDown extends Command {

	private int upDown;
	
    public FeederArmUpDown(int armDirection) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.infeed);
    	
    	upDown = armDirection;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.infeed.armUpDown(upDown);
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
