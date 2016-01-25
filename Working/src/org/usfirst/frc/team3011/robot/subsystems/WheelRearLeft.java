package org.usfirst.frc.team3011.robot.subsystems;

import org.usfirst.frc.team3011.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class WheelRearLeft extends PIDSubsystem {
	private static double Kp = -1;
	private static double Ki = 0;
	private static double Kd = 0;
	
	
	private static final double ZERO = 1.758,
			MAX = 3.522,
			MIN = 0.211;
			
	
	public SpeedController drive = new Jaguar(RobotMap.driveLeftRear);
	SpeedController motor = new Jaguar(RobotMap.steerLeftRear);
	AnalogInput pot = new AnalogInput(RobotMap.potLeftRear);
	
    // Initialize your subsystem here
    public WheelRearLeft() {
    	super("WheelLeftRear",Kp,Ki,Kd);
    	setInputRange(0.1,4.9);
    	setOutputRange(-.25,.25);
    	setPercentTolerance(1);
    	getPIDController().setContinuous(false);
    	setSetpoint(2.5);
    	//enable();
    }
    
    public void setSpeed(double speed){
    	drive.set(speed*RobotMap.speedLimit);
    }
    
    public void initDefaultCommand() {
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	SmartDashboard.putNumber("potrl", pot.getVoltage());
    	return pot.getVoltage();
    }
    
    protected void usePIDOutput(double output) {
        motor.set(output);
    }
}
