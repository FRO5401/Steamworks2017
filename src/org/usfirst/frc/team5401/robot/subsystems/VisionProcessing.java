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
	String host="pulsatrix.local";
    String user="pi";
    String password="raspberry";
    String startCommand="java -Djava.library.path=/usr/local/share/OpenCV/java -jar Webcam.java &";
    String terminateCommand="killall java";
    Channel channel;
	Session session;
	static NetworkTable targetRect;//XXX
	double networkDefault = -99;
	private double target[];
	
	public VisionProcessing(){
		java.util.Properties config = new java.util.Properties(); 
    	config.put("StrictHostKeyChecking", "no");
    	JSch jsch = new JSch();
		try {
			session = jsch.getSession(user, host, 22);
	    	session.setPassword(password);
	    	session.setConfig(config);
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
    
    public boolean beginTargeting() {
    	((ChannelExec)channel).setCommand(startCommand);
        channel.setInputStream(null);
    	return true;
    }

    public boolean terminateTargeting() {
    	((ChannelExec)channel).setCommand(terminateCommand);
        channel.setInputStream(null);
        channel.disconnect();
        session.disconnect();
        System.out.println("DONE");
    	return true;
    }
    
    public boolean readTargetingData(){
    	targetRect = NetworkTable.getTable("BoilerPipeLineOut");
    	target[0] =  targetRect.getNumber("X", networkDefault);
    	target[1] =  targetRect.getNumber("Y", networkDefault);
    	target[2] =  targetRect.getNumber("height", networkDefault);
    	target[3] =  targetRect.getNumber("width", networkDefault);//XXX
    	System.out.println(target);

    	return true;
    }

}

