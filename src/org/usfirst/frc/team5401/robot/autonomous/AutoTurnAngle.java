package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.commands.XboxMove;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * 
 */
public class AutoTurnAngle extends Command {
	
	private double desiredTurnAngle;
	private double currentAngle;
	private double initAngle;
	private boolean finished;
	
	//Constants
	private final double angleThreshold;
	private final double autoDistThresh;
	private final double autoTurnSpeed;
	private final double autoTurnPrecision;
	private final boolean modeAuto;
	private final boolean modeAutoTarget;

	//XXX Send AutoTurnAngle and angle of 0 to auto target!
    public AutoTurnAngle(double angle, boolean inAuto, boolean autoTarget) {
    	//Initialize Constants
    	angleThreshold	= 2; 		//Turn angle in degrees
    	autoDistThresh	= 2; 		//Distance threshold in inches
    	autoTurnSpeed	= 0.95;
    	autoTurnPrecision = .5;
    	
    	modeAuto = inAuto;
    	modeAutoTarget = autoTarget;
    	
    	if (modeAutoTarget){
    		desiredTurnAngle = SmartDashboard.getNumber("Target Angle", 0); //0 is the default value
    	} else {
    		desiredTurnAngle = angle;
    	}
    	
    	requires(Robot.drivebase);
    	//XXX Switched to using ReportGyro rather than the raw value; if there's problems, have ReportGyro return MainGyro.GetAngle()
    	currentAngle = 0;
    	initAngle = 0;
    	finished = true;
    	System.out.println("AutoTurnAngle Constructed");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initAngle = Robot.drivebase.reportGyro();
//    	Robot.drivebase.recalibrateGyro(); 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Math.abs(desiredTurnAngle) <= angleThreshold){
    		//DesiredTurnAngle too small
    		System.out.println("AutoTurnAngle should stop");
    		finished = true;
    	} else {
    		if (desiredTurnAngle > 0 && (currentAngle < Math.abs(desiredTurnAngle) - angleThreshold)){
    			Robot.drivebase.drive(-autoTurnSpeed * autoTurnPrecision, autoTurnSpeed * autoTurnPrecision);
    			finished = false;
    		} else if (desiredTurnAngle < 0 && (currentAngle > angleThreshold - Math.abs(desiredTurnAngle))) {
    			Robot.drivebase.drive(autoTurnSpeed * autoTurnPrecision, -autoTurnSpeed * autoTurnPrecision);
    			finished = false;
    		} else { //error or exactly 0
    			//Finished
    			finished = true;
    			System.out.println("AutoTurnAngle should stop");
    		}
    	currentAngle = Robot.drivebase.reportGyro() - initAngle;
    	}
    	double angle = Robot.drivebase.reportGyro();
    	SmartDashboard.putNumber("Gyro Angle", angle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if (modeAuto) {	 //if in auto, stop motors
    		Robot.drivebase.stop();
    	} else { //if in teleop, start xboxmove
    		Scheduler.getInstance().add(new XboxMove());
    	}
    	System.out.println("AutoTurnAngle end()");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivebase.stop();
    	System.out.println("AutoTurnAngle Interrupted");
    }
}
