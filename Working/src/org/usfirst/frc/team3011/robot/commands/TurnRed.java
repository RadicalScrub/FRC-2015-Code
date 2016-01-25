package org.usfirst.frc.team3011.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team3011.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnRed extends Command {

    public TurnRed() {
    	requires(Robot.lightStrip);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.lightStrip.setRGB (255,0,0);
    	
    }

    protected boolean isFinished() {       
    	return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

