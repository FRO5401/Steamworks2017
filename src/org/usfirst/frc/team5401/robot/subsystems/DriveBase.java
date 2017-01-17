package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

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
	
	
	public DriveBase(){
		leftDrive  = new Victor(RobotMap.LEFT_MOTOR);
		rightDrive = new Victor(RobotMap.RIGHT_MOTOR);
		leftGearShift = new DoubleSolenoid(LEFT_GEAR_SHIFT_FORWARD_CHANNEL, LEFT_GEAR_SHIFT_REVERSE_CHANNEL);
		rightGearShift  = new DoubleSolenoid(RIGHT_GEAR_SHIFT_FORWARD_CHANNEL, RIGHT_GEAR_SHIFT_REVERSE_CHANNEL);
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
    
    public void 
}