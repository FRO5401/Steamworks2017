package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *This is not used
 */
public class FeederUpDown extends Command {

	private int upDown;
	private int L3Operator_State;
    public FeederUpDown() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(Robot.infeed);
//    	upDown = direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.infeed.armUpDown(1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
    	Robot.infeed.armUpDown(-1);
    }
}
