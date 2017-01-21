package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DigitalInput;

import org.usfirst.frc.team5401.robot.RobotMap;
import org.usfirst.frc.team5401.robot.commands.LoadShooter;

/**
 *
 */
public class Loader extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
		VictorSP leftConveyor;
		VictorSP rightConveyor;
		DigitalInput limitSwitch; 
		
		private final double LOADER_SPEED;
	//declare Victor motor
	
	
	public Loader(){
		//initialize Victor motor
		leftConveyor = new VictorSP(RobotMap.LOADER_CONVEYOR_LEFT);
		rightConveyor = new VictorSP(RobotMap.LOADER_CONVEYOR_RIGHT);
		LOADER_SPEED = 1;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new LoadShooter());
    }
    
    //load action method (direction parameter)
    	//send motor values 
    public void runConveyors(){
    	leftConveyor.set(LOADER_SPEED);
    	rightConveyor.set(LOADER_SPEED);
    }
    
   public void stopConveyors(){
	   leftConveyor.set(0);
   		rightConveyor.set(0);
   }
    
}

