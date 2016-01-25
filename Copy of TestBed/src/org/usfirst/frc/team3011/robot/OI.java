package org.usfirst.frc.team3011.robot;


import org.usfirst.frc.team3011.robot.commands.RotateTo;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private Joystick driverPad = new Joystick(0);
	private Button buttonA = new JoystickButton(driverPad,1);
	private Button buttonB = new JoystickButton(driverPad,2);
	
	private double wfr, wfl, wrl, wrr;
	private double wafr,wafl,warl,warr;
	private int direction = 0;
	
	public OI() {
		buttonA.whenPressed(new RotateTo(90));
		buttonB.whenPressed(new RotateTo(0));
	}
	
	public void prepToTurn() {
		double rcw = 1;
		double l = 22;
		double w = 21;
		double r = Math.sqrt(l*l + w*w);
		double a = -rcw*l/r;
		double b = rcw*l/r;
		double c = -rcw*w/r;
		double d = rcw*w/r;	
		
		this.wfr=Math.sqrt(b*b+c*c);
		this.wfl=Math.sqrt(b*b+d*d);
		this.wrl=Math.sqrt(a*a+d*d);
		this.wrr=Math.sqrt(a*a+c*c);
		
		
		this.wafr=Math.atan2(b, c) * 180/ Math.PI;
		this.wafl=Math.atan2(b, d) * 180/ Math.PI;
		this.warl=Math.atan2(a, d) * 180/ Math.PI;
		this.warr=Math.atan2(a, c) * 180/ Math.PI;
		SmartDashboard.putNumber("Front Right Wheel Angle: ", this.wafr);
		SmartDashboard.putNumber("Rear Right Wheel Angle: ", this.warr);
		SmartDashboard.putNumber("Front Left Wheel Angle: ", this.wafl);
		SmartDashboard.putNumber("Rear Left Wheel Angle: ", this.warl);
		// volt/angle = 0.01381111
		// ZERO = 1.758,
		// MAX = 3.722,
		// MIN = 0.011;
		// ie zero - 10 degrees * 0.013811
		if (this.wafr <= 100 && this.wafr >= -100) {
			this.wafr = RobotMap.potRFZero - this.wafr * 0.013811;
		} else if (this.wafr > 100){
			this.wafr = RobotMap.potRFZero - (this.wafr-180) * 0.013811;
			this.wfr = - this.wfr;
		} else  if (this.wafr < -100){
			this.wafr = RobotMap.potRFZero - (this.wafr+180) * 0.013811;
			this.wfr = - this.wfr;
		}
		
		if (this.warr <= 100 && this.warr >= -100) {
			this.warr = RobotMap.potRRZero - this.warr * 0.013811;
		} else if (this.warr > 100){
			this.warr = RobotMap.potRRZero - (this.warr-180) * 0.013811;
			this.wrr = - this.wrr;
		} else  if (this.warr < -100){
			this.warr = RobotMap.potRRZero - (this.warr+180) * 0.013811;
			this.wrr = - this.wrr;
		}
		
		if (this.wafl <= 100 && this.wafl >= -100) {
			this.wafl = RobotMap.potLFZero - this.wafl * 0.013811;
		} else if (this.wafl > 100){
			this.wafl = RobotMap.potLFZero - (this.wafl-180) * 0.013811;
			this.wfl = - this.wfl;
		} else  if (this.wafl < -100){
			this.wafl = RobotMap.potLFZero - (this.wafl+180) * 0.013811;
			this.wfl = - this.wfl;
		}
		
		if (this.warl <= 100 && this.warl >= -100) {
			this.warl = RobotMap.potLRZero - this.warl * 0.013811;
		} else if (this.warl > 100){
			this.warl = RobotMap.potLRZero - (this.warl-180) * 0.013811;
			this.wrl = - this.wrl;
		} else  if (this.warl < -100){
			this.warl = RobotMap.potLRZero - (this.warl+180) * 0.013811;
			this.wrl = - this.wrl;
		}
	}
	
	public void calculateWheels() {
		//returns cc 0 to 180, ccc 0 to -180
		double gyro = Robot.imu.getYaw();
		if (gyro < 0) {
			gyro += 360.0;
		}
		gyro = Math.toRadians(gyro);
		double fwd = -driverPad.getRawAxis(1);
		double str = driverPad.getRawAxis(0);
		double temp = fwd*Math.cos(gyro) + str*Math.sin(gyro);
		str = -fwd*Math.sin(gyro)+str*Math.cos(gyro);
		fwd = temp;
		double rcw = driverPad.getRawAxis(4); 
		
		double l = 22;
		double w = 21;
		double r = Math.sqrt(l*l + w*w);
		double a = str-rcw*l/r;
		double b = str+rcw*l/r;
		double c = fwd-rcw*w/r;
		double d = fwd+rcw*w/r;	
		
		this.wfr=Math.sqrt(b*b+c*c);
		this.wfl=Math.sqrt(b*b+d*d);
		this.wrl=Math.sqrt(a*a+d*d);
		this.wrr=Math.sqrt(a*a+c*c);
		
		double max = Math.max(this.wfr, Math.max(this.wfl, Math.max(this.wrl, this.wrr)));
		if (max > 1.0) {
			this.wfr = this.wfr/max;
			this.wfl = this.wfl/max;
			this.wrl = this.wrl/max;
			this.wrr = this.wrr/max;
		}
		this.wafr=Math.atan2(b, c) * 180/ Math.PI;
		this.wafl=Math.atan2(b, d) * 180/ Math.PI;
		this.warl=Math.atan2(a, d) * 180/ Math.PI;
		this.warr=Math.atan2(a, c) * 180/ Math.PI;
		SmartDashboard.putNumber("Front Right Wheel Angle: ", this.wafr);
		SmartDashboard.putNumber("Rear Right Wheel Angle: ", this.warr);
		SmartDashboard.putNumber("Front Left Wheel Angle: ", this.wafl);
		// volt/angle = 0.01381111
		// ZERO = 1.758,
		// MAX = 3.722,
		// MIN = 0.011;
		// ie zero - 10 degrees * 0.013811
		if (this.wafr <= 100 && this.wafr >= -100) {
			this.wafr = RobotMap.potRFZero - this.wafr * 0.013811;
		} else if (this.wafr > 100){
			this.wafr = RobotMap.potRFZero - (this.wafr-180) * 0.013811;
			this.wfr = - this.wfr;
		} else  if (this.wafr < -100){
			this.wafr = RobotMap.potRFZero - (this.wafr+180) * 0.013811;
			this.wfr = - this.wfr;
		}
		
		if (this.warr <= 100 && this.warr >= -100) {
			this.warr = RobotMap.potRRZero - this.warr * 0.013811;
		} else if (this.warr > 100){
			this.warr = RobotMap.potRRZero - (this.warr-180) * 0.013811;
			this.wrr = - this.wrr;
		} else  if (this.warr < -100){
			this.warr = RobotMap.potRRZero - (this.warr+180) * 0.013811;
			this.wrr = - this.wrr;
		}
		
		if (this.wafl <= 100 && this.wafl >= -100) {
			this.wafl = RobotMap.potLFZero - this.wafl * 0.013811;
		} else if (this.wafl > 100){
			this.wafl = RobotMap.potLFZero - (this.wafl-180) * 0.013811;
			this.wfl = - this.wfl;
		} else  if (this.wafl < -100){
			this.wafl = RobotMap.potLFZero - (this.wafl+180) * 0.013811;
			this.wfl = - this.wfl;
		}
		
		if (this.warl <= 100 && this.warl >= -100) {
			this.warl = RobotMap.potLRZero - this.warl * 0.013811;
		} else if (this.warl > 100){
			this.warl = RobotMap.potLRZero - (this.warl-180) * 0.013811;
			this.wrl = - this.wrl;
		} else  if (this.warl < -100){
			this.warl = RobotMap.potLRZero - (this.warl+180) * 0.013811;
			this.wrl = - this.wrl;
		}
		
	}
	
	public double getWAFR() {
		return this.wafr;
	}
	
	public double getWFR(){
		return this.wfr;
	}
	
	public double getWARR() {
		return this.warr;
	}
	public double getWRR(){
		return this.wrr;
	}
	
	public double getWAFL() {
		return this.wafl;
	}
	public double getWFL(){
		return this.wfl;
	}
	
	public double getWARL() {
		return this.warl;
	}
	public double getWRL(){
		return this.wrl;
	}
	
	//axis 1 is left y, up is neg
	//axis 0 is left x, right is pos
	
	
}

