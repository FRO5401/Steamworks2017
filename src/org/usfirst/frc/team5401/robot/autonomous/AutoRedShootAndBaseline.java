package org.usfirst.frc.team5401.robot.autonomous;

import org.usfirst.frc.team5401.robot.commands.LoadShooter;
import org.usfirst.frc.team5401.robot.commands.Shoot;
import org.usfirst.frc.team5401.robot.commands.ShooterToggle;
import org.usfirst.frc.team5401.robot.commands.UnjamToggle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.usfirst.frc.team5401.robot.commands.GetShooterUpToSpeed;

/**
 *
 */
public class AutoRedShootAndBaseline extends CommandGroup {

    public AutoRedShootAndBaseline() {
    	//From KevinsWay XXX update with KevinsWay Red
    	addSequential(new GetShooterUpToSpeed());
    	addSequential(new AutoDrive(-2.5, .5));
    	addSequential(new AutoTurnAngle(-7, true,false));
    	addSequential(new Shoot());
    	addSequential(new WaitCommand(4));
    	addSequential(new ShooterToggle());
    	addSequential(new LoadShooter());
    	//End code from KevinsWay
    	addSequential(new AutoTurnAngle(61, true, false));
    	addSequential(new AutoDrive(-87,0.9)); //120
    	addSequential(new AutoTurnAngle(-90, true, false));
    	
    	/**UNCOMMENT THESE FOR HOPPER **/
    	//addSequential(new AutoDrive(20,0.9));
    	//addSequential(new UnjamToggle(-1));
        
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
