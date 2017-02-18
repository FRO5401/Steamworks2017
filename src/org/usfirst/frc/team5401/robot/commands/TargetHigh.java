package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;

/**
 *
 */
public class TargetHigh extends Command {
		
    public TargetHigh() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.drivebase);
        requires(Robot.visionprocessing);
//        requires(Robot.shooter);
        requires(Robot.loader);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.shooter.reset();
//    	Robot.shooter.startMotors();
//    	Robot.shooter.setSetpoint(RobotMap.SHOOTER_SETPOINT);
//    	Robot.shooter.enable();
    	SmartDashboard.putBoolean("Shooter OnOff", true);
    	SmartDashboard.putNumber("Target Angle", -99);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//get data from shooter pi from subsystem
    	//determine movement values
    	//send an auto drive command instructions
    	SmartDashboard.putBoolean("AutoTargeting", true);
    	double angle = Robot.visionprocessing.readTargetingData();
    	SmartDashboard.putNumber("Target Angle", angle);

//    	Robot.loader.runConveyors();
//    	Robot.shooter.setSetpoint(RobotMap.SHOOTER_SETPOINT);
//    	Robot.drivebase.autoTurnAngle(angle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.oi.getXboxBack_Operator();//TODO Make sure this is the button
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putBoolean("Shooter OnOff", false);
		System.out.println("Stop Targeting");
    	Robot.visionprocessing.terminateTargeting(true);
//    	Robot.shooter.reset();
//    	Robot.loader.stopConveyors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putBoolean("Shooter OnOff", false);
//    	Robot.shooter.reset();
//    	Robot.loader.stopConveyors();
    }
}
