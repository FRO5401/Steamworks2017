package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Climb extends Command {

	
	//boolean UpperLimit;
	//boolean LowerLimit;
    public Climb() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires climber
    	//UpperLimit = 0;
    	//TouchLimit = 0;
    }
    
    

    // Called just before this Command runs the first time
    protected void initialize() {
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//UpperLimit =getUpperlimitswitch
    	//TouchLimit =getLowerLimitSwitch
    	//while touch rope sensor is true and while touch upper limit sensor is false
    	//give power to victor motors
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
