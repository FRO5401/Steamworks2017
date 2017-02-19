package org.usfirst.frc.team5401.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.CameraServer;

/**
 *
 */
public class DummyCameras extends Subsystem {

	CameraServer frontCamera;
	CameraServer backCamera;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public DummyCameras(){
		frontCamera = CameraServer.getInstance();
		backCamera = CameraServer.getInstance();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void startCameras(){
    	//Before, setSize and setFPS were needed. setSize was replaced by setResolution. However, setResolutiona and setFPS are in a different class other than CameraServer
    	//cam1 and cam0 are names given to camera by roboRIO. frontCamera and backCamera are names we give them
    	frontCamera.startAutomaticCapture("cam0", 0);//parameters are String, int. Many overloads with different parameters
    	backCamera.startAutomaticCapture("cam1", 1);
    }
    
    public void getCurrentCamera(){
    	
    }
    
    public void switchCameraFrontToBack(){
    	
    }
    
    public void switchCameraBackToFront(){
    	
    }
}

