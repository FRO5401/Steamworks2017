package org.usfirst.frc.team5401.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5401.robot.Robot;


/**
 * Pulses the Metering Wheel
 * Problems: the pulsing isn't great because the wheel doesn't completely stop before restarting, probably due to the fact that the motors are on COAST
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
    	//Reads the stop button
    	boolean stopButton = Robot.oi.getXboxRightJoystickButton();
    	
    	if(stopButton == true){//If stopButton is pressed, true, then stop pulsing
    		finishedVariable = true;
    	}
    	
    	else if(stopButton == false){//If not pressed, false, then start pulsing
    		finishedVariable = false;
    		
    		Robot.meteringwheel.runMeteringMotor();//Start Motor for pulsing
    		
    		//Get the time for the loop to keep the motor running
    		double beforeStopSinglePulseTime = Robot.meteringwheel.getTimeInSeconds();
    		while(Robot.meteringwheel.getTimeInSeconds() < (beforeStopSinglePulseTime + 0.5))
    		{
    		}
    		
    		//Stops the motor for the "pulse effect
    		Robot.meteringwheel.stopMeteringMotor();
    		double beforeStopSinglePauseTime = Robot.meteringwheel.getTimeInSeconds();
    		while(Robot.meteringwheel.getTimeInSeconds() < (beforeStopSinglePauseTime + 0.5))
    		{
    		}
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
