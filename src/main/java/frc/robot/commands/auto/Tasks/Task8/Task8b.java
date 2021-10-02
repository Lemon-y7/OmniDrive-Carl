package frc.robot.commands.auto.Tasks.Task8;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.AutoCommand;
import frc.robot.commands.auto.Pick;
import frc.robot.subsystems.Vision;

public class Task8b extends AutoCommand {

  private final static Vision m_vision = RobotContainer.m_vision;

  public Task8b() {
    
    super(
      
      new InstantCommand(m_vision::itemLook),
      new WaitCommand(2),
      new InstantCommand(() -> Globals.curItem = 1),
      new Pick()
    );

  }

}