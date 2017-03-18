package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

import org.usfirst.frc.team5401.robot.commands.GetShooterUpToSpeed;
import org.usfirst.frc.team5401.robot.commands.UnjamToggle;
import org.usfirst.frc.team5401.robot.commands.Shoot;
/**
 *
 */
public class AutoBlueHopperAndShoot extends CommandGroup {

    public AutoBlueHopperAndShoot() {
    	addSequential(new AutoDrive(-65.0,0.9));//Moves forward
    	addSequential(new AutoTurnAngle(90, true, false));//Turns Unjammer into hopper direction
    	addSequential(new AutoDrive(75,0.9));//Start experimenting on a shorter length//Rams robot towards Hopper
    	addSequential(new UnjamToggle(-1));//Unjammer out
    	addSequential(new WaitCommand(2));//wait for ball to fall into hopper
    	addSequential(new AutoDrive(-5.0,0.9));//Drive backwards away from hopper
    	addSequential(new AutoTurnAngle(-90, true, false));//Turns to move back toward the boiler
    	addSequential(new GetShooterUpToSpeed());
    	addSequential(new AutoDrive(51.0, 0.9));//Moves toward boiler
    	addSequential(new AutoTurnAngle(41, true, false));//Turns toward boiler
    	addSequential(new Shoot());
    	
    	
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
