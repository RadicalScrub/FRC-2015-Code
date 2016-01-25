package org.usfirst.frc.team3011.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team3011.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnGreen extends Command {

    public TurnGreen() {
    	requires(Robot.lightStrip);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.lightStrip.setRGB (0,255,0);
    	
    }

    protected boolean isFinished() {        
    	return false;       
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
