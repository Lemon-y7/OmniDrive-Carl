package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.Points;
import frc.robot.RobotContainer;
import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Vision;

public class Test extends AutoCommand{

    private final static OmniDrive m_omnidrive = RobotContainer.m_omnidrive;
    private final static Vision m_vision = RobotContainer.m_vision;
    private final static Points m_points = RobotContainer.m_points;

    

    public Test() {

        super(
                
                new MovePose("Pick"),
                new AlignLeft(),
                new WaitCommand(1),
                new InstantCommand(m_omnidrive::setreferencePose),
                new InstantCommand(m_omnidrive::setreferenceHeading)
                // new MoveTest2()

                // new MapPose("BlackBox", "RedBox", "Drop2"),
                // new MapPose("GreenBox", "YellowBox", "Drop1"),
                // new MovePose("Drop2"),
                // new MovePose("waypoint1"),
                // new MovePose("Drop1"),
                // new MovePose("waypoint1"),
                // new MovePose("Zero")
   

                

        );
    }



}