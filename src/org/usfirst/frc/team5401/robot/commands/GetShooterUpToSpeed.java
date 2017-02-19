package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Starts the flywheels. Stops running the command when the flywheels are up to speed.
 * DOES NOT stop the flywheels.
 */
public class GetShooterUpToSpeed extends Command {
	
	private boolean upToSpeed;
	private double targetSpeed;
	private double currentSpeed;
	private final double THRESH;
	
    public GetShooterUpToSpeed() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooter);
        upToSpeed = false;
        targetSpeed = 0;
        currentSpeed = 0;
        THRESH = 200;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.startMotors();
    	targetSpeed = Math.abs(Robot.shooter.getTargetSpeed());
    	upToSpeed = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentSpeed = Math.abs(Robot.shooter.getVelocity());
    	
    	if (currentSpeed <= targetSpeed + THRESH || currentSpeed >= targetSpeed - THRESH){
    		upToSpeed = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return upToSpeed;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}