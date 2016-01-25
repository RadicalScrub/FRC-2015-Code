package org.usfirst.frc.team3011.robot.commands;

import org.usfirst.frc.team3011.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class AutoMove extends Command {

	double speed;
	double angle;
	double distance;
	
    public AutoMove() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	 requires(Robot.wheelFR);
         requires(Robot.wheelRR);
         requires(Robot.wheelFL);
         requires(Robot.wheelRL);   
         requires(Robot.distanceEncoder);
    }

    /*
     * INPUT ANGLE: 0 to 5 volts for the absolutes
     */
    public AutoMove (double inputSpeed, double inputAngle, double inputDistance){
    	requires(Robot.wheelFR);
        requires(Robot.wheelRR);
        requires(Robot.wheelFL);
        requires(Robot.wheelRL);
        requires(Robot.distanceEncoder);

        
        this.speed = inputSpeed;
        this.angle = inputAngle;
        this.distance = inputDistance;
        
    	
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.wheelFR.setSetpoint(this.angle);
    	///Robot.wheelFR.enable();
    	Robot.wheelRR.setSetpoint(this.angle);
    	//Robot.wheelRR.enable();
    	Robot.wheelFL.setSetpoint(this.angle);
    	//Robot.wheelFL.enable();
    	Robot.wheelRL.setSetpoint(this.angle);
    	//Robot.wheelRL.enable();
    	while (Math.abs(Robot.wheelFR.getPosition()-this.angle)> 0.08) {}
    	while (Math.abs(Robot.wheelRR.getPosition()-this.angle)> 0.08) {}
    	while (Math.abs(Robot.wheelFL.getPosition()-this.angle)> 0.08){}
    	while (Math.abs(Robot.wheelRL.getPosition()-this.angle)> 0.08){}
    	
    	Robot.distanceEncoder.resetDistance();
    	
    	Robot.wheelFR.setSpeed(this.speed);
    	Robot.wheelRR.setSpeed(this.speed);
    	Robot.wheelFL.setSpeed(this.speed);
    	Robot.wheelRL.setSpeed(this.speed);
    	
    	double currentEncoderValue = Robot.distanceEncoder.getDistance();
    	Timer.delay(0.5);
    	
    	while (Math.abs(Robot.distanceEncoder.getDistance()) < this.distance && Robot.isAutoMode && Math.abs(Robot.distanceEncoder.getDistance()-currentEncoderValue) > 0.1){
    		Timer.delay(0.5);
    		SmartDashboard.putNumber("distance", Robot.distanceEncoder.getDistance());
    	}
    	
    	Robot.distanceEncoder.resetDistance();
    	SmartDashboard.putNumber("distance", Robot.distanceEncoder.getDistance());
    	
    	Robot.wheelFR.setSpeed(0);
    	Robot.wheelRR.setSpeed(0);
    	Robot.wheelFL.setSpeed(0);
    	Robot.wheelRL.setSpeed(0);
    	
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
