package org.usfirst.frc.team3011.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
import org.usfirst.frc.team3011.robot.RobotMap;

public class WheelEncoder extends Subsystem {
	private static Encoder distEncoder = new Encoder (RobotMap.wheelEncoder1, RobotMap.wheelEncoder2);
	
	public void initDefaultCommand() {
	}
	
	public double getDistance() {
		return distEncoder.getDistance()*RobotMap.wheelConstant;
	}
	
	public void resetDistance() {
		distEncoder.reset();
	}
}