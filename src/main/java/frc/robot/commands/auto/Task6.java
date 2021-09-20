package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.Globals;
import frc.robot.Points;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.task.Testing;

import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.Sensor;


public class Task6 extends AutoCommand{

    private final static OmniDrive m_omnidrive = RobotContainer.m_omnidrive;
    private final static Vision m_vision = RobotContainer.m_vision;
    private final static Points m_points = RobotContainer.m_points;
    private final static Sensor m_sensor = RobotContainer.m_sensor;



    

    public Task6() {

        super(
            // new MoveRobot(2, 1, 0, 0, 0.5),
            new InstantCommand(m_vision::itemLook),
            new WaitCommand(2),
            new Testing(),
            new InstantCommand(m_vision::getItem),
            new Pick()



            

        );
    }



}