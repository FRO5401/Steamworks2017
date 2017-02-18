package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CameraServer;

/*
 *Subsystem for Dummy Cameras
 */
public class DummyCamera extends Subsystem {
	
	CameraServer camera0;
	CameraServer camera1;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public DummyCamera(){
		camera0 = CameraServer.getInstance();
		camera1 = CameraServer.getInstance();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void startAutomaticCaptureCamera0(){
    	camera0.startAutomaticCapture("camera0", 0);//Various forms, need to verify which one, probably startAutomaticCapture(String name, int dev)
    }
    
    public void startAutomaticCaptureCamera1(){
    	camera1.startAutomaticCapture("camera1", 1);//Various forms, need to verify which one, probably startAutomaticCapture(String name, int dev)
    }
    
    public void removeCamera0(){
    	camera0.removeCamera("camera0");
    }
    
    public void removeCamera1(){
    	camera1.removeCamera("camera1");
    }
    
    public void getCamera0Server(){
    	camera0.getServer("camera0");
    }
    
    public void getCamera1Server(){
    	camera0.getServer("camera1");
    }
    
    public void removeCamera0Server(){
    	camera0.removeServer("camera0");
    }
    
    public void removeCamera1Server(){
    	camera0.removeServer("camera1");
    }
    
    
}

