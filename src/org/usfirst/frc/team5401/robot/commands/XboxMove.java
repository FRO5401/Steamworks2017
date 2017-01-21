package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class XboxMove extends Command {
	
	//Might make the following two constants in RobotMap
	double accelerationThreshold;
	double minimumVelocityForHighGear;//Determined Experimentally
	
	double accelerationSample1; //Oldest
    double accelerationSample2;
    double accelerationSample3;
    double accelerationSample4;
    double accelerationSample5; //Newest
    double avgAccelerationFromSamples;
    double instantaneousVelocitySample1;//oldest
    double instantaneousVelocitySample2;//newest
    double deltaTime;
	
    public XboxMove() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivebase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	accelerationThreshold = 0.01;
    	minimumVelocityForHighGear = 9;
    	
    	accelerationSample1 = 0;
    	accelerationSample2 = 0;
    	accelerationSample3 = 0;
    	accelerationSample4 = 0;
    	accelerationSample5 = 0;
    	instantaneousVelocitySample1 = 0;
    	instantaneousVelocitySample2 = 0;
    	deltaTime = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double 	slew        =	Robot.oi.readXboxLeftX_Driver();
    	double 	throttle 	=	Robot.oi.readRightTrigger_Driver();
    	double 	reverse 	=	Robot.oi.readLeftTrigger_Driver();
    	boolean precision	=	Robot.oi.getPrecision_Driver();
    	boolean brake		=	Robot.oi.getBrake_Driver();
    	boolean turn		= 	Robot.oi.getTurnButton_Driver();
    	
    	/*****Shifting Gear Code*********/
    	
    	//Backlogs the old final velocity (velocity 2) into the new initial velocity (velocity 1)
    	instantaneousVelocitySample1 = instantaneousVelocitySample2;
    	//Gets new final velocity
    	instantaneousVelocitySample2 = Robot.drivebase.getVelocityOfRobot();
    	
    	//Gets change in time
    	deltaTime = Robot.drivebase.getTimerValue();
    	
    	//Backlogs the acceleration
    	accelerationSample1 = accelerationSample2;
    	accelerationSample2 = accelerationSample3;
    	accelerationSample3 = accelerationSample4;
    	accelerationSample4 = accelerationSample5;
    	//Gets newest acceleration from the velocity sample above
    	accelerationSample5 = (instantaneousVelocitySample2-instantaneousVelocitySample1)/deltaTime;
    	
    	//calculates the average acceleration from previous samples to balance out spikes
    	avgAccelerationFromSamples = (accelerationSample1+accelerationSample2+accelerationSample3+accelerationSample4+accelerationSample5)/5;
    	
    	if(slew <= 0 + RobotMap.DRIVE_THRESHHOLD){
    		//Uses average acceleration for gear shifting up to higher speeds
    		//0 is just there to understand original logic
    		if(Math.abs(avgAccelerationFromSamples) <= 0 + accelerationThreshold){
    			Robot.drivebase.shiftGearLowToHigh();
    		}
    	
    		//Upshift using velocity
    		//if(instantaneousVelocitySample2 >= maximumVelocityForLowGear){
    		//	Robot.drivebase.shiftGearLowToHigh();
    		//}
    	
    		//Uses Current Velocity to Shift High to Low
    		if(instantaneousVelocitySample2 <= minimumVelocityForHighGear){
    			Robot.drivebase.shiftGearHighToLow();
    		}
    	
    		//Downshift Due to release in Thottle
    		//if(Math.abs(thottle) <= 0 + RobotMap.DRIVE_THRESHHOLD) {
    		//	Robot.drivebase.shiftGearHighToLow();
    		//}
    		//Gear Shift Done
    	}
    	
    	    	
    	//Driving Code
    	double right = 0, left = 0, sensitivity;
    	
    	if (precision) { //Sets drive precision based on RobotMap and Precision Mode
    		sensitivity	=	RobotMap.DRIVE_SENSITIVITY_PRECISE;
    	} else {
    		sensitivity	=	RobotMap.DRIVE_SENSITIVITY_DEFAULT;
    	}
    	
    	if (brake){
    		left  = 0;
    		right = 0;
    	} else if(!turn){
    		if (slew > RobotMap.DRIVE_THRESHHOLD){ //If Slew is positive (Thumbstick pushed right), go Right
    			left  = (throttle - reverse) * sensitivity;					//Send Left full power
    			right = (throttle - reverse) * sensitivity * (1 - slew);	//Send Right partial power, tempered by how hard the thumbstick is being pushed
    		} else if (slew < (-1 * RobotMap.DRIVE_THRESHHOLD)){ //If Slew is negative (Thumbstick pushed left), go Left
    			left  = (throttle - reverse) * sensitivity * (1 + slew); //Send Left partial power, tempered by how hard thumbstick is being pushed left
    			right = (throttle - reverse) * sensitivity; 			//Send right full power
    		} else { //Drive forward/back normally
    			left  = (throttle - reverse) * sensitivity;
    			right = (throttle - reverse) * sensitivity;
    		}
    	} else {
    		if (Math.abs(slew) > RobotMap.DRIVE_THRESHHOLD){
    			left  = RobotMap.DRIVE_SPIN_SENSITIVITY * slew;
    			right = RobotMap.DRIVE_SPIN_SENSITIVITY * slew * -1;
    		}
    	}
    	
    	Robot.drivebase.drive(left, right);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
