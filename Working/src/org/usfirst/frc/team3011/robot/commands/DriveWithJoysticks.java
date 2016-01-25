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

    protected void initialize() {	// Called just before this Command runs the first time
    }

    protected void execute() {	// Called repeatedly when this Command is scheduled to run
    	//MOVE THE WHEELS TO THE INPUT SETPOINT
    	Robot.oi.calculateWheels();
    	
    	Robot.wheelFR.setSetpoint(Robot.oi.getWAFR());
    	Robot.wheelRR.setSetpoint(Robot.oi.getWARR());
    	Robot.wheelFL.setSetpoint(Robot.oi.getWAFL());
    	Robot.wheelRL.setSetpoint(Robot.oi.getWARL());
    	
    	if (Math.abs(Robot.wheelFR.getPosition() - Robot.oi.getWAFR()) > 0.15){
    		Robot.wheelFR.setSetpoint(Robot.oi.getWAFR());
    	}
    	
    	if (Math.abs(Robot.wheelRR.getPosition() - Robot.oi.getWARR()) > 0.15){
    		Robot.wheelRR.setSetpoint(Robot.oi.getWARR());
    	}
    	
    	if (Math.abs(Robot.wheelFL.getPosition() - Robot.oi.getWAFL()) > 0.15){
    		Robot.wheelFL.setSetpoint(Robot.oi.getWAFL());
    	}
    	
    	if (Math.abs(Robot.wheelRL.getPosition() - Robot.oi.getWARL()) > 0.15){
    		Robot.wheelRL.setSetpoint(Robot.oi.getWARL());
    	}
    	
    	//SETS SPEED AFTER THE CORRECT ANGLE HAS BEEN REACHED
    	
    	Robot.wheelFR.setSpeed(Robot.oi.getWFR());
    	Robot.wheelRR.setSpeed(Robot.oi.getWRR());
    	Robot.wheelFL.setSpeed(Robot.oi.getWFL());
    	Robot.wheelRL.setSpeed(Robot.oi.getWRL());
    	
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
