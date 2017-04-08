package org.usfirst.frc.team5401.robot.autonomous;

import org.usfirst.frc.team5401.robot.commands.GetShooterUpToSpeed;
import org.usfirst.frc.team5401.robot.commands.LoadShooter;
import org.usfirst.frc.team5401.robot.commands.Shoot;
import org.usfirst.frc.team5401.robot.commands.ShooterStop;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *NEED TO ADJUST last leg because the robot doesn't hit baseline
 */
public class AutoRedShootGearPositionTechFiresWay extends CommandGroup {

    public AutoRedShootGearPositionTechFiresWay() {
    	addParallel(new GetShooterUpToSpeed());
    	addSequential(new WaitCommand(2));
    	addSequential(new AutoDrive(-2.5, .5));
    	addSequential(new AutoTurnAngle(-7, true,false));
    	addSequential(new Shoot());
    	addSequential(new WaitCommand(4));
    	addSequential(new ShooterStop());
    	addSequential(new LoadShooter());
    	addSequential(new AutoTurnAngle(0, true, false));// was 32, 0 is only for teamup with 225
    	addSequential(new AutoDrive(-20, .9));//Formerly -47
    	addSequential(new AutoTurnAngle(-28, true, false));
    	
    	
    	addSequential(new AutoTurnAngle(90, true, false));
    	addSequential(new AutoDrive(-10,0.75));
    	
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
