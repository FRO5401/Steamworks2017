package org.usfirst.frc.team5401.robot.subsystems;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import org.usfirst.frc.team5401.robot.TargetMap;


import com.jcraft.jsch.JSchException;

/**
 *
 */
public class VisionProcessing extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    //Constants for the indices of the target array
    final int VALID = 0;
    final int X = 1;
    final int Y = 2;
    final int HEIGHT = 3;
    final int WIDTH = 4;

    //Constants for the login information
    String HOST="pulsatrix.local";
    String USER="pi";
    String PASSWORD="raspberry";
	
    //Constants for command strings
    String START_COMMAND="java -Djava.library.path=/usr/local/share/OpenCV/java -jar Webcam.java &";
    String TERMINATE_COMMAND="killall java";
	
	//TODO indenting is all f'd up down below
    Channel channel;
	Session session;
	static NetworkTable targetRect;
	double networkDefault = -99;
	private double target[];
	/* 	target[0] is data validity flag
		target [1...4] is [x y height width] defined in readTargetingData()
	*/
	public VisionProcessing(){
		java.util.Properties config = new java.util.Properties(); 
    	config.put("StrictHostKeyChecking", "no");
    	JSch jsch = new JSch();
		try {
			session = jsch.getSession(USER, HOST, 22);
	    	session.setPassword(PASSWORD);
	    	session.setConfig(config);  //XXX No idea what this does
	    	session.connect();
			channel=session.openChannel("exec");
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{Thread.sleep(1000);}catch(Exception ee){}

    	System.out.println("Connected");
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
	//Start the command running on the Raspberry Pi
    public boolean beginTargeting() {
    	((ChannelExec)channel).setCommand(START_COMMAND);
        channel.setInputStream(null);
    	return true;
    }
	
	//Stop the command runnin on the Raspberry Pi
    public boolean terminateTargeting() {
    	((ChannelExec)channel).setCommand(TERMINATE_COMMAND);
        channel.setInputStream(null);
        channel.disconnect();
        session.disconnect();
        System.out.println("DONE");
    	return true;
    }
    
	//Assigns values to array based on network table values
	//TODO Thread this
    public boolean readTargetingData(){
    	targetRect = NetworkTable.getTable("BoilerPipeLineOut");
    	target[VALID] = targetRect.getNumber("valid", networkDefault);
    	target[X] =  targetRect.getNumber("X", networkDefault);
    	target[Y] =  targetRect.getNumber("Y", networkDefault);
    	target[HEIGHT] =  targetRect.getNumber("height", networkDefault);
    	target[WIDTH] =  targetRect.getNumber("width", networkDefault);
    	System.out.println(target);

    	return true;
    }

	//Method for computing the angle to put target in shooting sights
    public double findTargetAngle(){
	float pixelAngleScale_X = TargetMap.BOILER_CAM_RES_X/TargetMap.BOILER_CAM_FOV_X ;
	double Angle = (target[X] - TargetMap.BOILER_UPLEFT_X)/ pixelAngleScale_X;
    	return Angle;
    }

	//Method for computing the distance from target to optimal shooting location
    public double findTargetDistance(){
	double normalizedWidth = 2*target[WIDTH]/TargetMap.BOILER_CAM_RES_X;
	double distanceTrig =  TargetMap.BOILER_WIDTH/(normalizedWidth*12*Math.tan(TargetMap.BOILER_CAM_FOV_X*Math.PI/(180*2)));
	float pixelDistanceScale = TargetMap.BOILER_CAM_RES_Y/TargetMap.BOILER_CAM_FOV_Y ;
	double distanceY = (target[Y] - TargetMap.BOILER_UPLEFT_Y)/ pixelDistanceScale;
	double distance = distanceTrig;
    	return distance;
    }

}

