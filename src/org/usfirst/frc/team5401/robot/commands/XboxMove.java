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
    	double 	slew        =	Robot.oi.readXboxDriverLeftStickX();
    	double 	throttle 	=	Robot.oi.getRightTriggerDriver();
    	double 	reverse 	=	Robot.oi.getLeftTriggerDriver();
    	boolean precision	=	Robot.oi.getPrecisionDriver();
    	boolean brake		=	Robot.oi.getBrakeDriver();
    	boolean turn		= 	Robot.oi.getTurnButton();
    	
    	double right = 0, left = 0, sensitivity;
    	
    	if (precision) { //Sets drive precision based on RobotMap and Precision Mode
    		sensitivity	=	RobotMap.DRIVE_SENSITIVITY_PRECISE;
    	} else {
    		sensitivity	=	RobotMap.DRIVE_SENSITIVITY_DEFAULT;
    	}
    	// -----Begin block of spin in place code
    		if (brake){		//brake, Bracket level 1
    			left = 0;
    			right = 0;
    		}else if(!turn){ 	//drive normally, end bracket L1, new bracket L1
    			//else
    			if (slew > RobotMap.DRIVE_THRESHHOLD){	//If Slew is positive (Thumbstick pushed right), go Right, new bracket L2
    				left = (throttle - reverse) * sensitivity;			//Send Left full power
    				right = (throttle - reverse) * sensitivity * (1 - slew);	//Send Right partial power, tempered by how hard the thumbstick is being pushed
//    				heading = Robot.drivebase . ReportGyro();
//    				drift = 0;
    			}else if (slew < (-1 * RobotMap.DRIVE_THRESHHOLD)){	//If Slew is negative (Thumbstick pushed left), go Left, end bracket L2, new bracket L2 ***020516 KJM - added an else here.  May be unnecessary
    				left = (throttle - reverse) * sensitivity * (1 + slew);		//Send Left partial power, tempered by how hard thumbstick is being pushed left
    				right = (throttle - reverse) * sensitivity; 			//Send right full power
//    				heading = Robot.drivebase . ReportGyro();
//    				drift = 0;
    			}else {//if (Slew < Thresh && Slew > (-1 * Thresh))
//    				drift = Robot.drivebase . ReportGyro() - heading;
//    				if (drift < -.5) { //drifting left
//    					Left = ((Throttle - Reverse) * Sensitivity) + (kP_Drift * drift);
//    					Right = (Throttle - Reverse) * Sensitivity;
//    				}else if (drift > .5) { //drifting
//    					Left = (Throttle - Reverse) * Sensitivity;
//    					Right = ((Throttle - Reverse) * Sensitivity) + (kP_Drift * drift);
//    				}else {
    					left = (throttle - reverse) * sensitivity;
    					right = (throttle - reverse) * sensitivity;
//    				}
    			}//end bracket L2
    		}else {	//drive turning end bracket L1, new bracket L1
    			if (Math.abs(slew) > RobotMap.DRIVE_THRESHHOLD){
    				 left = RobotMap.DRIVE_SPIN_SENSITIVITY * slew;
    				 right = RobotMap.DRIVE_SPIN_SENSITIVITY * slew * -1;
    			}//end bracket L2
     
    		}//end bracket L1
    	Robot.drivebase.Drive(left, right);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
