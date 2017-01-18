package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team5401.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5401.robot.commands.XboxMove;

/**
 *
 */
public class DriveBase extends Subsystem {
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Victor leftDrive;
	private Victor rightDrive;
	private DoubleSolenoid leftGearShift;
	private DoubleSolenoid rightGearShift;
	private Timer timer;
	
	
	public DriveBase(){
		leftDrive  = new Victor(RobotMap.LEFT_MOTOR);
		rightDrive = new Victor(RobotMap.RIGHT_MOTOR);
		leftGearShift = new DoubleSolenoid(RobotMap.LEFT_GEAR_SHIFT_FORWARD_CHANNEL, RobotMap.LEFT_GEAR_SHIFT_REVERSE_CHANNEL);
		rightGearShift  = new DoubleSolenoid(RobotMap.RIGHT_GEAR_SHIFT_FORWARD_CHANNEL, RobotMap.RIGHT_GEAR_SHIFT_REVERSE_CHANNEL);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new XboxMove());
    }
    
    public void drive(double leftDriveDesired, double rightDriveDesired){
    	leftDrive .set(leftDriveDesired); //passes desired state to speed controllers
    	rightDrive.set(rightDriveDesired);
    }

    public void stop(){
    	leftDrive .set(0);
    	rightDrive.set(0);
    }
    
    public void shiftGearLowToHigh(){//Meaning Low speed to high speed
    	//Assumes Pneumatic forward/out shifts low to high
    	leftGearShift.set(DoubleSolenoid.Value.kForward);
    	rightGearShift.set(DoubleSolenoid.Value.kForward);
    }
    public void shiftGearHighToLow(){
    	//Assumes Pneumatic reverse/in shifts high to low
    	leftGearShift.set(DoubleSolenoid.Value.kReverse);
    	rightGearShift.set(DoubleSolenoid.Value.kReverse);
    }
    
    public double getTimerValue(){
    	double timerValue = timer.get();
    	//Also displays for testing
    	SmartDashboard.putNumber("Time", timerValue);
    	return timerValue;
    }
    
    public void resetTimer(){
    	timer.reset();
    }
    
    public void stopTimer(){
    	timer.stop();
    }
    
    public void startTimer(){
    	timer.start();
    }
}