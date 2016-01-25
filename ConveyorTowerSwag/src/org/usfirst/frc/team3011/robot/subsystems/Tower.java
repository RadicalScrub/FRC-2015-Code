package org.usfirst.frc.team3011.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Jaguar;
import org.usfirst.frc.team3011.robot.RobotMap;

public class Tower extends Subsystem{
	Jaguar yJag = new Jaguar (RobotMap.liftSystem);
	public void initDefaultCommand() {
		yJag.set(0);
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	public void setSpeed (double speed){
		yJag.set(speed);
	}
}
