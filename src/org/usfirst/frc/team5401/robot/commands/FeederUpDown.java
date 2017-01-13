package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FeederUpDown extends Command {

	//double feederUpDownVariable
	
    public FeederUpDown(double feederUpDownInput) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	//requires(Robot.infeeder)
    	//feederUpDownVariable = feederUpDownInput
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//set motor to feederUpDownVariable
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
