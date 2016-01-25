package org.usfirst.frc.team3011.robot.commands;

import org.usfirst.frc.team3011.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RotateTo extends Command {
	private double setpoint;

    public RotateTo(double a) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.setpoint = a;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("TESTING", SmartDashboard.getNumber("TESTING",0)+1);
    	Robot.oi.prepToTurn();
    	Robot.wheelFR.setSetpoint(Robot.oi.getWAFR());
    	Robot.wheelFR.enable();
    	Robot.wheelRR.setSetpoint(Robot.oi.getWARR());
    	Robot.wheelRR.enable();
    	Robot.wheelFL.setSetpoint(Robot.oi.getWAFL());
    	Robot.wheelFL.enable();
    	Robot.wheelRL.setSetpoint(Robot.oi.getWARL());
    	Robot.wheelRL.enable();
    	while (!Robot.wheelFR.onTarget()){}
    	while (!Robot.wheelFL.onTarget()){}
    	while (!Robot.wheelRR.onTarget()){}
    	while (!Robot.wheelRL.onTarget()){}
    	Robot.robotRotation.setSetpoint(this.setpoint);
    	Robot.robotRotation.enable();
    	double gyro = Robot.imu.getYaw();
    	if (gyro < 0) {
			gyro += 360.0;
		}
    	while (Math.abs(Robot.robotRotation.getSetpoint() - gyro) > 1){
    		gyro = Robot.imu.getYaw();
	    	if (gyro < 0) {
				gyro += 360.0;
	    	}
    		SmartDashboard.putNumber(   "IMU_Yaw",              Robot.imu.getYaw());
    	}
    	Robot.robotRotation.disable();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.robotRotation.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
