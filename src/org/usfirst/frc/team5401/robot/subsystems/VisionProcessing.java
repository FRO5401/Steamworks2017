package org.usfirst.frc.team5401.robot.subsystems;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import java.io.*;

import org.usfirst.frc.team5401.robot.TargetMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5401.robot.commands.XboxMove;


import com.jcraft.jsch.JSchException;

/**
 *
 */
public class VisionProcessing extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    //Constants for the indices of the target array
    double targetValid;
    double targetX;
    double targetY;
    double targetHeight;
    double targetWidth;

    //Constants for the login information
    String HOST="pulsatrix.local";
    String USER="pi";
    String PASSWORD="raspberry";
	
    //Constants for command strings
    String START_COMMAND="govispro";
    String TERMINATE_COMMAND="killall java";
	
	//TODO indenting is all f'd up down below
    Channel channel;
	Session session;
	//static NetworkTable targetRect;
	NetworkTable targetRect;
	double networkDefault = -99;
	InputStream in;
	/* 	target[0] is data validity flag
		target [1...4] is [x y height width] defined in readTargetingData()
	*/
	public VisionProcessing(){
		SmartDashboard.putNumber("VALID", targetValid);
		java.util.Properties config = new java.util.Properties(); 
    	config.put("StrictHostKeyChecking", "no");
    	JSch jsch = new JSch();
		try {
	    	System.out.println("Trying login");
			session = jsch.getSession(USER, HOST, 22);
	    	session.setPassword(PASSWORD);
	    	session.setConfig(config);  //XXX No idea what this does
	    	session.connect();
			channel=session.openChannel("exec");
			((ChannelExec)channel).setPty(true); 
//			channel.setInputStream(in, true);
	    	((ChannelExec)channel).setCommand("cd /home/pi/vision; java -Djava.library.path=/usr/local/share/OpenCV/java -jar Webcam.java &");
			channel.connect();//This is important
			
			if (channel.isConnected()){
		    	System.out.println("Connected");
//		    	((ChannelExec)channel).setCommand("sudo wall -n Connected");
		    	
	    	} else {
		    	System.out.println("Not Connected");
	    	}
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Exception logging in");
		}
/*		try{Thread.sleep(1000);}catch(Exception ee)
		{
			System.out.println("Exception sleeping");
			
		}
*/		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
//    	setDefaultCommand(new XboxMove());
    }
    
	//Start the command running on the Raspberry Pi
    public boolean beginTargeting() {
		try {
			StringBuilder outputBuffer = new StringBuilder();

		        Channel channel = session.openChannel("exec");
		        ((ChannelExec)channel).setCommand("govispro");
		        InputStream commandOutput = channel.getInputStream();
		        channel.connect();
		        int readByte = commandOutput.read();

		        while(readByte != 0xffffffff)
		        {
		           outputBuffer.append((char)readByte);
		           readByte = commandOutput.read();
		        }
/*		channel=session.openChannel("exec");
    	((ChannelExec)channel).setCommand("cd /home/pi/vision; java -Djava.library.path=/usr/local/share/OpenCV/java -jar Webcam.java &");
		channel.connect();//This is important
*/	   	System.out.println("Begin Targeting");
		if (channel.isConnected()){
	    	System.out.println("Connected");	    	
    	} else {
	    	System.out.println("Not Connected");
    	}
//    	((ChannelExec)channel).setCommand(START_COMMAND);
//		channel.connect();//This is important
        channel.disconnect();
		}catch(IOException ioX)
	     {
			System.out.println(ioX);
	     }
	     catch(JSchException jschX)
	     {
				System.out.println(jschX);
	     }
    	return true;
    }
	
	//Stop the command runnin on the Raspberry Pi
    public boolean terminateTargeting() {
		try {
	   	System.out.println("Terminate Targeting");
		channel=session.openChannel("exec");
    	((ChannelExec)channel).setCommand(TERMINATE_COMMAND);
		channel.connect();//This is important
    	System.out.println("Terminating");

        channel.setInputStream(null);
        channel.disconnect();
        session.disconnect();
        System.out.println("DONE");
	}catch(Exception ee){
    	System.out.println(ee);
	}
    	return true;
    }
    
	//Assigns values to array based on network table values
	//TODO Thread this
    public boolean readTargetingData(){
		try {
		System.out.println("Read Target Data");
    	targetRect = NetworkTable.getTable("BoilerPipeLineOut");
    	targetValid = targetRect.getNumber("valid", networkDefault);
    	targetX =  targetRect.getNumber("X", networkDefault);
    	targetY =  targetRect.getNumber("Y", networkDefault);
    	targetHeight =  targetRect.getNumber("height", networkDefault);
    	targetWidth =  targetRect.getNumber("width", networkDefault);
    	SmartDashboard.putNumber("VALID", targetValid);
    	System.out.println(targetValid);
    	System.out.println(targetX);
    	System.out.println(targetY);
    	System.out.println(targetHeight);
    	System.out.println(targetWidth);
		}catch(Exception ee)
			{
	    	System.out.println("No target data");
	    	System.out.println(ee);
		    }
    	return true;
    }

	//Method for computing the angle to put target in shooting sights
    public double findTargetAngle(){
    	double Angle = 0;
    	readTargetingData();
    	try {
        	if (targetValid > 0) {
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

}

