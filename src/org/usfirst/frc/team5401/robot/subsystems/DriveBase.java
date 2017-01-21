package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Encoder;

import org.usfirst.frc.team5401.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5401.robot.commands.XboxMove;

/**
 *
 */
public class DriveBase extends Subsystem {
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private VictorSP leftDrive;
	private VictorSP rightDrive;
	private DoubleSolenoid gearShifter;
	private Timer timer;
	private Encoder leftEncoder;
	private Encoder rightEnocoder;
	
	public DriveBase(){
		leftDrive  = new VictorSP(RobotMap.DRIVE_LEFT_MOTOR);
		rightDrive = new VictorSP(RobotMap.DRIVE_RIGHT_MOTOR);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new XboxMove());
    }
    
    public void drive(double leftDriveDesired, double rightDriveDesired){
    	leftDrive .set(-1 * leftDriveDesired); //passes desired state to speed controllers
    	rightDrive.set(rightDriveDesired);
    	
    	System.out.println("LEFT DESIRED: " + leftDriveDesired);
    	System.out.println("RIGHT DESIRED: " + rightDriveDesired);
    }

    public void stop(){
    	leftDrive .set(0);
    	rightDrive.set(0);
    }
}