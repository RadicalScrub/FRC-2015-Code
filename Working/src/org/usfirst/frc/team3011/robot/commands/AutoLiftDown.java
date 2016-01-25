
package org.usfirst.frc.team3011.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3011.robot.Robot;

/**
 *
 */
public class AutoLiftDown extends Command {

    public AutoLiftDown() {
        requires(Robot.tower);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.tower.setSpeed(-1);	//move DOWNWARD at full speed
    	Timer.delay(1);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.tower.setSpeed(0);	//stop when finished
    }

    protected void interrupted() {
    	Robot.tower.setSpeed(0);	//stop when interrupted
    }
}
