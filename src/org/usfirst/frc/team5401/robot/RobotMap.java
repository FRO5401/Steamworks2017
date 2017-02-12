package org.usfirst.frc.team5401.robot;
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
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	//Global Variables

	public static final double DRIVE_SENSITIVITY_DEFAULT = 1.0;
	public static final double DRIVE_SENSITIVITY_PRECISE = 0.2;
	public static final double DRIVE_SPIN_SENSITIVITY 	 = 0.5;
	public static final double DRIVE_THRESHHOLD 		 = 0.1;
	
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
	
	//PWM Motors
	public static final int DRIVE_LEFT_MOTOR	  = 0;
	public static final int DRIVE_RIGHT_MOTOR     = 1;
	public static final int SHOOTER_MOTORS   	  = 2;
	public static final int INFEEDER_MOTOR		  = 3;
	public static final int METERING_ROLLER       = 4;
	public static final int HOPPER_CONVEYOR       = 5;
	public static final int CLIMBER_MOTOR 		  = 6;
	public static final int HOPPER_SPINNER		  = 7;
	
	//Sensor Channels
	public static final int PHOTOSWITCH_CHANNEL = 0;
	public static final int CLIMBER_LIMITSWITCH = 1;
	public static final int DRIVE_ENC_LEFT_A    = 2;
	public static final int DRIVE_ENC_LEFT_B    = 3;
	public static final int DRIVE_ENC_RIGHT_A   = 4;
	public static final int DRIVE_ENC_RIGHT_B   = 5;
	
	//Pneumatics
	public static final int PCM_ID 			= 0;
	public static final int DRIVE_SHIFT_IN   	 = 0;
	public static final int DRIVE_SHIFT_OUT  	 = 1;
	public static final int INFEEDER_IN		 	 = 2;
	public static final int INFEEDER_OUT     	 = 3;
	public static final int HOPPER_UNJAMMER_IN   = 4;
	public static final int HOPPER_UNJAMMER_OUT  = 5;
	public static final int GEAR_MANIPULATOR_IN  = 6;
	public static final int GEAR_MANIPULATOR_OUT = 7;
//	public static final int HOPPER_FLAP_IN		 = 6;
//	public static final int HOPPER_FLAP_OUT      = 7;
	
//	public static final int PCM_ID_2 		= 1;

							
}