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
	private final double MINIMUM_VELOCITY_FOR_HIGH_GEAR; //Experimentally Determined, REMEMBER inches per second
	private final double MAXIMUM_VELOCITY_FOR_LOW_GEAR;
	
/*	1/23/17 NOT NEEDED because using velocity for gear shift, no acceleration is used
	double accelerationSample1;//Oldest Sample
	double accelerationSample2;
	double accelerationSample3;
	double accelerationSample4;
	double accelerationSample5;//Newest Sample

	double avgAccelerationFromSamples;
*/	
	double velocitySample1;
	double velocitySample2;

	//1/23/17 1/23/17 NOT NEEDED because the time for acceleration equation is no longer necessary
	//double deltaTime;
	
	public XboxMove() {
		
//        accelerationThreshhold = 0.01;
		
		//Min and Max velocity have to be different to prevent constant shifting if at the shift speed if there is only one shift speed
		MINIMUM_VELOCITY_FOR_HIGH_GEAR 	= 25;//NEED TO CHANGE, REMEMBER inches per second
		MAXIMUM_VELOCITY_FOR_LOW_GEAR 	= 35;//NEED TO CHANGE
		
/*		1/23/17 NOT NEEDED
  		//Remember to initialize to zero
		accelerationSample1 = 0;
		accelerationSample2 = 0;
		accelerationSample3 = 0;
		accelerationSample4 = 0;
		accelerationSample5 = 0;
		
		avgAccelerationFromSamples = 0;
*/		
		velocitySample1 = 0;
		velocitySample2 = 0;
		
		//1/23/17 NOT NEEDED because no acceleration
		//deltaTime = 0;
		
		
		// Use requires() here to declare subsystem dependencies

        requires(Robot.drivebase);
        System.out.println("XBoxMove Constructed");
        //Starts timer, 1/23/17 NOT NEEDED because no timer necessary
        //Robot.drivebase.startTimer();
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivebase.shiftGearHighToLow();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.out.println("XBoxMove Excute");
    	double angle = Robot.drivebase.reportGyro();
    	SmartDashboard.putNumber("Gyro",  angle);
    	
    	double  slew      = Robot.oi.readXboxLeftX_Driver() * -1;

    	double 	throttle  = Robot.oi.readRightTrigger_Driver();
    	double 	reverse   = Robot.oi.readLeftTrigger_Driver();
    	boolean precision = Robot.oi.getPrecision_Driver();
    	boolean brake	  = Robot.oi.getBrake_Driver();
    	boolean turn	  = Robot.oi.getTurnButton_Driver();
    	boolean invert	  = Robot.oi.getDriveInvertButton_Driver();
    	
    	boolean gearShiftLow  = Robot.oi.getXboxBack_Driver();
    	boolean gearShiftHigh = Robot.oi.getXboxStart_Driver();
    	
    	//Manual Gear Shift
    	if (gearShiftHigh){
    		Robot.drivebase.shiftGearLowToHigh();;
    	} else if (gearShiftLow){
    		Robot.drivebase.shiftGearHighToLow();
    	}
    	
    	//Inverts Drive
    	if (invert){
    		slew *= -1;
    		double temp = throttle;
    		throttle = reverse;
    		reverse = temp;
    	}
	
 /*   		//Alternative Upshift using velocity
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
*/    		
    	//Gear Shift Done
    	

    	//Driving Code
    	double right = 0, left = 0, sensitivity;
    	/*
    	System.out.println("LEFT STICK X: " + slew + "\n"
    					 + "RIGHT TRIGGER: " + throttle + "\n"
    					 + "LEFT TRIGGER: " + reverse + "\n"
    					 + "BRAKE: " + brake);
    	*/
    	
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
    	
//    	System.out.println("LEFT: " + left);
//    	System.out.println("RIGHT: " + right);
    	
    	Robot.drivebase.drive(left, right);
    
    	
    	
    	
/*****Shifting Gear Code*********/
    	Robot.drivebase.getEncoderDistance();
/*    	//Backlogs the old final velocity (velocity 2) into the new initial velocity (velocity 1)
    	velocitySample1 = velocitySample2;
*/   	
    	//Gets new final velocity
    	velocitySample2 = Robot.drivebase.getVelocityOfRobot();
/*    	
    	//1/23/17 NOT NEEDED
    	//Gets change in time
    	//deltaTime = Robot.drivebase.getTimerValue();
    	
    	//1/23/17 NOT NEEDED
    	//Restarts timer for deltaTime in next iteration
    	//Robot.drivebase.stopTimer();
    	//Robot.drivebase.resetTimer();
    	//Robot.drivebase.startTimer();

/*    	1/23/17 NOT NEEDED
    	//Backlogs the acceleration
    	accelerationSample1 = accelerationSample2;
    	accelerationSample2 = accelerationSample3;
    	accelerationSample3 = accelerationSample4;
    	accelerationSample4 = accelerationSample5;

    	//Gets newest acceleration from the velocity sample above, pretty much final - inital
    	accelerationSample5 = (velocitySample2-velocitySample1)/deltaTime;

    	//calculates the average acceleration from previous samples to balance out spikes in acceleration
    	avgAccelerationFromSamples = (accelerationSample1+accelerationSample2+accelerationSample3+accelerationSample4+accelerationSample5)/5;
*/
    	
    	
    	//												vvvvv this is for no shifting at acceleration = 0 when robot is totally still, might be unnecessary
/*    	if(slew <= 0 + RobotMap.DRIVE_THRESHHOLD && velocitySample2 != 0){
    	//Uses average acceleration for gear shifting up to higher speeds
    	//0 is just there to understand original logic
		//Commented out because of problems of unwanted shifting up if running at a low constant velocity
    		//if(Math.abs(avgAccelerationFromSamples) <= 0 + accelerationThreshhold){
    			//Robot.drivebase.shiftGearLowToHigh();
    		//}
    		
    	//Alternative Upshift using velocity
    		if(Math.abs(velocitySample2) >= MAXIMUM_VELOCITY_FOR_LOW_GEAR){
    			Robot.drivebase.shiftGearLowToHigh();
    			Robot.drivebase.setDPPHighGear();
    		}


    	//Uses Current Velocity to Shift High to Low
    		if(Math.abs(velocitySample2) <= MINIMUM_VELOCITY_FOR_HIGH_GEAR){
    			Robot.drivebase.shiftGearHighToLow();
    			Robot.drivebase.setDPPLowGear();
    		}

    	//Alternative Downshift Due to release in Thottle
    		//if(Math.abs(thottle) <= 0 + RobotMap.DRIVE_THRESHHOLD) {
    		//	Robot.drivebase.shiftGearHighToLow();
    		//}
    		
    	}
    	//Gear Shift Done
*/    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() { //SHOULD never run
    	Robot.drivebase.stop();
    	System.out.println("XboxMove end()");
//    	this.cancel(); //not needed
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivebase.stop();
    	System.out.println("XboxMove Interrupted");
//    	this.cancel(); //not needed
    }
}
