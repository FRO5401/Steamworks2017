package org.usfirst.frc.team5401.robot.subsystems;

import org.usfirst.frc.team5401.robot.commands.VisionTrackingTower;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.VisionThread;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team5401.robot.RobotMap;
import org.usfirst.frc.team5401.robot.HalfCirlceVision;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

/**
 *Thank you team401 Camera.kt 2017 Robot for switching cameras code, not yet implemented
 *Using one dummy display camera
 * OpenCV, what GRIP is based on, uses a Screen Coordinate System. Basically there is only one quadrant,
 * 		origin is at the top left, x-axis is positive to the right, y-axis is positive downwards
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
		
		visionThread = new VisionThread(camera, new HalfCirlceVision(), pipeline -> {
			if(!pipeline.filterContoursOutput().isEmpty()){
				Rect boundingBox = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
				
				synchronized(visionLock){
					//image location is determined by the top left corner .x finds x coordinate, .y finds y coordinate
					//If there is not a shape, the coordinate will be (0,0)
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
			centerX = this.centerX;//Basically centerX in this method  = centerX declared above in the class
		}
		
		//If centerX is 0 (when there is no shape), the robot will turn until a shape is found
		System.out.println(centerX);
		
		
		//XXX Below gives turn, in pixel amount, to turn towards the middle, need to put in pixel to distance conversion 
		double turnInPixelDistance = centerX - (160/2);//XXX may need to put it as (160/2) - centerX. 160 is image width
		
		//12 inches over 160 pixel. Measured. 
		double turnInInchesDistance = turnInPixelDistance * (12.0/160.0);
		//atan is arc tan. Divide by 12 because that is the distance from the surface with the retro tape 
		double turnAngleInRad = Math.atan(turnInInchesDistance/12.0);
		double turnAngleInDegrees = turnAngleInRad * (180.0/(Math.PI));//Coverts Radian angle to degrees
		
		return turnAngleInDegrees;
    }
    
}