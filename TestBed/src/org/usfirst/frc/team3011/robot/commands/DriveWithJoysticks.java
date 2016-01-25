package org.usfirst.frc.team3011.robot.commands;

import org.usfirst.frc.team3011.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveWithJoysticks extends Command {

    public DriveWithJoysticks() {
    	
        requires(Robot.wheelFR);
        requires(Robot.wheelRR);
        requires(Robot.wheelFL);
        requires(Robot.wheelRL);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.oi.calculateWheels();
    	Robot.wheelFR.setSetpoint(Robot.oi.getWAFR());
    	Robot.wheelFR.setSpeed(Robot.oi.getWFR());
    	Robot.wheelFR.enable();
    	Robot.wheelRR.setSetpoint(Robot.oi.getWARR());
    	Robot.wheelRR.setSpeed(Robot.oi.getWRR());
    	Robot.wheelRR.enable();
    	Robot.wheelFL.setSetpoint(Robot.oi.getWAFL());
    	Robot.wheelFL.setSpeed(Robot.oi.getWFL());
    	Robot.wheelFL.enable();
    	Robot.wheelRL.setSetpoint(Robot.oi.getWARL());
    	Robot.wheelRL.setSpeed(Robot.oi.getWRL());
    	Robot.wheelRL.enable();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
