package frc.robot.commands.auto;

import frc.robot.RobotContainer;
import frc.robot.subsystems.Sensor;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.OmniDrive;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Points;

public class Reset extends AutoCommand {

  private final static OmniDrive m_omnidrive = RobotContainer.m_omnidrive;

  
  public Reset(){
    
    super(

      new MovePose("Pick"),
      new WaitCommand(1),
      new ZeroPose(),
      new InstantCommand(m_omnidrive::resetPose)
    
    );
    

  }
}