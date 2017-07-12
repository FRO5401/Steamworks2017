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
    		double beforeStopSinglePulseTime = Robot.meteringwheel.getTimeInSeconds();
    		System.out.println(beforeStopSinglePulseTime);
    		//WHILE LOOP DOES NOT WORK
    		while(Robot.meteringwheel.getTimeInSeconds() < (beforeStopSinglePulseTime + 0.5))
    		{
    			//Change Nothing
    			System.out.println("Waiting");
    			System.out.println(Robot.meteringwheel.getTimeInSeconds());
    		}
    		System.out.println("Wait Done");
    		System.out.println(Robot.meteringwheel.getTimeInSeconds());
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
