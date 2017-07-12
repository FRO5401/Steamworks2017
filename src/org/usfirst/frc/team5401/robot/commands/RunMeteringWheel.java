package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5401.robot.Robot;


/**
 *
 */
public class RunMeteringWheel extends Command {

	boolean finishedVariable;
	
    public RunMeteringWheel() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.meteringwheel);
        
        finishedVariable = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	boolean stopButton = Robot.oi.getXboxRightJoystickButton();
    	if(stopButton == true){
    		finishedVariable = true;
    	}else if(stopButton == false){
    		finishedVariable = false;
    		Robot.meteringwheel.runMeteringMotor();
    		Robot.meteringwheel.timerStop();
    		Robot.meteringwheel.timerReset();
    		Robot.meteringwheel.timerStart();
    		
    		while(Robot.meteringwheel.getTimeInSeconds() <.5)
    		{
    			//Change Nothing
    		}
    		Robot.meteringwheel.stopMeteringMotor();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finishedVariable;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
