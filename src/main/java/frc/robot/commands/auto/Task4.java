package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.Points;
import frc.robot.RobotContainer;
import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.Sensor;

public class Task4 extends AutoCommand{

    private final static OmniDrive m_omnidrive = RobotContainer.m_omnidrive;
    private final static Vision m_vision = RobotContainer.m_vision;
    private final static Points m_points = RobotContainer.m_points;
    private final static Sensor m_sensor = RobotContainer.m_sensor;

    

    public Task4() {

        super(
            new MoveRobotSense(1, 5, 0, 0.4, 0.4, () -> m_sensor.getSonicDistance1() < 300), 
            new MoveRobot(1, 0.1, 0.4, 0, 0.4),           
            new MoveRobot(0, 0.4, 0, 0, 0.4),
            new MoveRobot(1, 1, 0, 0, 0.4),
            new MoveRobot(0, -0.4, 0, 0, 0.4),
            new MoveRobot(1, 1, 0, 0, 0.4)
        );
    }



}