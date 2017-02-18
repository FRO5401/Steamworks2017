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
//    	Robot.compressorsubsystem.switchState();
    	//TODO SmartDashboard
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.compressorsubsystem.switchState();
    	//TODO SmartDashboard

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
    	Robot.compressorsubsystem.switchState();
    	//TODO SmartDashboard
    }
}
