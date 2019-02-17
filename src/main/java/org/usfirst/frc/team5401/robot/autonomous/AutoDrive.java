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
public class AutoDrive extends Command {

	private double desiredDistance;
	private double autoDriveSpeed;
	private boolean doneTraveling;
	private double distanceTraveled;
	private double heading;
	private double drift;
	private double kP_Drift;
	private double velocitySample2;
	
	private final double autoDistThresh;
	private final double MAXIMUM_VELOCITY_FOR_LOW_GEAR;
	private final double MINIMUM_VELOCITY_FOR_HIGH_GEAR;
	
    public AutoDrive(double DistanceInput, double SpeedInput) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivebase);

    	desiredDistance = DistanceInput;
    	autoDriveSpeed = SpeedInput;
    	doneTraveling = true;
    	distanceTraveled = 0;
    	heading = Robot.drivebase.reportGyro();
    	drift = 0;
    	kP_Drift = .1;
    	velocitySample2 = 0;
    	
    	//Final Variables
    	autoDistThresh = 2;
    	MAXIMUM_VELOCITY_FOR_LOW_GEAR = 50;
    	MINIMUM_VELOCITY_FOR_HIGH_GEAR = 40;
    	
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
    	if (Math.abs(desiredDistance) <= autoDistThresh){
    		//DesiredDistance too small!
    		doneTraveling = true;
    	} else {
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
