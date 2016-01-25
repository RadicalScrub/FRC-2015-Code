package org.usfirst.frc.team3011.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3011.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
/**
 *
 */
public class LiftCommand extends Command {
	boolean moveUp;
	double speed;
	double time;

    public LiftCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    requires (Robot.tower);
    }
    public LiftCommand(double inputSpeed, double inputTime, boolean direction){
    	requires(Robot.tower);
    	
    	this.speed = inputSpeed;
    	this.time = inputTime;
    	this.moveUp = direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (this.moveUp) {
    		Robot.tower.setSpeed (this.speed);
    	} else {
    		Robot.tower.setSpeed (- this.speed);
    	}
    	Timer.delay(this.time);
    	Robot.tower.setSpeed(0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
