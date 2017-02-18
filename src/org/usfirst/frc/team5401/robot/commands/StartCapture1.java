package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StartCapture1 extends Command {

    public StartCapture1() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.dummycamera);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.dummycamera.startAutomaticCaptureCamera1();
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
