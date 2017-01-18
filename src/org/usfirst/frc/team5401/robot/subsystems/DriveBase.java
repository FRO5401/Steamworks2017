package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Victor;

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
	
	public DriveBase(){
		leftDrive  = new Victor(RobotMap.DRIVE_LEFT_MOTOR);
		rightDrive = new Victor(RobotMap.DRIVE_RIGHT_MOTOR);
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
}