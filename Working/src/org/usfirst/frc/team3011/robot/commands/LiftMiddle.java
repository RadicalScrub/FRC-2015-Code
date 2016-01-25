package org.usfirst.frc.team3011.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3011.robot.Robot;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class LiftMiddle extends Command {

    public LiftMiddle() {
        requires(Robot.tower);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.tower.setSpeed(-1);
    	Timer.delay(.875);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	Robot.tower.setSpeed(0);	//stop when finished
    }

    protected void interrupted() {
    	Robot.tower.setSpeed(0);	//stop when interrupted
    }
}
