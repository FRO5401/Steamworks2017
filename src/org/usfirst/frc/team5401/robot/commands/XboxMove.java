package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *The code used for driving the Robot. 
 *Robot runs on tank drive with manual shifting gearboxes for drive. 
 *Auto shift is included but not used
 */
public class XboxMove extends Command {
	//TODO Might make the following constants in RobotMap
	private final double MINIMUM_VELOCITY_FOR_HIGH_GEAR; //Experimentally Determined, REMEMBER inches per second
	private final double MAXIMUM_VELOCITY_FOR_LOW_GEAR;
	
	double velocitySample;
	
	public XboxMove() {
		//Min and Max velocity have to be different to prevent constant shifting if at the shift speed if there is only one shift speed
		MINIMUM_VELOCITY_FOR_HIGH_GEAR 	= 35;// REMEMBER inches per second
		MAXIMUM_VELOCITY_FOR_LOW_GEAR 	= 45;

		velocitySample = 0;
		
		// Use requires() here to declare subsystem dependencies

        requires(Robot.drivebase);
        System.out.println("XBoxMove Constructed");
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivebase.shiftGearHighToLow();
    	//Robot.drivebase.shiftGearLowToHigh();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
    	    	
    	//Inverts Drive
    	if (invert){
    		slew *= -1;
    		double temp = throttle;
    		throttle = reverse;
    		reverse = temp;
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
    		if (invert){
    			slew *= -1;
    		}
    		if (Math.abs(slew) > RobotMap.DRIVE_THRESHHOLD){
    			left  = RobotMap.DRIVE_SPIN_SENSITIVITY * slew;
    			right = RobotMap.DRIVE_SPIN_SENSITIVITY * slew * -1;
    		}
    	}
    	
    	Robot.drivebase.drive(left, right);
    
    	
/*****Manual Gear Shift********/
    	if (gearShiftHigh){
    		Robot.drivebase.shiftGearLowToHigh();;
    	} else if (gearShiftLow){
    		Robot.drivebase.shiftGearHighToLow();
    	}
    	
/*****Auto Shifting Gear Code*********/
    	Robot.drivebase.getEncoderDistance();
   	
    	//Gets new final velocity
    	velocitySample = Robot.drivebase.getVelocityOfRobot();

    	
/*    	
    	//Upshift using velocity
    		if(Math.abs(velocitySample) >= MAXIMUM_VELOCITY_FOR_LOW_GEAR){
    			Robot.drivebase.shiftGearLowToHigh();
    			Robot.drivebase.setDPPHighGear();
    		}


    	//Uses Current Velocity to Shift High to Low
    		if(Math.abs(velocitySample) <= MINIMUM_VELOCITY_FOR_HIGH_GEAR){
    			Robot.drivebase.shiftGearHighToLow();
    			Robot.drivebase.setDPPLowGear();
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivebase.stop();
    	System.out.println("XboxMove Interrupted");
    }
}
