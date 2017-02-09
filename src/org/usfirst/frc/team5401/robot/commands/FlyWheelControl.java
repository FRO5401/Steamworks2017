package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FlyWheelControl extends Command {

	private final double FLYWHEEL_SPEED = 0; //TODO 
	
    public FlyWheelControl() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.reset();
    	//Robot.shooter.startMotors();
    	//Robot.shooter.setSetpoint(0);
    	//Robot.shooter.enable();
    	SmartDashboard.putBoolean("Shooter OnOff", true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.shooter.setSetpoint(FLYWHEEL_SPEED);
    	Robot.shooter.startMotors();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Not necessary for toggleWhenPressed
//    	Robot.shooter.reset();
//    	Robot.shooter.setSetpoint(0);
    	Robot.shooter.stop();
    }

    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putBoolean("Shooter OnOff", false);
    	Robot.shooter.reset();
    }
}
