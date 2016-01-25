package org.usfirst.frc.team3011.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3011.robot.RobotMap;

public class RingLight {

	private static DigitalOutput dLight = new DigitalOutput(RobotMap.ringLight);
	private static double intensity = 0;	
	    
	static void initialize () {
		RingLight.dLight.enablePWM(0.0);
		System.out.println("RingLight initialized");
	}
	    
	static void turnOnLight () {
		if (RingLight.intensity == 0) {
			RingLight.intensity = SmartDashboard.getNumber("RingLight_Intensity");
			System.out.println("ringlight intensity = " + RingLight.intensity);
		}
		RingLight.dLight.updateDutyCycle(RingLight.intensity);	
	}
	
	static void turnOffLight () {
		RingLight.dLight.updateDutyCycle(0);
	}
	
	static void setIntensity(double a) {
		RingLight.dLight.updateDutyCycle(a);
		RingLight.intensity = a;
	}
	    
	static double getIntensity(){
		return RingLight.intensity;
	}
}
