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
	
	
	private static double
			max,
			min;
			
	
	public SpeedController drive = new Jaguar(RobotMap.driveLeftFront);
	SpeedController motor = new Jaguar(RobotMap.steerLeftFront);
	AnalogInput pot = new AnalogInput(RobotMap.potLeftFront);
	
    // Initialize your subsystem here
    public WheelFrontLeft() {
    	super("WheelLeftFront",Kp,Ki,Kd);
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
    	SmartDashboard.putNumber("potfl", pot.getVoltage());
    	return pot.getVoltage();
    }
    
    protected void usePIDOutput(double output) {
        motor.set(output);
    }
}
