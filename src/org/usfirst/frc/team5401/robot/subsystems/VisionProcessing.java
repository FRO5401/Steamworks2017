package org.usfirst.frc.team5401.robot.subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import org.usfirst.frc.team5401.robot.TargetMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5401.robot.commands.TargetHigh;

/**
 *
 */
public class VisionProcessing extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    //Constants for the indices of the target array
    boolean targetValid;
    double targetX;
    double targetY;
    double targetHeight;
    double targetWidth;
	
	//TODO indenting is all f'd up down below
    //static NetworkTable targetRect;
	NetworkTable targetRect;
	NetworkTable outCmd;
	static String tableName = "BoilerPipeLineOut";
	static String commandName = "BoilerCommands";

	final double NETWORK_DEFAULT = -99;
	/* 	target[0] is data validity flag
		target [1...4] is [x y height width] defined in readTargetingData()
	*/
	public VisionProcessing(){
		SmartDashboard.putBoolean("VALID", targetValid);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
//    	setDefaultCommand(new XboxMove());
    }
    	
	//Assigns values to array based on network table values
	//TODO Thread this
    public double readTargetingData(){
		try {
		System.out.println("Read Target Data");
    	targetRect = NetworkTable.getTable("BoilerPipeLineOut");
    	targetValid = targetRect.getBoolean("valid", false);
    	if (targetValid){
        	targetX =  targetRect.getNumber("X", NETWORK_DEFAULT);
        	targetY =  targetRect.getNumber("Y", NETWORK_DEFAULT);
        	targetHeight =  targetRect.getNumber("height", NETWORK_DEFAULT);
        	targetWidth =  targetRect.getNumber("width", NETWORK_DEFAULT);
        	System.out.println(targetValid);
        	System.out.println(targetX);
        	System.out.println(targetY);
        	System.out.println(targetHeight);
        	System.out.println(targetWidth);    		
    	}
    	SmartDashboard.putBoolean("VALID", targetValid);
		}catch(Exception ee)
			{
	    	System.out.println("Target Data Null");
	    	System.out.println(ee);
		    }
    	return 0;//this.findTargetAngle();//TODO change back to boolean when I figure out how to thread it
    }

	//Method for computing the angle to put target in shooting sights
    public double findTargetAngle(){
    	double Angle = 0;
    	readTargetingData();
    	try {
        	if (targetValid) {
        		float pixelAngleScale_X = TargetMap.BOILER_CAM_RES_X/TargetMap.BOILER_CAM_FOV_X ;
        		Angle = (targetX - TargetMap.BOILER_UPLEFT_X)/ pixelAngleScale_X;    		
        	} else {
        		Angle = 0;
        	} 
    	}catch (Exception ee) {
        		System.out.println("No Target");
       	}
    		
    	
    	return Angle;
    }

	//Method for computing the distance from target to optimal shooting location
    public double findTargetDistance(){
    	double normalizedWidth = 2*targetWidth/TargetMap.BOILER_CAM_RES_X;
    	double distanceTrig =  TargetMap.BOILER_WIDTH/(normalizedWidth*12*Math.tan(TargetMap.BOILER_CAM_FOV_X*Math.PI/(180*2)));
    	float pixelDistanceScale = TargetMap.BOILER_CAM_RES_Y/TargetMap.BOILER_CAM_FOV_Y ;
    	double distanceY = (targetY - TargetMap.BOILER_UPLEFT_Y)/ pixelDistanceScale;
    	double distance = distanceTrig;
    	return distance;
    }

    public boolean terminateTargeting(boolean terminate){
    	try {    			
    		outCmd = NetworkTable.getTable("BoilerCommands");
            outCmd.putBoolean("Cmd", terminate);
        	return true;
    	}catch (Exception ee) {
        		System.out.println("Terminate Failed");
            	return false;
       	}
//    	return true; //Unreachable due to try/catch
    }

}

