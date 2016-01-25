
package org.usfirst.frc.team3011.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team3011.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;
/**
 * Need to make the lift go up/down for a set time 
 */
public class AutoLiftUp extends Command {
	Timer yolo = new Timer();
    public AutoLiftUp() {
        requires(Robot.tower);
    }
    
    protected void initialize() {
    }

    protected void execute() {
    	yolo.start();
    	while (yolo.get() < 1.6){
    	Robot.tower.setSpeed(1);	//move UPWARD at full speed
    	}
    	
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