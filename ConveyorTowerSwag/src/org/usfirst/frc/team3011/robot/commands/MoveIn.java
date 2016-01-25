
package org.usfirst.frc.team3011.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3011.robot.Robot;
/**
 *
 */
public class MoveIn extends Command {

    public MoveIn() {
        requires(Robot.conveyor);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.conveyor.setDirection (-1);	//move INWARDS
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.conveyor.setDirection(0);	//stop when finished
    }

    protected void interrupted() {
    	Robot.conveyor.setDirection(0);	//stop when interrupted
    }
}
