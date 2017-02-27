package org.usfirst.frc.team5401.robot;
/**
* Steamworks 2017 RobotMap code
* (c) 2017 Bensalem High School Fightin' Robotic Owls
*/
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	//Global Variables

	public static final double DRIVE_SENSITIVITY_DEFAULT = 1.0;
	public static final double DRIVE_SENSITIVITY_PRECISE = 0.2;
	public static final double DRIVE_SPIN_SENSITIVITY 	 = 0.5;
	public static final double DRIVE_THRESHHOLD 		 = 0.1;

	public static final double SHOOTER_SETPOINT 		 = 0.0;

	public static final double AUTO_TURN_THRESHOLD 		 = 0.1;
	public static final double AUTO_TURN_PRECISION		 = 0.5;
	public static final double AUTO_TURN_SPEED			 = 1.0;

		//Camera variables
	public static final int CAMERA_WIDTH 		= 320;
	public static final int CAMERA_HEIGHT 		= 240;
	public static final int CAMERA_FPS			= 15;
	
	//Joysticks
	public static final int XBOX_CONTROLLER_DRIVER 	 = 0;
	public static final int XBOX_CONTROLLER_OPERATOR = 1;
	
	//Xbox Controller Axes
	public static final int XBOX_AXIS_LEFT_X 		= 0;
	public static final int XBOX_AXIS_LEFT_Y 		= 1;
	public static final int XBOX_AXIS_LEFT_TRIGGER  = 2;
	public static final int XBOX_AXIS_RIGHT_TRIGGER = 3;
	public static final int XBOX_AXIS_RIGHT_X 		= 4;
	public static final int XBOX_AXIS_RIGHT_Y 		= 5;
	
	//USB Camera Locations
	public static final int CAMERA_FRONT 	= 0;
	public static final int CAMERA_BACK 	= 1;
	
	//PWM Motors
	public static final int DRIVE_LEFT_MOTOR_1	  = 0;
	public static final int DRIVE_RIGHT_MOTOR_1   = 1;
	public static final int SHOOTER_MOTORS   	  = 2;
	public static final int INFEEDER_MOTOR		  = 3;
	public static final int METERING_ROLLER       = 4;
	public static final int HOPPER_CONVEYOR       = 5;
	public static final int CLIMBER_MOTOR 		  = 6;
	public static final int DRIVE_LEFT_MOTOR_2	  = 7;
	public static final int DRIVE_RIGHT_MOTOR_2   = 8;
	
	//Sensor Channels
	public static final int PHOTOSWITCH_CHANNEL = 0;
	public static final int CLIMBER_LIMITSWITCH = 1;
	public static final int DRIVE_ENC_LEFT_A    = 2;
	public static final int DRIVE_ENC_LEFT_B    = 3;
	public static final int DRIVE_ENC_RIGHT_A   = 4;
	public static final int DRIVE_ENC_RIGHT_B   = 5;
	
	//Pneumatics
	public static final int PCM_ID 			= 0;
	public static final int DRIVE_SHIFT_IN   	  = 0;
	public static final int DRIVE_SHIFT_OUT  	  = 1;
	public static final int INFEEDER_OUT     	  = 2;
	public static final int INFEEDER_IN		 	  = 3;
	public static final int UNJAMMER_IN		      = 4;
	public static final int UNJAMMER_OUT          = 5;
	public static final int GEAR_MANIPULATOR_IN   = 6;
	public static final int GEAR_MANIPULATOR_OUT  = 7;
	
	//Analog Channels
	public static final int PRESSURE_SENSOR = 0;

							
}