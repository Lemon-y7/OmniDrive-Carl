package frc.robot.commands.auto.Tasks;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.AutoCommand;
import frc.robot.commands.auto.Pick;
import frc.robot.commands.auto.PickCommands.GripperPick;
import frc.robot.subsystems.Vision;

public class Task8 extends AutoCommand {

  private final static Vision m_vision = RobotContainer.m_vision;
  
  public Task8() {
    /*
        item 0 = chips
            1 = ball
            2 = kitkat
            3 = nissin
        */
    
    super(
      
      new InstantCommand(m_vision::itemLook),
      new WaitCommand(2),
      // new InstantCommand(m_vision::getItem),
      new InstantCommand(() -> Globals.curItem = 2),
      new Pick(),
      new GripperPick(4)
    );

  }

}