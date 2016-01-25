 package org.usfirst.frc.team3011.robot;

import org.usfirst.frc.team3011.robot.commands.LiftCommand;
import org.usfirst.frc.team3011.robot.commands.AutoLiftUp;
import org.usfirst.frc.team3011.robot.commands.LiftDown;
import org.usfirst.frc.team3011.robot.commands.MoveIn;
import org.usfirst.frc.team3011.robot.commands.MoveOut;
import org.usfirst.frc.team3011.robot.commands.LiftUp;
import org.usfirst.frc.team3011.robot.commands.RotateTo;
import org.usfirst.frc.team3011.robot.commands.TurnRed;
import org.usfirst.frc.team3011.robot.commands.TurnGreen;
import org.usfirst.frc.team3011.robot.commands.LiftMiddle;

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
	private Button buttonA = new JoystickButton(driverPad,1);			//A moves lift down
	private Button buttonB = new JoystickButton(driverPad,2);			//B does *NOTHING*
	private Button buttonX = new JoystickButton(driverPad,3);			//X moves lift up
	private Button buttonY = new JoystickButton(driverPad,4);			//Y does *NOTHING*
	private Button buttonL1 = new JoystickButton(driverPad,5);			//L1 moves conveyor in
	private Button buttonR1 = new JoystickButton(driverPad,6);			//R1 moves conveyor out
	private Button buttonBack = new JoystickButton(driverPad, 7);	//Back "Rotates to 90"
	private Button buttonStart = new JoystickButton(driverPad, 8);		//Start "Rotates to 0"
	
	
	private Joystick driverPad2 = new Joystick(1);
	private Button buttonA2 = new JoystickButton(driverPad2,1);			//A makes light Green
	private Button buttonB2 = new JoystickButton(driverPad2,2);			//B makes light Red
	private Button buttonX2 = new JoystickButton(driverPad2,3);
	private Button buttonY2 = new JoystickButton(driverPad2,4);
	private Button buttonL12 = new JoystickButton(driverPad2,5);
	private Button buttonR12 = new JoystickButton(driverPad2,6);
	private Button buttonStart2 = new JoystickButton (driverPad2, 8);
	
	private double wfr, wfl, wrl, wrr;
	private double wafr,wafl,warl,warr;
	private int dirfr = 0,
	            dirfl = 0,
	            dirrl = 0,
	            dirrr = 0;
	private double ratio = 0.01381111;
	
	public OI() {
		//buttonA.whenPressed(new RotateTo(90));
		//buttonB.whenPressed(new RotateTo(0));
		
		//buttonStart.whenPressed (new RotateTo(25));
		//buttonBack.whenPressed (new RotateTo(115));
		
		//buttonA.whileHeld(new LiftDown());
		//buttonX.whileHeld(new LiftUp());
		//buttonL1.whileHeld(new MoveIn());
		//buttonR1.whileHeld(new MoveOut());
		
		buttonX2.whenPressed (new LiftCommand(0.5,1.5,false));
		buttonX2.whenPressed(new TurnRed());
		buttonY2.whenPressed(new TurnGreen());
		buttonA2.whileHeld(new LiftDown());
		buttonA2.whenPressed(new TurnRed());
		buttonB2.whenPressed(new TurnRed());
		buttonB2.whileHeld(new LiftUp());
		buttonL12.whileHeld(new MoveIn());
		buttonR12.whileHeld(new MoveOut());
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
		
		if (this.wafr <= 175 && this.wafr >= -175) {
			this.wafr = RobotMap.potRFZero - this.wafr * 0.013811;
		} else if (this.wafr > 175){
			this.wafr = RobotMap.potRFZero - (this.wafr-180) * 0.013811;
			this.wfr = - this.wfr;
		} else  if (this.wafr < -175){
			this.wafr = RobotMap.potRFZero - (this.wafr+180) * 0.013811;
			this.wfr = - this.wfr;
		}
		
		if (this.warr <= 175 && this.warr >= -175) {
			this.warr = RobotMap.potRRZero - this.warr * 0.013811;
		} else if (this.warr > 175){
			this.warr = RobotMap.potRRZero - (this.warr-180) * 0.013811;
			this.wrr = - this.wrr;
		} else  if (this.warr < -175){
			this.warr = RobotMap.potRRZero - (this.warr+180) * 0.013811;
			this.wrr = - this.wrr;
		}
		
		if (this.wafl <= 175 && this.wafl >= -175) {
			this.wafl = RobotMap.potLFZero - this.wafl * 0.013811;
		} else if (this.wafl > 175){
			this.wafl = RobotMap.potLFZero - (this.wafl-180) * 0.013811;
			this.wfl = - this.wfl;
		} else  if (this.wafl < -175){
			this.wafl = RobotMap.potLFZero - (this.wafl+180) * 0.013811;
			this.wfl = - this.wfl;
		}
		
		if (this.warl <= 175 && this.warl >= -175) {
			this.warl = RobotMap.potLRZero - this.warl * 0.013811;
		} else if (this.warl > 175){
			this.warl = RobotMap.potLRZero - (this.warl-180) * 0.013811;
			this.wrl = - this.wrl;
		} else  if (this.warl < -175){
			this.warl = RobotMap.potLRZero - (this.warl+180) * 0.013811;
			this.wrl = - this.wrl;
		}
	}
	
	public void calculateWheels() {
		SmartDashboard.putNumber("y-axis", -driverPad.getRawAxis(1));
		SmartDashboard.putNumber("x-axis", driverPad.getRawAxis(0));
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
		
		SmartDashboard.putNumber("max", max);
		
		if (max > 1.0) {
			this.wfr = this.wfr/max;
			this.wfl = this.wfl/max;
			this.wrl = this.wrl/max;
			this.wrr = this.wrr/max;
		}
		
		
		//setting the angle for steering in degrees
		this.wafr=Math.atan2(b, c) * 180/ Math.PI;
		this.wafl=Math.atan2(b, d) * 180/ Math.PI;
		this.warl=Math.atan2(a, d) * 180/ Math.PI;
		this.warr=Math.atan2(a, c) * 180/ Math.PI;
		
		
		//setting the wheel rotation direction for each wheel
		if (this.dirfr == 0 && max > 0.05) {
		    if (this.wafr > - 90 && this.wafr < 90) {
		        this.dirfr = 1;
		    } else {
		        this.dirfr = -1;
		    }
		} else if (max < 0.05) {
		    this.dirfr = 0;
		    this.wafr = 2.5;
		} else if (this.dirfr == 1 && (this.wafr < -175 || this.wafr > 175)) {
		    this.dirfr = -1;
		} else if (this.dirfr == -1 && (this.wafr > -5 && this.wafr < 5)) {
		    this.dirfr = 1;
		}
		
		if (this.dirfl == 0 && max > 0.05) {
		    if (this.wafl > - 90 && this.wafl < 90) {
		        this.dirfl = 1;
		    } else {
		        this.dirfl = -1;
		    }
		} else if (max < 0.05) {
		    this.dirfl = 0;
		    this.wafl = 2.5;
		} else if (this.dirfl == 1 && (this.wafl < -175 || this.wafl > 175)) {
		    this.dirfl = -1;
		} else if (this.dirfl == -1 && (this.wafl > -5 && this.wafl < 5)) {
		    this.dirfl = 1;
		}
		
		if (this.dirrl == 0 && max > 0.05) {
		    if (this.warl > - 90 && this.warl < 90) {
		        this.dirrl = 1;
		    } else {
		        this.dirrl = -1;
		    }
		} else if (max < 0.05) {
		    this.dirrl = 0;
		    this.warl = 2.5;
		} else if (this.dirrl == 1 && (this.warl < -175 || this.warl > 175)) {
		    this.dirrl = -1;
		} else if (this.dirrl == -1 && (this.warl > -5 && this.warl < 5)) {
		    this.dirrl = 1;
		}
		
		if (this.dirrr == 0 && max > 0.05) {
		    if (this.warr > - 90 && this.warr < 90) {
		        this.dirrr = 1;
		    } else {
		        this.dirrr = -1;
		    }
		} else if (max < 0.05) {
		    this.dirrr = 0;
		    this.warr = 2.5;
		} else if (this.dirrr == 1 && (this.warr < -175 || this.warr > 175)) {
		    this.dirrr = -1;
		} else if (this.dirrr == -1 && (this.warr > -5 && this.warr < 5)) {
		    this.dirrr = 1;
		}
		
		
		
		//post information to the dashboard
		SmartDashboard.putNumber("Front Right Wheel Angle: ", this.wafr);
		SmartDashboard.putNumber("Rear Right Wheel Angle: ", this.warr);
		SmartDashboard.putNumber("Front Left Wheel Angle: ", this.wafl);
		SmartDashboard.putNumber("Rear Left Wheel Angle: ", this.warl);
		
		
		//now deal with wheels that are moving
		if (this.dirfr == 1) {
		    this.wafr = 2.5 - this.wafr * ratio;
		} else if (this.dirfr == -1 && this.wafr < 0) {
		    this.wafr = 2.5 - (this.wafr + 180) * ratio;
		    this.wfr = -this.wfr;
		} else if (this.dirfr == -1 && this.wafr >= 0) {
		    this.wafr = 2.5 - (this.wafr - 180) * ratio;
		    this.wfr = -this.wfr;
		}
		
		if (this.dirfl == 1) {
		    this.wafl = 2.5 - this.wafl * ratio;
		} else if (this.dirfl == -1 && this.wafl < 0) {
		    this.wafl = 2.5 - (this.wafl + 180) * ratio;
		    this.wfl = -this.wfl;
		} else if (this.dirfl == -1 && this.wafl >= 0) {
		    this.wafl = 2.5 - (this.wafl - 180) * ratio;
		    this.wfl = -this.wrl;
		}
		
		if (this.dirrl == 1) {
		    this.warl = 2.5 - this.warl * ratio;
		} else if (this.dirrl == -1 && this.warl < 0) {
		    this.warl = 2.5 - (this.warl + 180) * ratio;
		    this.wrl = -this.wrl;
		} else if (this.dirrl == -1 && this.warl >= 0) {
		    this.warl = 2.5 - (this.warl - 180) * ratio;
		    this.wrl = -this.wrl;
		}
		
		if (this.dirrr == 1) {
		    this.warr = 2.5 - this.warr * ratio;
		} else if (this.dirrr == -1 && this.warr < 0) {
		    this.warr = 2.5 - (this.warr + 180) * ratio;
		    this.wrr = -this.wrr;
		} else if (this.dirrr == -1 && this.warr >= 0) {
		    this.warr = 2.5 - (this.warr - 180) * ratio;
		    this.wrr = -this.wrr;
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

