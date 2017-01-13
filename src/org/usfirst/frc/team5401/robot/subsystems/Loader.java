package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5401.robot.commands.LoadShooter;

/**
 *
 */
public class Loader extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	//declare Victor motor
	//declare limit switch
	
	public Loader(){
		//initialize Victor motor
		//initialize limit switch
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new LoadShooter());
    }
    
    //load action method (direction parameter)
    	//send motor values 
    
    //read limit switch (public)
    	//return if pressed
}

