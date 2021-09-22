package frc.robot.commands.auto.Tasks;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.AutoCommand;
import frc.robot.commands.auto.Pick;
import frc.robot.commands.auto.Sonic;
import frc.robot.commands.auto.Taskpick;
import frc.robot.commands.auto.PickCommands.GripperPick;
import frc.robot.subsystems.Vision;

public class Task5 extends AutoCommand {

  static final Vision m_vision = RobotContainer.m_vision;

  public Task5() {
    super(
      // new Sonic(),
      new InstantCommand(m_vision::itemLook),
      new WaitCommand(2),
      // new Taskpick(),
      // new WaitCommand(2),
      new InstantCommand(m_vision::getItem),
      new WaitCommand(2),
      new Pick(),
      new GripperPick(4)
    );
  }

}