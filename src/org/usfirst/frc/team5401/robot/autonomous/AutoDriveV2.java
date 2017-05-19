package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.Robot;


/**
 *
 */
public class AutoDriveV2 extends Command {

	private double desiredDistance;
	private double maxAutoDriveSpeed;
	private double autoDistanceThreshhold;
	private boolean doneTraveling;
	private double gyroDrift;
	private double initialAngle;
	private double distanceTraveled;
	
    public AutoDriveV2(double distance, double motorSpeed) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivebase);
        desiredDistance = distance;
        maxAutoDriveSpeed = motorSpeed;
        autoDistanceThreshhold = 1;
        doneTraveling = false;
        gyroDrift = 0;
        initialAngle = 0;
        distanceTraveled = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initialAngle = readGyro;
    	distanceTraveled = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Math.abs(desiredDistance) <= autoDistanceThreshhold){
    		System.out.println("Distance to travel is too small");
    		doneTraveling = true;
    	} else {
    		gyroDrift = Robot.drivebase.reportGyro() - intialAngle;
    		SmartDashboard.putNumber("Gyro Drift", gyroDrift);
    			if (desiredDistance > 0 && (distanceTraveled < Math.abs(desiredDistance) - autoDistThresh)){ //DesiredDistance is positive, go forward
    				//Drive Forward
    				if (gyroDrift > .5){ //Currently assumes we always drift right while going forwards
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
