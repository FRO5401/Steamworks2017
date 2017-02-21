
package org.usfirst.frc.team5401.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5401.robot.subsystems.*;

//import org.usfirst.frc.team5401.robot.autonomous.AutoTurnAngle;
//import org.usfirst.frc.team5401.robot.autonomous.DoNothing;
import org.usfirst.frc.team5401.robot.autonomous.*; //XXX Temporary - Should import only the ones being used
import org.usfirst.frc.team5401.robot.commands.XboxMove;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static DriveBase drivebase;
	public static Climber climber;
	public static GearMechanism gearmechanism;
	public static Infeed infeed;
	public static Loader loader;
	public static Shooter shooter;
	public static VisionProcessing visionprocessing;
	public static CompressorSubsystem compressorsubsystem;
	public static Unjammer unjammer;
	public static DummyCameras dummycameras;
	public static OI oi;

    Command autonomousCommand;
    SendableChooser chooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	drivebase = new DriveBase();
    	climber = new Climber();
    	gearmechanism = new GearMechanism();
    	infeed = new Infeed();
    	loader = new Loader();
    	shooter = new Shooter();
    	visionprocessing = new VisionProcessing();
    	compressorsubsystem = new CompressorSubsystem();
    	unjammer = new Unjammer();
    	dummycameras = new DummyCameras();
    	
		oi = new OI();/****ALWAYS Instantiate OI() last*****/
        
		Robot.compressorsubsystem.stopCompressor();
		chooser = new SendableChooser();
		chooser.addDefault("Do Nothing", new DoNothing());
		chooser.addObject("CenterGear", new AutoCenterGear());
		chooser.addObject("CenterGearBlueShoot", new AutoCenterGearBlueShoot());
		chooser.addObject("CenterGearRedShoot", new AutoCenterGearRedShoot());
		chooser.addObject("Drive", new AutoDrive(12, .5)); //Takes in distance to drive in inches, speed to drive at
		chooser.addObject("LeftGearBlue", new AutoLeftGearBlue());
		chooser.addObject("LeftGearBlueShoot", new AutoLeftGearBlueShoot());
		chooser.addObject("LeftGearRed", new AutoLeftGearRed());
		chooser.addObject("NoGearBlueTargetedShootBaseline", new AutoNoGearBlueTargetedShootBaseline());
		chooser.addObject("NoGearBlueTargetShooterHopper", new AutoNoGearBlueTargetShooterHopper());
		chooser.addObject("NoGearRedTargetedShootBaseline", new AutoNoGearRedTargetedShootBaseline());
		chooser.addObject("NoGearRedTargetShooterHopper", new AutoNoGearRedTargetShooterHopper());
		chooser.addObject("NoGearShootBaselineBlue", new AutoNoGearShootBaselineBlue());
		chooser.addObject("NoGearShootBaselineRed", new AutoNoGearShootBaselineRed());
		chooser.addObject("NoGearShootHopperBlue", new AutoNoGearShootHopperBlue());
		chooser.addObject("NoGearShootHopperRed", new AutoNoGearShootHopperRed());
		chooser.addObject("PopGear", new AutoPopGear());
		chooser.addObject("RightGearBlue", new AutoRightGearBlue());
		chooser.addObject("RightGearRed", new AutoRightGearRed());
		chooser.addObject("RightGearRedShoot", new AutoRightGearRedShoot());
		chooser.addObject("Shoot", new AutoShoot());
		chooser.addObject("Target", new AutoTarget(90, true, false)); //takes in angle to turn //CommandGroup
		chooser.addObject("TargetAndShoot", new AutoTargetAndShoot(90, true, false)); //takes in angle to turn
		chooser.addObject("TurnAngle", new AutoTurnAngle(90, true, false)); //takes in angle to turn
        SmartDashboard.putData("Auto mode", chooser);
        
        //Ensures that the "Target Angle" value is on the dashboard
        SmartDashboard.putNumber("Target Angle", 0);
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
        
        //Start loader motors

        //Robot.compressorsubsystem.startCompressor();
        Robot.drivebase.resetGyro();
        SmartDashboard.putNumber("Velocity",  Robot.shooter.getVelocity());
        Robot.compressorsubsystem.startCompressor();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putNumber("Velocity",  Robot.shooter.getVelocity());
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        
        SmartDashboard.putNumber("Velocity",  Robot.shooter.getVelocity());
        Robot.compressorsubsystem.startCompressor();
        
        Scheduler.getInstance().add(new XboxMove());
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        Robot.compressorsubsystem.getCompressorStatus();
        SmartDashboard.putNumber("Velocity",  Robot.shooter.getVelocity());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
