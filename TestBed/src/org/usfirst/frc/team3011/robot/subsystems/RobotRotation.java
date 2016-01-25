package org.usfirst.frc.team3011.robot.subsystems;

import org.usfirst.frc.team3011.robot.Robot;
import org.usfirst.frc.team3011.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RobotRotation extends PIDSubsystem {

	private static double Kp = 1;
	private static double Ki = 0;
	private static double Kd = 0;
	
    // Initialize your subsystem here
    public RobotRotation() {
    	super("RobotRotation",Kp,Ki,Kd);
    	setInputRange(0,359);
    	setOutputRange(-.15,.15);
    	setAbsoluteTolerance(1);
    	
    }
    
    public void initDefaultCommand() {
    	
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	double gyro = Robot.imu.getYaw();
    	
    	if (gyro < 0) {
			gyro += 360.0;
		}
    	return gyro;
    }
    
    protected void usePIDOutput(double output) {
    	Robot.wheelFL.drive.set(output);
    	Robot.wheelFR.drive.set(-output);
    	Robot.wheelRR.drive.set(-output);
    	Robot.wheelRL.drive.set(output);
    }
}
