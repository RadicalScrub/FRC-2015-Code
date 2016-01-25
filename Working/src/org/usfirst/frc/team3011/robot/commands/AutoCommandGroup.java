package org.usfirst.frc.team3011.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCommandGroup extends CommandGroup {
    
    public  AutoCommandGroup() {
    	
    	//STARTING AT YELLOW BIN
    	
    	addSequential(new LiftCommand(0.75,0.5,true));
    	Timer.delay(1);
    	addSequential(new AutoMove (1,2.5,13.5));
    	addParallel(new LiftCommand (1, 0.5, true));
    	addSequential(new LiftCommand(0.75,0.5,false));	
    	
    		
    	//STARTING AT LANDFILL
    	
    	//addSequential(new AutoMove (0.6, 2.5, 0.4));
    	//Timer.delay(1.5);
    	//addSequential(new LiftCommand (0.75,0.5, true));
    	//addSequential(new AutoMove (-1, 2.5, 7.8));
    	//addSequential (new LiftCommand (0.75, 0.5, false));
    	
    	
        
    	
    	
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
