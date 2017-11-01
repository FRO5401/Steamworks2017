package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.Robot;

/**
 *
 */
public class AutoPIDDrive extends Command {
	
	
    public AutoPIDDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.piddrivebase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.piddrivebase.encoderReset();
    	
    	double distance = SmartDashboard.getNumber("DriveStraight Distance", 0);
    	Robot.piddrivebase.setSetpoint(distance);
    	//Robot.piddrivebase.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (Math.abs(Robot.piddrivebase.getSetpoint() - Robot.piddrivebase.getPosition()) < .5)
        	return true;
        else
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
