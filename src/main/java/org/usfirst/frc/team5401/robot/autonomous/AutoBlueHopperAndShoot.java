package org.usfirst.frc.team5401.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

import org.usfirst.frc.team5401.robot.commands.GetShooterUpToSpeed;
import org.usfirst.frc.team5401.robot.commands.UnjamToggle;
import org.usfirst.frc.team5401.robot.commands.Shoot;
import org.usfirst.frc.team5401.robot.commands.FeederInOut;
import org.usfirst.frc.team5401.robot.commands.FeederArmUpDown;

/**
 *
 */
public class AutoBlueHopperAndShoot extends CommandGroup {

    public AutoBlueHopperAndShoot() {
    	addParallel(new UnjamToggle(-1));
    	addSequential(new AutoDrive(141, 0.9));
    	addSequential(new AutoTurnAngle(-45, true, false));
    	addSequential(new WaitCommand(3));
    	
    	addSequential(new AutoTurnAngle(-90, true, false));
    	addSequential(new FeederArmUpDown(1));
    	addParallel(new FeederInOut(-1));
    	addSequential(new AutoDrive(75, 0.9));
    	addSequential(new FeederArmUpDown(-1));
    	addParallel(new GetShooterUpToSpeed());
    	addSequential(new AutoDrive(5, 0.5));
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
