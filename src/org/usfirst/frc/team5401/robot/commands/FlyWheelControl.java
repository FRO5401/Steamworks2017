package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FlyWheelControl extends Command {
	
    public FlyWheelControl() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.shooter);
    	requires(Robot.compressorsubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.switchState();
    	
    	if (Robot.shooter.isEnabled()){
    		Robot.compressorsubsystem.stopCompressor();
    	} else {
    		Robot.compressorsubsystem.startCompressor();
    	}
    	
    	SmartDashboard.putBoolean("Shooter OnOff", Robot.shooter.isEnabled());    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {  
    	//end() does nothing because this command toggles the state
    }

    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.stop();
    	Robot.compressorsubsystem.stopCompressor();
    	SmartDashboard.putBoolean("Shooter OnOff", Robot.shooter.isEnabled());
    	System.out.println("FlyWheelControl Interrupted");
    }
}
