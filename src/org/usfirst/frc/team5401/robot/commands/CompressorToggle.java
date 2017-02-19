package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CompressorToggle extends Command {

    public CompressorToggle() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.compressorsubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	 //XXX Used to be in execute, still have to test
    	//TODO SmartDashboard
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//end() does nothing because this command toggles the state
    	Robot.compressorsubsystem.switchState();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//end() does nothing because this command toggles the state
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.compressorsubsystem.stopCompressor();
    	//TODO SmartDashboard
    	System.out.println("CompressorToggle Interrupted");
    }
}
