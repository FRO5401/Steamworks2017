package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class XboxMove extends Command {

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
    	accelerationSample1 = 0;
    	accelerationSample2 = 0;
    	accelerationSample3 = 0;
    	accelerationSample4 = 0;
    	accelerationSample5 = 0;
    	velocitySample = 0;
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
    	
    	//Shifting Gear Code
    	//avgAccelerationFromSamples = (accelerationSample1+accelerationSample2+accelerationSample3+accelerationSample4+accelerationSample5)/5
    	/*velocity = getEncoder
    	 * time = getTimer()
    	 * acceleration = velocity/time;
    	 */
    	// IS ONE LINE OF CODE ENOUGH TIME TO DIFFER VELOCITY?
    	// This is assuming yes.
    	//velocitySample = (getLeftEncoderGetRate+getRightEncoderGetRate)/2;
    	//veolcitySample2
    	deltaTime = Robot.drivebase.getTimerValue();
    	accelerationSample1 = (instantaneousVelocitySample2-instantaneousVelocitySample1)/deltaTime;
    	
    	    	
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
