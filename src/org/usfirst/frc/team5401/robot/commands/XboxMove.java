package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class XboxMove extends Command {

	public XboxMove() {
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
