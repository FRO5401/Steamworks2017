package org.usfirst.frc.team5401.robot.subsystems;

import org.usfirst.frc.team5401.robot.commands.VisionTrackingTower;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.VisionThread;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team5401.robot.RobotMap;
import org.usfirst.frc.team5401.robot.GripPipeline;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

/**
 *Thank you team401 Camera.kt 2017 Robot for switching cameras code, not yet implemented
 *Using one dummy display camera
 */
public class DummyCameras extends Subsystem {
	//CameraServer cameraServer;
	//UsbCamera frontCamera;
	//UsbCamera backCamera;
	
	//CameraServer cam;
	
	final int localWidth;
	final int localHeight;
	final int localFPS;
	
	private static final String WEBCAM_PATH = "/dev/video0";
	private final int width = 160;
	private final int height = 120;
	
	private VisionThread visionThread;
	private double centerX = 0.0;
	private double centerY = 0.0;
	
	private final Object visionLock = new Object();
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public DummyCameras(){
		//cameraServer = CameraServer.getInstance();
		localWidth = RobotMap.CAMERA_WIDTH;
		localHeight = RobotMap.CAMERA_HEIGHT;
		localFPS = RobotMap.CAMERA_FPS;
    	
		//cam.getInstance().startAutomaticCapture(0);//0 is USB slot nearest to the center of the roboRIO.
		
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(width, height);
		camera.setBrightness(0);
		camera.setExposureManual(0);
		camera.setFPS(15);
		
		visionThread = new VisionThread(camera, new GripPipeline(), pipeline -> {
			if(!pipeline.filterContoursOutput().isEmpty()){
				Rect boundingBox = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
				
				synchronized(visionLock){
					centerX = boundingBox.x + (boundingBox.width/2);
					centerY = boundingBox.y + (boundingBox.height/2);
				}
			}
		});
		visionThread.start();
		
		/******Under this is for Camera Switching**********/
		
		//Before, setSize and setFPS were needed. setSize was replaced by setResolution. However, setResolutiona and setFPS are in a different class other than CameraServer
    	//cam1 and cam0 are names given to camera by roboRIO. frontCamera and backCamera are names we give them
		//frontCamera = cameraServer.getInstance().startAutomaticCapture("cam0", 0);//parameters are String, int. Many overloads with different parameters
		//frontCamera.setResolution(localWidth, localHeight);
		//frontCamera.setFPS(localFPS);

//		frontCamera.setResolution(localWidth, localHeight);
//		frontCamera = setFPS(localFPS);
//		backCamera = cameraServer.startAutomaticCapture("cam1", RobotMap.CAMERA_BACK);
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
    
    public double visionLoopSynchronized(){
    	double centerX;
		synchronized(visionLock){
			centerX = this.centerX;
		}
		double turn = centerX - (320/2);
		return turn;
    }
    
}