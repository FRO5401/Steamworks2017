package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;

/**
 *
 */
public class TargetHigh extends Command {
	
	private final double FLYWHEEL_SPEED = 0; //TODO
	
    public TargetHigh() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.drivebase);
        requires(Robot.visionprocessing);
        requires(Robot.shooter);
        requires(Robot.loader);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.reset();
    	Robot.shooter.startMotors();
    	Robot.shooter.setSetpoint(0);
    	Robot.shooter.enable();
    	SmartDashboard.putBoolean("Shooter OnOff", true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//get data from shooter pi from subsystem
    	//determine movement values
    	//send an auto drive command instructions
    	double angle = Robot.visionprocessing.findTargetAngle();
    	Robot.loader.runConveyors();
    	Robot.shooter.setSetpoint(FLYWHEEL_SPEED);
//    	Robot.drivebase.autoTurnAngle(angle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Robot.oi.getXboxstart_Operator();
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putBoolean("Shooter OnOff", false);
    	Robot.shooter.reset();
    	Robot.loader.stopConveyors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putBoolean("Shooter OnOff", false);
    	Robot.shooter.reset();
    	Robot.loader.stopConveyors();
    }
}
