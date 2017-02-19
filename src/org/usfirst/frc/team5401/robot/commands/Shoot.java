package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Starts the loader.
 * Note: The loader must be turned off manually.
 */
public class Shoot extends Command {
	
    public Shoot() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.loader);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.loader.runConveyorsAndMeteringMotor();
    	
    	//XXX Might have to give back control of xboxmove to drivebase
		System.out.println("Stop Targeting");
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
    	Robot.loader.stopConveyorsAndMeteringMotor();
    	System.out.println("Shoot Interrupted");
    }
}
