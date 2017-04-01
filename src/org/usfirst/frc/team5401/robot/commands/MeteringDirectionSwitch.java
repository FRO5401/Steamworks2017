package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5401.robot.Robot;
/**
 *
 */
public class MeteringDirectionSwitch extends Command {
	int wheelDirection;
	
    public MeteringDirectionSwitch(int direction) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.loader);
        wheelDirection = direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(wheelDirection == 1)
    	{
    		Robot.loader.switchMeteringToForwards();
    	}
    	else if(wheelDirection == -1)
    	{
    		Robot.loader.switchMeteringToBackwards();
    	}
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
