package org.usfirst.frc.team5401.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team5401.robot.commands.*;
import org.usfirst.frc.team5401.robot.autonomous.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
	Joystick xboxController_Driver = new Joystick(RobotMap.XBOX_CONTROLLER_DRIVER);
	Joystick xboxController_Operator = new Joystick(RobotMap.XBOX_CONTROLLER_OPERATOR);
	
	//Buttons
	Button xboxA_Driver			  = new JoystickButton(xboxController_Driver, 1);
	Button xboxB_Driver			  = new JoystickButton(xboxController_Driver, 2);
	Button xboxX_Driver			  = new JoystickButton(xboxController_Driver, 3);
	Button xboxY_Driver			  = new JoystickButton(xboxController_Driver, 4);
	Button xboxLeftBumper_Driver  = new JoystickButton(xboxController_Driver, 5);
	Button xboxRightBumper_Driver = new JoystickButton(xboxController_Driver, 6);
	Button xboxBack_Driver		  = new JoystickButton(xboxController_Driver, 7);
	Button xboxStart_Driver		  = new JoystickButton(xboxController_Driver, 8);
	Button xboxL3_Driver		  = new JoystickButton(xboxController_Driver, 9);
	Button xboxR3_Driver		  = new JoystickButton(xboxController_Driver, 10);
	
	Button xboxA_Operator			= new JoystickButton(xboxController_Operator, 1);
	Button xboxB_Operator			= new JoystickButton(xboxController_Operator, 2);
	Button xboxX_Operator			= new JoystickButton(xboxController_Operator, 3);
	Button xboxY_Operator			= new JoystickButton(xboxController_Operator, 4);
	Button xboxLeftBumper_Operator  = new JoystickButton(xboxController_Operator, 5);
	Button xboxRightBumper_Operator = new JoystickButton(xboxController_Operator, 6);
	Button xboxBack_Operator		= new JoystickButton(xboxController_Operator, 7);
	Button xboxStart_Operator		= new JoystickButton(xboxController_Operator, 8);
	Button xboxL3_Operator		  	= new JoystickButton(xboxController_Operator, 9);
	Button xboxR3_Operator		  	= new JoystickButton(xboxController_Operator, 10);
	
	
	
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	//Buttons
	public OI(){

		//Shoot (Loader) Button
		xboxA_Driver.toggleWhenPressed(new LoadShooter());

		
		//Climber Button
		xboxY_Operator.whenPressed(new Climb());
		
		//Flywheels Button
		xboxB_Operator.toggleWhenPressed(new FlyWheelControl());
		
		//AutoTarget Button
		xboxA_Operator.toggleWhenPressed(new TargetHigh());
		
		//Unjammer Button
		xboxRightBumper_Operator.whileHeld(new UnjamToggle());
		
		//Unjammer In Only Button
		xboxLeftBumper_Operator.whenPressed(new UnjamIn());
		

		//XXX TEMPORARY BUTTON
			//For testing pneumatic shifter on drivebase
		xboxBack_Driver.toggleWhenPressed(new TestShift());

	}
	
	/**Method Naming: 'read' = Analog; 'get' = Digital **/
	
	public double readXboxLeftX_Driver(){
		return xboxController_Driver.getRawAxis(RobotMap.XBOX_AXIS_LEFT_X);
	}
	
	public double readLeftTrigger_Driver(){
		return xboxController_Driver.getRawAxis(RobotMap.XBOX_AXIS_LEFT_TRIGGER);		
	}
	
	public double readRightTrigger_Driver(){
		return xboxController_Driver.getRawAxis(RobotMap.XBOX_AXIS_RIGHT_TRIGGER);
	}
	
	public boolean getPrecision_Driver(){
		return xboxController_Driver.getRawButton(5);
	}
	
	public boolean getBrake_Driver(){
		return xboxController_Driver.getRawButton(6);
	}
	
	public boolean getTurnButton_Driver(){
		return xboxController_Driver.getRawButton(9);
	}
	
	//For GearMechanism
	public int getXboxRightStickY_Driver(){
		double value = xboxController_Driver.getRawAxis(RobotMap.XBOX_AXIS_RIGHT_X);
		if (value > .1){
			return 1;
		} else if (value < -.1){
			return -1;
		} else {
			return 0;
		}
	}
	

	//For Feeder Up/Down
	public int getXboxLeftStickY_Operator(){
		double value = xboxController_Driver.getRawAxis(RobotMap.XBOX_AXIS_LEFT_Y);
		if (value > .1){
			return 1;
		} else if (value < -.1){
			return -1;
		} else {
			return 0;
		}
	}
	
	//For Feeder In/Out
	public int getXboxTriggers_Operator(){
		double left  = xboxController_Operator.getRawAxis(RobotMap.XBOX_AXIS_LEFT_TRIGGER);
		double right = xboxController_Operator.getRawAxis(RobotMap.XBOX_AXIS_RIGHT_TRIGGER);
		if (right > .1){ //Feeder In in prioritized over Out
			return 1;
		} else if (left > .1){
			return -1;
		} else {
			return 0;
		}
	}
	
}