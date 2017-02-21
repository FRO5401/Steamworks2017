package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

import org.usfirst.frc.team5401.robot.commands.XboxMove;

/**
 * Requires drivebase, shooter, compressor, and loader in order to cause
 * other commands that have control of it to run their interrupts which
 * should be deactivating the systems.
 * 
 * Should Trigger:
 * 	AutoTurnAngle interrupted()
 * 	GetShooterUpToSpeed interrupted()
 * 	Shoot interrupted()
 * 
 * Note: It is intended that the compressor turns on and the shooter off.
 */
public class CeaseFire extends Command {

    public CeaseFire() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.drivebase);
    	requires(Robot.shooter);
    	requires(Robot.compressorsubsystem);
    	requires(Robot.loader);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("CeaseFire start!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.stop();
    	Robot.compressorsubsystem.startCompressor();
    	Robot.loader.stopConveyorsAndMeteringMotor();
    	Scheduler.getInstance().add(new XboxMove());
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
