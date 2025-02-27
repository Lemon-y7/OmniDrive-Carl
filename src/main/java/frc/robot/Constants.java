/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants
{
    /**
     * Motor Constants
     */
    public static final int TITAN_ID        = 42; 
    public static final int MOTOR_NUM       = 3;
    public static final int SERVO1          = 9;
    public static final int SERVO2          = 8;
    public static final int SERVO3          = 7;
    public static final int SERVO4          = 6;
    public static final int SERVO_C         = 0;

    /**
     * Sensors
     */
    public static final int SHARP1           = 0;
    public static final int SHARP2           = 1;
    public static final int SHARP3           = 2;
    public static final int SONIC_TRIGG1     = 1;
    public static final int SONIC_ECHO1      = 0;
    // public static final int SONIC_TRIGG2     = 9;
    // public static final int SONIC_ECHO2      = 8;
    
    //Wheels
    public static final double KWHEELDIAMETER = 0.1;  //wheel diameter
    public static final double KENCODERCNTPR = 1440;  //Count per output shaft rev
    public static final double KENCODERDISTPERPULSE = (KWHEELDIAMETER*Math.PI)/KENCODERCNTPR;

    //PIDs
    public static final int PID_NUM = 3;
    public static final double PID_DT = 0.005;
    public static final boolean PID_THREAD = true;

    //Arm
    public static final double ARM1 = 0.24;
    public static final double ARM2 = 0.325;

    public static final String[] obstacles = {"Bin", "unadjustedDrop1", "unadjustedDrop2" ,"Bin2"};
    public static final int gridsize = 100;


    


}
