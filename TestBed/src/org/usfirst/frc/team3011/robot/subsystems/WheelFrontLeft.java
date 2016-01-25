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
public class WheelFrontLeft extends PIDSubsystem {
	private static double Kp = -1;
	private static double Ki = 0;
	private static double Kd = 0;
	
	
	private static final double ZERO = 1.758,
			MAX = 3.522,
			MIN = 0.211;
			
	
	public SpeedController drive = new Jaguar(RobotMap.driveLeftFront);
	SpeedController motor = new Jaguar(RobotMap.steerLeftFront);
	AnalogInput pot = new AnalogInput(RobotMap.potLeftFront);
	
    // Initialize your subsystem here
    public WheelFrontLeft() {
    	super("WheelLeftFront",Kp,Ki,Kd);
    	setInputRange(MIN,MAX);
    	setOutputRange(-.25,.25);
    	setPercentTolerance(1);
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
    	SmartDashboard.putNumber("potfl", pot.getVoltage());
    	return pot.getVoltage();
    }
    
    protected void usePIDOutput(double output) {
        motor.set(output);
    }
}
