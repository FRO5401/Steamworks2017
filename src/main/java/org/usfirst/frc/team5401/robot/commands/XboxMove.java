package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class XboxMove extends Command {
	private final double MINIMUM_VELOCITY_FOR_HIGH_GEAR;
	private final double MAXIMUM_VELOCITY_FOR_LOW_GEAR;
	
	double velocitySample1;
	double velocitySample2;

    public XboxMove() {
    	MINIMUM_VELOCITY_FOR_HIGH_GEAR = 35;
    	MAXIMUM_VELOCITY_FOR_LOW_GEAR  = 45;
    	
    	velocitySample1 = 0;
    	velocitySample2 = 0;
        // Use requires() here to declare subsystem dependencies

    	requires(Robot.drivebase);
    	System.out.println("XboxMove Constructed");
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivebase.shiftGearHighToLow();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double angle = Robot.drivebase.reportGyro();
    	SmartDashboard.putNumber("Gyro", angle);
    	Robot.drivebase.reportGyro();
    	
    	double slew      = Robot.oi.readXboxLeftX_Driver() * -1;
    	
    	double  throttle   = Robot.oi.readRightTrigger_Driver();
    	double  reverse    = Robot.oi.readLeftTrigger_Driver();
    	boolean precision  = Robot.oi.getPrecision_Driver();
    	boolean turn       = Robot.oi.getTurnButton_Driver();
    	
    	boolean gearShiftLow  = Robot.oi.getXboxBack_Driver();
    	boolean gearShiftHigh = Robot.oi.getXboxStart_Driver();
    	
    	//Gear Shifting
    	if (gearShiftHigh){
    		Robot.drivebase.shiftGearLowToHigh();
    	} 
    	else if (gearShiftLow){
    		Robot.drivebase.shiftGearHighToLow();
    	}
    	  	
    	double right = 0, left = 0, sensitivity;
    	
    	//Precision
    	if (precision){
    		sensitivity = RobotMap.DRIVE_SENSITIVITY_PRECISE;
    	} 
    	else {
    		sensitivity = RobotMap.DRIVE_SENSITIVITY_DEFAULT;
    	}
    	
    	//Forward/Backward + Left/Right (While Driving)
    	if (!turn){
    		if (slew > RobotMap.DRIVE_THRESHHOLD){
    			left  = (throttle - reverse) * sensitivity;
    			right = (throttle - reverse) * sensitivity * (1 - slew);
    		} 
    		else if (slew < (-1 *RobotMap.DRIVE_THRESHHOLD)){
    			left  = (throttle - reverse) * sensitivity * (1 + slew);
    			right = (throttle - reverse) * sensitivity;
    		} 
    		else {
    			left  = (throttle - reverse) * sensitivity;
    			right = (throttle - reverse) * sensitivity;
    		}
    	}
    	//Pirouette
    	else {
    		if (Math.abs(slew) > RobotMap.DRIVE_THRESHHOLD){
    			left  = RobotMap.DRIVE_SPIN_SENSITIVITY * slew;
    			right = RobotMap.DRIVE_SPIN_SENSITIVITY * slew * -1;
    				
    			}
    		}
    	
    	Robot.drivebase.drive(left, right);
    	
    	Robot.drivebase.getEncoderDistance();
    	
    	velocitySample2 = Robot.drivebase.getVelocityOfRobot();
    	
    		
    	}
    
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivebase.stop();
    	System.out.println("XboxMove end()");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivebase.stop();
    	System.out.println("XboxMove Interrupted");
    }
}
