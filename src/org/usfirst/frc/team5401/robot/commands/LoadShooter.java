package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;

/**
 *
 */
public class LoadShooter extends Command {
	
    public LoadShooter() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.loader);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putBoolean("Loader Conveyors", true);
    	Robot.loader.runConveyorsAndMeteringMotor();
    	//check button press
    	//if pressed, load ball
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//stop motors
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//stop motors
    	SmartDashboard.putBoolean("Loader Conveyors", false);
    	Robot.loader.stopConveyorsAndMeteringMotor();
    }
}
