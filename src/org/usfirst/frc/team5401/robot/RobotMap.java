package org.usfirst.frc.team5401.robot;

import edu.wpi.first.wpilibj.buttons.Button;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	//public static final int conveyors     = 0;
	//public static final int meteringMotor = 1;
	
	//Global Variables
	public static final double DRIVE_SENSITIVITY_DEFAULT = 1.0;
	public static final double DRIVE_SENSITIVITY_PRECISE = 0.2;
	public static final double DRIVE_SPIN_SENSITIVITY 	 = 0.5;
	public static final double DRIVE_THRESHHOLD 		 = 0.1;

	public static final double SHOOTER_SETPOINT      = 0.0;
	
	//Controllers
	public static final int XBOX_CONTROLLER_DRIVER   = 0;
	public static final int XBOX_CONTROLLER_OPERATOR = 1;
	
	//Buttons
	public static final int XBOX_A_DRIVER = 1;  
	public static final int XBOX_B_DRIVER = 2;	
	public static final int XBOX_X_DRIVER = 3;
	public static final int XBOX_Y_DRIVER = 4;
	public static final int XBOX_LEFT_BUMPER_DRIVER = 5;
	public static final int XBOX_RIGHT_BUMPER_DRIVER = 6;
	public static final int XBOX_BACK_DRIVER = 7;
	public static final int XBOX_START_DRIVER = 8;
	public static final int XBOX_L3_DRIVER = 9;
	public static final int XBOX_R3_DRIVER = 10;
	
	public static final int XBOX_A_OPERATOR = 1;
	public static final int XBOX_B_OPERATOR = 2;
	public static final int XBOX_X_OPERATOR = 3;
	public static final int XBOX_Y_OPERATOR = 4;
	public static final int XBOX_LEFT_BUMPER_OPERATOR = 5;
	public static final int XBOX_RIGHT_BUMPER_OPERATOR = 6;
	public static final int XBOX_BACK_OPERATOR = 7;
	public static final int XBOX_START_OPERATOR = 8;
	public static final int XBOX_L3_OPERATOR = 9;
	public static final int XBOX_R3_OPERATOR = 10;
	
	//Controller Axis
	public static final int LEFT_STICK_AXIS_X    = 0;
	public static final int LEFT_STICK_AXIS_Y    = 1;
	public static final int LEFT_TRIGGER_AXIS    = 2;
	public static final int RIGHT_TRIGGER_AXIS   = 3;
	public static final int RIGHT_STICK_AXIS_X	 = 4;
	public static final int RIGHT_STICK_AXIS_Y   = 5;
	
    //PWM Motors
	public static final int DRIVE_LEFT_MOTOR_1    = 0;
	public static final int DRIVE_RIGHT_MOTOR_1   = 1;
	public static final int DRIVE_LEFT_MOTOR_2    = 7;
	public static final int DRIVE_RIGHT_MOTOR_2   = 8;
	public static final int SHOOTER_MOTORS        = 2;
	public static final int INFEEDER_MOTOR        = 3;
	   //This would be shooter motors
	public static final int METERING_BAR          = 4;
	public static final int HOPPER_BELTS          = 5;
	public static final int CLIMBER_MOTOR 		  = 6;
	public static final int LEFT_MOTOR_2          = 7;
	public static final int RIGHT_MOTOR_2         = 8;
	

	
	//Sensors
	public static final int DRIVE_ENC_LEFT_A   = 2;
	public static final int DRIVE_ENC_RIGHT_A  = 4;
	public static final int DRIVE_ENC_LEFT_B   = 3;
	public static final int DRIVE_ENC_RIGHT_B  = 5;
	
	//Pneumatics
	public static final int PCM_ID          = 0;
	public static final int SHIFTER_IN      = 0;
	public static final int SHIFTER_OUT     = 1;
	public static final int INFEEDER_IN     = 3;
	public static final int INFEEDER_OUT    = 2;
	public static final int UNJAM_IN        = 4;
	public static final int UNJAM_OUT       = 5;

	public static final int GEAR_DOOR_OPEN  = 6;
	public static final int GEAR_DOOR_CLOSE = 7;
	
	//Analog
	public static final int PRESSURE_SENSOR  = 0;
	
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
