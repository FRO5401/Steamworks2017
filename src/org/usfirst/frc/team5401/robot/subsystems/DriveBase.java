package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

import org.usfirst.frc.team5401.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5401.commands.XboxMove;

/**
 *
 */
public class DriveBase extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public DriveBase(){
		LeftDrive = new Victor(RobotMap.LEFT_MOTOR);
		RightDrive = new Victor(RobotMap.RIGHT_MOTOR);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new XboxMove());
    }
    
    public void Drive(double LeftDriveDesired, double RightDriveDesired)
    {

    LeftDrive.set(-1 * LeftDriveDesired); //passes desired state to speed controllers
    
    RightDrive.set(RightDriveDesired);
    }

  public void Stop()
  {

    LeftDrive.set(0);
    RightDrive.set(0);

  //  TimeCount.stop();

  }
}

