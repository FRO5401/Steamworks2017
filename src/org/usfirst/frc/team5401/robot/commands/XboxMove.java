package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class XboxMove extends Command {
	//Might make the following constants in RobotMap
//	private final double accelerationThreshhold;
	private final double minimumVelocityForHighGear; //Experimentall Determined
	private final double maximumVelocityForLowGear;
	
	double accelerationSample1;//Oldest Sample
	double accelerationSample2;
	double accelerationSample3;
	double accelerationSample4;
	double accelerationSample5;//Newest Sample
	
	double avgAccelerationFromSamples;
	
	double velocitySample1;
	double velocitySample2;
	
	double deltaTime;
	
	public XboxMove() {
//        accelerationThreshhold = 0.01;
		
		//Min and Max velocity have to be different to prevent constant shifting if at the shift speed if there is only one shift speed
        minimumVelocityForHighGear 	= 8;//NEED TO CHANGE
        maximumVelocityForLowGear 	= 9;//NEED TO CHANGE
		// Use requires() here to declare subsystem dependencies
        requires(Robot.drivebase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
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
    	velocitySample1 = velocitySample2;
    	
    	//Gets new final velocity
    	velocitySample2 = Robot.drivebase.getVelocityOfRobot();
    	
    	//Gets change in time
    	deltaTime = Robot.drivebase.getTimerValue();
    	
    	//Restarts timer for deltaTime in next iteration
    	Robot.drivebase.stopTimer();
    	Robot.drivebase.resetTimer();
    	Robot.drivebase.startTimer();
    	
    	//Backlogs the acceleration
    	accelerationSample1 = accelerationSample2;
    	accelerationSample2 = accelerationSample3;
    	accelerationSample3 = accelerationSample4;
    	accelerationSample4 = accelerationSample5;

    	//Gets newest acceleration from the velocity sample above, pretty much final - inital
    	accelerationSample5 = (velocitySample2-velocitySample1)/deltaTime;

    	//calculates the average acceleration from previous samples to balance out spikes in acceleration
    	avgAccelerationFromSamples = (accelerationSample1+accelerationSample2+accelerationSample3+accelerationSample4+accelerationSample5)/5;
 
    	//												vvvvv this is for no shifting at acceleration = 0 when robot is totally still, might be unnecessary
    	if(slew <= 0 + RobotMap.DRIVE_THRESHHOLD && velocitySample2 != 0){
    		//Uses average acceleration for gear shifting up to higher speeds
    		//0 is just there to understand original logic
/*			Commented out because of problems of unwanted shifting up if running at a low constant velocity
    		if(Math.abs(avgAccelerationFromSamples) <= 0 + accelerationThreshhold){
    			Robot.drivebase.shiftGearLowToHigh();
    		}
*/    		
    		//Alternative Upshift using velocity
    		if(velocitySample2 >= maximumVelocityForLowGear){
    			Robot.drivebase.shiftGearLowToHigh();
    		}


    		//Uses Current Velocity to Shift High to Low
    		if(velocitySample2 <= minimumVelocityForHighGear){
    			Robot.drivebase.shiftGearHighToLow();
    		}

    		//Alternative Downshift Due to release in Thottle
    		//if(Math.abs(thottle) <= 0 + RobotMap.DRIVE_THRESHHOLD) {
    		//	Robot.drivebase.shiftGearHighToLow();
    		//}
    		
    	}
    	//Gear Shift Done
    	
    	//Driving Code
    	double right = 0, left = 0, sensitivity;
    	
    	System.out.println("LEFT STICK X: " + slew + "\n"
    					 + "RIGHT TRIGGER: " + throttle + "\n"
    					 + "LEFT TRIGGER: " + reverse + "\n"
    					 + "BRAKE: " + brake);
    	
    	
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
    	
    	System.out.println("LEFT: " + left);
    	System.out.println("RIGHT: " + right);
    	
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
