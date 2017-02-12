package autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.Robot;
import org.usfirst.frc.team5401.robot.subsystems.DriveBase;


/**
 *
 */
public class AutoDrive extends Command {
	
	private double DesiredDistance;
	private double AutoDriveSpeed;
	private boolean DoneTraveling;
	private double DistanceTraveled;
	private double heading;
	private double drift;
	private double kP_Drift;
	
	private final double AutoDistThresh;
	
    public AutoDrive(double DistanceInput, double SpeedInput) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivebase);
    	DesiredDistance = DistanceInput;
    	AutoDriveSpeed = SpeedInput;
    	DoneTraveling = true;
    	DistanceTraveled = 0;
    	heading = Robot.drivebase.reportGyro();
    	drift = 0;
    	kP_Drift = .1;
    	
    	//Final Variables
    	AutoDistThresh = 2;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivebase.EncoderReset();
    	heading = Robot.drivebase.reportGyro();
    	drift = 0;
    	DoneTraveling = true;
    	DistanceTraveled = 0;
    	
    	SmartDashboard.putNumber("heading", heading);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Math.abs(DesiredDistance) <= AutoDistThresh){
    		//DesiredDistance too small!
    		DoneTraveling = true;
    	} else {
    		drift = Robot.drivebase.reportGyro() - heading;
    		SmartDashboard.putNumber("Drift", drift);
    			if (DesiredDistance > 0 && (DistanceTraveled < Math.abs(DesiredDistance) - AutoDistThresh)){ //DesiredDistance is positive, go forward
    				//Drive Forward
    				if (drift < -.5){ //Currently assumes we always drift left while going forwards
    					Robot.drivebase.drive(AutoDriveSpeed, AutoDriveSpeed + (kP_Drift * drift)); //Adjust right motor when driving forward
    				} else {
    					Robot.drivebase.drive(AutoDriveSpeed, AutoDriveSpeed);
    				}
    				DoneTraveling = false;
    			} else if (DesiredDistance < 0 && (DistanceTraveled > AutoDistThresh - Math.abs(DesiredDistance))){ //DesiredDistance is negative, go backward
    				//Drive Backward
    				if(drift > .5){ //Currently assumes we always drift right while going backwards
    					Robot.drivebase.drive(-(AutoDriveSpeed + (kP_Drift * drift)), -AutoDriveSpeed);//Adjusts left motor when driving backwards
    				}else{
    					Robot.drivebase.drive(-AutoDriveSpeed, -AutoDriveSpeed);
    				}
    				DoneTraveling = false;
    			} else { //error, exactly 0, or done
    				//Finished
    				DoneTraveling = true;
    			}
    		DistanceTraveled = (Robot.drivebase.getEncoderDistance());
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return DoneTraveling;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivebase.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
