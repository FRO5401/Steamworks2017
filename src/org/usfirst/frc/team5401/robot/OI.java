package org.usfirst.frc.team5401.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team5401.robot.commands.*;

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
    
	Joystick XboxControllerDriver = new Joystick(RobotMap.XBOX_CONTROLLER_DRIVER);
	Joystick XboxControllerOperator = new Joystick(RobotMap.XBOX_CONTROLLER_OPERATOR);
	
	//Buttons
	
	//Driver
	Button XboxADriver						= new JoystickButton(XboxControllerDriver, 1);
	Button XboxBDriver						= new JoystickButton(XboxControllerDriver, 2);
	Button XboxXDriver						= new JoystickButton(XboxControllerDriver, 3);
	Button XboxYDriver						= new JoystickButton(XboxControllerDriver, 4);
	Button XboxUpperLeftTrigDriver			= new JoystickButton(XboxControllerDriver, 5);
	Button XboxUpperRightTrigDriver			= new JoystickButton(XboxControllerDriver, 6);
	Button XboxBackDriver					= new JoystickButton(XboxControllerDriver, 7);
	Button XboxStartDriver					= new JoystickButton(XboxControllerDriver, 8);
	Button XboxLeftStickButtonDriver		= new JoystickButton(XboxControllerDriver, 9);
	Button XboxRightStickButtonDriver		= new JoystickButton(XboxControllerDriver, 10);
	
	//Operator
	Button XboxAOperator						= new JoystickButton(XboxControllerOperator, 1);
	Button XboxBOperator						= new JoystickButton(XboxControllerOperator, 2);
	Button XboxXOperator						= new JoystickButton(XboxControllerOperator, 3);
	Button XboxYOperator						= new JoystickButton(XboxControllerOperator, 4);
	Button XboxUpperLeftTrigOperator			= new JoystickButton(XboxControllerOperator, 5);
	Button XboxUpperRightTrigOperator 			= new JoystickButton(XboxControllerOperator, 6);
	Button XboxBackOperator						= new JoystickButton(XboxControllerOperator, 7);
	Button XboxStartOperator					= new JoystickButton(XboxControllerOperator, 8);
	Button XboxLeftStickButtonOperator			= new JoystickButton(XboxControllerOperator, 9);
	Button XboxRightStickButtonOperator			= new JoystickButton(XboxControllerOperator, 10);
	
	
	
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
	
	public OI(){
		//This will make the InFeeder feed in -- Untested 
		XboxAOperator.whenPressed(new FeedInAndOut(1));	
		//This will make the InFeeder feed out -- Untested
		XboxBOperator.whenPressed(new FeedInAndOut(-1));
		//This will make the InFeeder stop -- Untested
		XboxAOperator.whenReleased(new FeedInAndOutStop());
		XboxBOperator.whenReleased(new FeedInAndOutStop());
	}
	public double readXboxDriverLeftStickX(){
		double slewInput = XboxControllerDriver.getRawAxis(0);
		return slewInput;
	}
	public double getLeftTriggerDriver(){
		double throttleInput = XboxControllerDriver.getRawAxis(2);
		return throttleInput;		
	}
	public double getRightTriggerDriver(){
		double throttleInput = XboxControllerDriver.getRawAxis(3);
		return throttleInput;
	}
	public boolean getPrecisionDriver(){
		boolean precisionInput = XboxControllerDriver.getRawButton(5);
		return precisionInput;
	}
	public boolean getBrakeDriver(){
		boolean brakeInput = XboxControllerDriver.getRawButton(6);
		return brakeInput;
	}
	public boolean getTurnButton(){
		boolean turnInput = XboxControllerDriver.getRawButton(9);
		return turnInput;
	}
	
}

