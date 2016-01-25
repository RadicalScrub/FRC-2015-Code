package org.usfirst.frc.team3011.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3011.robot.RobotMap;
import edu.wpi.first.wpilibj.Relay;

public class Conveyor extends Subsystem{
	Relay xRelay = new Relay (RobotMap.conveyor);
	public void initDefaultCommand() {
		xRelay.set(Relay.Value.kOff);
    }

	/**
	 * @param direction - Sets the direction of the motor. -1 = in, 1 = out, 0 = stopped
	 */
	public void setDirection (int direction){
		if (direction > 0){
			xRelay.set(Relay.Value.kForward);
		}
		if (direction < 0){
			xRelay.set(Relay.Value.kReverse);
		}
		if (direction == 0){
			xRelay.set(Relay.Value.kOff);
		}
	}
}
