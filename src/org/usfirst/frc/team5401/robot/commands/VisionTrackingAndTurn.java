package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.Robot;

/**
 *
 */
public class VisionTrackingAndTurn extends Command {

	private double desiredTurnAngleRelativeToInitAngle;
	private double currentAngleRelativeToInitAngle;
	private double initAngle;
	private boolean finished;
	private double angleReport;
	
	//Constants
	private final double angleThreshold;
	private final double autoTurnSpeed;
	private final double autoTurnPrecision;
	
    public VisionTrackingAndTurn() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.dummycameras);
        requires(Robot.drivebase);
        
        //Constants Declaration
        angleThreshold	= 1; 		//Turn angle in degrees
    	autoTurnSpeed	= 0.95;
    	autoTurnPrecision = .5;
    	
    	//0  initialization for safety
    	desiredTurnAngleRelativeToInitAngle = 0;
    	currentAngleRelativeToInitAngle = 0;
    	initAngle = 0;
    	angleReport = 0;
    	
    	//When the command first runs, we assume only one iteration is needed
    	finished = true;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	desiredTurnAngleRelativeToInitAngle = Robot.dummycameras.visionLoopSynchronized();
    	System.out.println(desiredTurnAngleRelativeToInitAngle);
    	
    	
    	initAngle = Robot.drivebase.reportGyro();
    	currentAngleRelativeToInitAngle = 0;//This needs to be done everytime the command runs, hence why it is here and in the constructor
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.out.println("InitAngle: " + initAngle);
    	//System.out.println("AutoTurning: " + desiredTurnAngle);
    	//System.out.println("Current Angle: " + currentAngle);
    	if (Math.abs(desiredTurnAngleRelativeToInitAngle) <= angleThreshold){
    		//DesiredTurnAngle too small
    		System.out.println("AutoTurnAngle should stop1");
    		finished = true;
    	} else {
    		if (desiredTurnAngleRelativeToInitAngle > 0 && (currentAngleRelativeToInitAngle < Math.abs(desiredTurnAngleRelativeToInitAngle) - angleThreshold)){
    			Robot.drivebase.drive(-autoTurnSpeed * autoTurnPrecision, autoTurnSpeed * autoTurnPrecision);
    			finished = false;
    		} else if (desiredTurnAngleRelativeToInitAngle < 0 && (currentAngleRelativeToInitAngle > angleThreshold - Math.abs(desiredTurnAngleRelativeToInitAngle))) {
    			Robot.drivebase.drive(autoTurnSpeed * autoTurnPrecision, -autoTurnSpeed * autoTurnPrecision);
    			finished = false;
    		} else { //error or exactly 0
    			//Finished
    			finished = true;
    			System.out.println("AutoTurnAngle should stop2");
    		}
    		currentAngleRelativeToInitAngle = Robot.drivebase.reportGyro() - initAngle;
    	}
    	angleReport = Robot.drivebase.reportGyro();
    	SmartDashboard.putNumber("Gyro Angle", currentAngleRelativeToInitAngle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
