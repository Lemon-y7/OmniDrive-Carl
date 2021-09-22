package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Sensor;

public class Sonic extends CommandBase{
    boolean endflag;
   
    private static final Sensor m_sensor = RobotContainer.m_sensor;
    private MoveRobot pcmd = new MoveRobot(1, -0.01, 0, 0, 0.5);
    private MoveRobot ncmd = new MoveRobot(1, 0.01, 0 ,0,0.5);


    public Sonic(){

    }

    @Override
    public void initialize() {
      endflag = false;
    }
    
    @Override
    public void execute() {
  
      if (Math.round(m_sensor.getSonicDistance1()) == 90)
        endflag = true;
      else if (Math.round(m_sensor.getSonicDistance1()) > 90)
        ncmd.schedule();
      else if (Math.round(m_sensor.getSonicDistance1()) < 90)
        pcmd.schedule();

    }
    
    @Override
    public boolean isFinished() {
      return endflag;
    }
  
    @Override
    public void end(boolean interrupted) {

    }
    


}