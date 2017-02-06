package org.usfirst.frc.team5401.robot.commands;

import org.usfirst.frc.team5401.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PopGear extends Command {

	//private int inout;
	
    public PopGear() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.gearmechanism); 
        //inout = direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gearmechanism.gearInOut(1);//closes around gear
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
    //Called when a toggleWhenPressed buttons is activated again
    protected void interrupted() {
    	Robot.gearmechanism.gearInOut(-1);//pushes gear out, this is called first when robotInit activates
    }
}
