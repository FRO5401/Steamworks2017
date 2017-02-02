package org.usfirst.frc.team5401.robot.autonomous;

/**
 * Steamworks 2017 AutoTurnAngle code
 * (c) 2017 Bensalem High School Fightin' Robotic Owls
 */

import org.usfirst.frc.team5401.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoTurnAngle extends Command {
    boolean finished;
    float angle;

    public AutoTurnAngle(float desiredAngle) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivebase);
        angle = desiredAngle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	finished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	finished = Robot.drivebase.autoTurnAngle(angle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivebase.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivebase.stop();
    }
}
