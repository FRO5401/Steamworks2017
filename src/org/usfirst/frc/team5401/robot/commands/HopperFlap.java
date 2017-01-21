package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.subsystems.Hopper;

/**
 *
 */
public class HopperFlap extends Command {
	
	private int openClose;
	
    public HopperFlap(int direction {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.hopper);
        openClose = direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(openClose == 1){
    		Robot.hopper.flapOpen();
    	}
    	else if (openClose == -1){
    		Robot.hopper.flapClose();
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
    }
}
