package org.usfirst.frc.team3011.robot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Timer;

public class Tower {
Jaguar upJag;
Timer time;
	public Tower (){
		upJag = new Jaguar(8);
		time = new Timer();
	}

	public void moveUp() {
		// TODO Auto-generated method stub
		upJag.set(.75);
		time.delay(1);
		upJag.set(0);
	}

}
