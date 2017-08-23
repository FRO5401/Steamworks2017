package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.Robot;
/**
 *
 */
public class LookAndTurn extends Command {
	private double turnAngle;
	private double angleThresholdForVision;
	private boolean turnIsFinished;
	private double autoTurnSpeed;
	private double autoTurnPrecision;
	private double currentAngle;
	private double initialAngle;
	
    public LookAndTurn() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivebase);
    	requires(Robot.dummycameras);
    	
    	turnAngle = 0;
    	angleThresholdForVision = 2;
    	turnIsFinished = false;
    	autoTurnSpeed = 0.95;
    	autoTurnPrecision = 0.5;
    	currentAngle = 0;
    	initialAngle = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	turnAngle = Robot.dummycameras.visionLoopSynchronized();
    	System.out.println(turnAngle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Math.abs(turnAngle) <= angleThresholdForVision){
    		//DesiredTurnAngle too small
    		System.out.println("AutoTurnAngle should stop1");
    		turnIsFinished = true;
    	} else {
    		if (turnAngle > 0 && (currentAngle < Math.abs(turnAngle) - angleThresholdForVision)){
    			Robot.drivebase.drive(-autoTurnSpeed * autoTurnPrecision, autoTurnSpeed * autoTurnPrecision);
    			turnIsFinished = false;
    		} else if (turnAngle < 0 && (currentAngle > angleThresholdForVision - Math.abs(turnAngle))) {
    			Robot.drivebase.drive(autoTurnSpeed * autoTurnPrecision, -autoTurnSpeed * autoTurnPrecision);
    			turnIsFinished = false;
    		} else { //error or exactly 0
    			//Finished
    			turnIsFinished = true;
    			System.out.println("AutoTurnAngle should stop2");
    		}
    	currentAngle = Robot.drivebase.reportGyro() - initialAngle;
    	}
    	
    	SmartDashboard.putNumber("How Much the Robot Has Turned During LookAndTurn", currentAngle);//This reports how much the robot has turned during this segment of LookAndTurn
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return turnIsFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
