package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.subsystems.DriveBase;
import org.usfirst.frc.team5401.robot.RobotMap;
import org.usfirst.frc.team5401.robot.commands.XboxMove;


/**
 * This command is also used as a "BaselineOnly" command 
 */
public class AutoDrive2 extends Command {

	private double desiredDistance;
	private double maxAutoDriveSpeed;
	private double autoDriveSpeed;
	private double autoDriveStallSpeed;
	private boolean doneTraveling;
	private double distanceTraveled;
	private double heading;
	private double drift;
	private double kP_Drift;
	private double autoDistThresh;

	
    public AutoDrive2(double DistanceInput, double SpeedInput) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivebase);

    	desiredDistance = DistanceInput;
    	maxAutoDriveSpeed = SpeedInput;
    	autoDriveSpeed = 0;
    	autoDriveStallSpeed = 0;
    	doneTraveling = true;
    	distanceTraveled = 0;
    	heading = Robot.drivebase.reportGyro();
    	drift = 0;
    	kP_Drift = .1;
    	autoDistThresh = 2;
    	
//    	if (SpeedInput > .5){
//    		Robot.drivebase.shiftGearLowToHigh();
//    	} else {
//    		Robot.drivebase.shiftGearHighToLow();
//    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivebase.encoderReset();
//    	Robot.drivebase.resetGyro(); 
    	heading = Robot.drivebase.reportGyro();
    	drift = 0;
    	doneTraveling = true;
    	distanceTraveled = 0;
    	
    	System.out.println("AutoDriveInitializing");
    	System.out.println("Angle when starting DriveShift:" + Robot.drivebase.reportGyro());
    	SmartDashboard.putNumber("heading", heading);
    	
    	Robot.drivebase.shiftGearHighToLow();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Donetraveling:" + doneTraveling);
    	System.out.println("Donetraveling:" + doneTraveling);
    	
    	if (Math.abs(desiredDistance) <= autoDistThresh){
    		//DesiredDistance too small!
    		doneTraveling = true;
    	} else {
    		
    		//New Stuff
    		if(Math.abs(distanceTraveled) <= Math.abs(desiredDistance)/2)//For first half of travel
    		{												
    			autoDriveSpeed = maxAutoDriveSpeed * 2 * Math.abs(distanceTraveled/desiredDistance);// Thus at 1/4 distance, 1/2 speed, and 1/2 distance, max speed
    		}
    		else if(Math.abs(distanceTraveled) > Math.abs(desiredDistance)/2)//for second half of travel
    		{
    			autoDriveSpeed = maxAutoDriveSpeed * 2 * (1 - Math.abs(distanceTraveled/desiredDistance));//Greater the distance, lower the speed
    		}
    		
			if(autoDriveSpeed < autoDriveStallSpeed)
			{
				autoDriveSpeed = autoDriveStallSpeed;// So that Speed doesn't drop under Stall Speed and stall motor
			}
    		
			
			
			
    		drift = Robot.drivebase.reportGyro() - heading;
    		SmartDashboard.putNumber("Drift", drift);
    			if (desiredDistance > 0 && (distanceTraveled < Math.abs(desiredDistance) - autoDistThresh)){ //DesiredDistance is positive, go forward
    				//Drive Forward
    				if (drift > .5){ //Currently assumes we always drift right while going forwards
    					Robot.drivebase.drive(autoDriveSpeed + (kP_Drift * drift), autoDriveSpeed); //Adjust right motor when driving forward
//    				} else if (drift < -.5){
//    					Robot.drivebase.drive(autoDriveSpeed, autoDriveSpeed + (kP_Drift * drift));
    				} else {
    					Robot.drivebase.drive(autoDriveSpeed, autoDriveSpeed);
    				}
    				doneTraveling = false;
    			} else if (desiredDistance < 0 && (distanceTraveled > autoDistThresh - Math.abs(desiredDistance))){ //DesiredDistance is negative, go backward
    				//Drive Backward
    				if (drift > .5){ //Currently assumes we always drift left (while looking backward as the front) while going backwards
    					Robot.drivebase.drive(-autoDriveSpeed, -(autoDriveSpeed + (kP_Drift * drift)));//Adjusts left motor when driving backwards
    				} else if (drift < -.5){
    					Robot.drivebase.drive(-autoDriveSpeed + (kP_Drift * drift), -autoDriveSpeed);
    				} else {
    					Robot.drivebase.drive(-autoDriveSpeed, -autoDriveSpeed);
    				}
    				doneTraveling = false;
    			} else { //error, exactly 0, or done
    				//Finished
    				doneTraveling = true;
    			}
    		distanceTraveled = (Robot.drivebase.getEncoderDistance());
    	}
    	
    	/*****Shifting Gear Code*********/
/*    	Robot.drivebase.getEncoderDistance();
   	
    	//Gets new final velocity
    	velocitySample2 = Robot.drivebase.getVelocityOfRobot();
*/    	
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
    	
    	//Uses average acceleration for gear shifting up to higher speeds
    	//0 is just there to understand original logic
		//Commented out because of problems of unwanted shifting up if running at a low constant velocity
    		//if(Math.abs(avgAccelerationFromSamples) <= 0 + accelerationThreshhold){
    			//Robot.drivebase.shiftGearLowToHigh();
    		//}
/*    		
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
    		
    	
    	//Gear Shift Done
*/    	
    	Robot.drivebase.getVelocityOfRobot();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return doneTraveling;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivebase.stop();
    	System.out.println("Angle when EXITING DriveShift:" + Robot.drivebase.reportGyro());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivebase.stop();
    }
}
