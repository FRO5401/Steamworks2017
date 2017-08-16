package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5401.robot.autonomous.*;
import org.usfirst.frc.team5401.robot.Robot;

/**
 *
 */
public class VisionTrackingTower extends Command {

    public VisionTrackingTower() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.dummycameras);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double angleNeededToTurn = Robot.dummycameras.visionLoopSynchronized();
    	System.out.println(angleNeededToTurn);
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
