package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

/**
 *Thank you team401 Camera.kt 2017 Robot
 */
public class DummyCameras extends Subsystem {
	CameraServer cameraServer;
	UsbCamera frontCamera;
	UsbCamera backCamera;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public DummyCameras(){
		cameraServer = CameraServer.getInstance();
    	//Before, setSize and setFPS were needed. setSize was replaced by setResolution. However, setResolutiona and setFPS are in a different class other than CameraServer
    	//cam1 and cam0 are names given to camera by roboRIO. frontCamera and backCamera are names we give them
		frontCamera = cameraServer.startAutomaticCapture("cam0", 0);//parameters are String, int. Many overloads with different parameters
		frontCamera = setResolution(locaWidth, localHeight);
		frontCamera = setFPS(localFPS);
		backCamera = cameraServer.startAutomaticCapture("cam1", 1);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
    public void getCurrentCamera(){
    	
    }
    
    public void switchCameraFrontToBack(){
    	
    }
    
    public void switchCameraBackToFront(){
    	
    }
}

