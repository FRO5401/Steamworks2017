package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.RobotMap;
import org.usfirst.frc.team5401.robot.autonomous.AutoTargetAndShoot;

/**
 *
 */
public class TargetHigh extends Command {
	
	double angle;
	
    public TargetHigh() {
        // Use requires() here to declare subsystem dependencies
          requires(Robot.visionprocessing);
          angle = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
   // 	SmartDashboard.putBoolean("Shooter OnOff", true);
    	SmartDashboard.putNumber("Target Angle", angle);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//get data from shooter pi from subsystem
    	//determine movement values
    	//send an auto drive command instructions
    	SmartDashboard.putBoolean("AutoTargeting", true);
//XXX    	angle = Robot.visionprocessing.findTargetAngle();
    	angle = 90;
    	SmartDashboard.putNumber("Target Angle", angle);
    	
//    	System.out.println("Starting AutoTargetAndShoot CmdGroup from TargetHigh Cmd");
//    	AutoTargetAndShoot shoot = new AutoTargetAndShoot(angle, false);    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
