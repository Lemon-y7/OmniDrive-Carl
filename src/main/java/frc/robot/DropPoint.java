package frc.robot;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Transform2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DropPoint {

  // Comparator<BoxPair> byDistance = Comparator.comparingDouble(BoxPair::getDistance);

  String[] boxes = new String[] { "RedBox", "BlueBox", "YellowBox", "BlackBox", "GreenBox" };
  String[] items = new String[] { "kitKatDrop", "ballDrop", "chipsDrop", "kitKatDrop", "nissinDrop" };
  ArrayList<BoxPair> boxPair = new ArrayList<>();
  // BoxPair[] boxPair;

  Pose2d Drop1;
  Pose2d Drop2;
  Pose2d RedBox;
  Pose2d GreenBox;
  Pose2d BlueBox;
  Pose2d BlackBox;
  Pose2d YellowBox;
  Pose2d Bin;
  static Points m_points = RobotContainer.m_points;

  public void getBoxes() {
    for (int i = 0; i < boxes.length; i++) {
      // if(m_points.getPoint(boxes[i]).equals(new Pose2d(0, 0, new Rotation2d(0))))
        m_points.updatePoint(boxes[i], Globals.curPose.transformBy(new Transform2d(new Translation2d( SmartDashboard.getNumber(boxes[i] + "x", 0) / 100,
            -SmartDashboard.getNumber(boxes[i] + "y", 0) / 100), new Rotation2d(0)))

      );

    }

    m_points.updatePoint("Bin",Globals.curPose.transformBy(new Transform2d(new Translation2d(

        SmartDashboard.getNumber("Binx", 0) / 100,
        -SmartDashboard.getNumber("Biny", 0) / 100), new Rotation2d(0))
        )
    );


    m_points.updatePoint("BinBack",
        m_points.getPoint("Bin").transformBy(new Transform2d(new Translation2d(0, -0.5), new Rotation2d(0)))

    );

    m_points.updatePoint("BinFront",
      m_points.getPoint("Bin").transformBy(new Transform2d(new Translation2d(0, 0.5), new Rotation2d(0)))

    );

    m_points.updatePoint("BinLeft",
      m_points.getPoint("Bin").transformBy(new Transform2d(new Translation2d(-0.5, 0), new Rotation2d(0)))

    );
    
    m_points.updatePoint("BinRight",
      m_points.getPoint("Bin").transformBy(new Transform2d(new Translation2d(0.5, 0), new Rotation2d(0)))

    );
  
  

    RedBox = m_points.getPoint("RedBox");
    BlueBox = m_points.getPoint("BlueBox");
    BlackBox = m_points.getPoint("BlackBox");
    YellowBox = m_points.getPoint("YellowBox");
    GreenBox = m_points.getPoint("GreenBox");
    Bin = m_points.getPoint("Bin");

  }

  public void generatePair() {
    int x = 0;
    // boxPair = new BoxPair[24];
    for (int i = 0; i < boxes.length; i++) {

      for (int j = i + 1; j < boxes.length; j++) {

        var x1 = m_points.getPoint(boxes[i]).getTranslation().getX();
        var x2 = m_points.getPoint(boxes[j]).getTranslation().getX();
        var y1 = -m_points.getPoint(boxes[i]).getTranslation().getY();
        var y2 = -m_points.getPoint(boxes[j]).getTranslation().getY();

        double dist = Math.sqrt((Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
        if (x1 + x2 == 0 || y1 + y2 == 0)
          break;
        boxPair.add(x, new BoxPair(boxes[i], boxes[j], Math.abs(dist), items[i], items[j]));
        x++;

      }
    }
    // Arrays.sort(boxPair, byDistance);
    boxPair.sort(Comparator.comparingDouble(BoxPair::getDistance));
    SmartDashboard.putString("Item1", boxPair.get(0).item1);
    SmartDashboard.putString("Item2", boxPair.get(0).item2);
  }

  public double getDropAngle(double ang) {
    double dropAngle = Math.toDegrees(ang);
    if (dropAngle > 70 && dropAngle < 110)
      return Math.PI / 2;
    else if (dropAngle < -70 && dropAngle > -110)
      return -Math.PI / 2;
    else if (dropAngle > -20 && dropAngle < 20)
      return 0;
    else
      return Math.toRadians(dropAngle);
  }

  public void generatePose(String posename, Pose2d box1, Pose2d box2) {

    var generatedPose = Globals.curPose.transformBy(
      new Transform2d(
        new Translation2d(
          (box1.getTranslation().getX() + 
          box2.getTranslation().getX()
          )/2,

          (box1.getTranslation().getY() + 
          box2.getTranslation().getY()
          )/2
        ),
        new Rotation2d( getDropAngle( 
          Math.atan(
            (
              box1.getTranslation().getY() - 
              box2.getTranslation().getY()
            )/(
              box1.getTranslation().getX() - 
              box2.getTranslation().getX()
            )
          )))));
    
    m_points.updatePoint(posename, generatedPose.transformBy(  
        new Transform2d(
          new Translation2d(0.1, -0.7),
          new Rotation2d(0)
        )
    ));
    m_points.updatePoint("unadjusted"+posename, generatedPose);
  }

  public void getDropPose() {

    for (int i = 0; i < 2; i++) {
      String str = String.valueOf(i+1);
      Pose2d box1 = m_points.getPoint(boxPair.get(i).box1);
      Pose2d box2 = m_points.getPoint(boxPair.get(i).box2);

      generatePose("Drop" + str, box1, box2);

      Pose2d unadjustedDrop = m_points.getPoint("unadjustedDrop" + str);
      Pose2d adjustedDrop = m_points.getPoint("Drop" + str);
              


      if ((unadjustedDrop.getTranslation().getX() > 0 && unadjustedDrop.getRotation().getDegrees() > 15)
          || (unadjustedDrop.getTranslation().getX() < 0 && unadjustedDrop.getRotation().getDegrees() < -15)) {

        // unadjustedDrop.getRotation().rotateBy(new Rotation2d(Math.PI));
        // adjustedDrop.getRotation().rotateBy(new Rotation2d(Math.PI));

        m_points.updatePoint("unadjustedDrop" + str, new Pose2d(unadjustedDrop.getTranslation(),
            unadjustedDrop.getRotation().rotateBy(new Rotation2d(Math.PI))));

        m_points.updatePoint("Drop" + str,
            new Pose2d(adjustedDrop.getTranslation(), adjustedDrop.getRotation().rotateBy(new Rotation2d(Math.PI))));

        m_points.updatePoint(boxPair.get(i).item1,  new Pose2d(adjustedDrop.getTranslation(), adjustedDrop.getRotation().rotateBy(new Rotation2d(Math.PI))));

        m_points.updatePoint(boxPair.get(i).item2,  new Pose2d(adjustedDrop.getTranslation(), adjustedDrop.getRotation().rotateBy(new Rotation2d(Math.PI))));


        // m_points.updatePoint(boxPair.get(i).box1,

        //     new Pose2d(adjustedDrop.getTranslation(), adjustedDrop.getRotation().rotateBy(new Rotation2d(Math.PI))));

        // m_points.updatePoint(boxPair.get(i).box2,
        //     new Pose2d(adjustedDrop.getTranslation(), adjustedDrop.getRotation().rotateBy(new Rotation2d(Math.PI))));

      }

      m_points.updatePoint(boxPair.get(i).item1, m_points.getPoint("Drop" + str));
      m_points.updatePoint(boxPair.get(i).item2, m_points.getPoint("Drop" + str));
      
          
      setAlignment(boxPair.get(i).box1, boxPair.get(i).box2, "unadjustedDrop" + str);


    }

  }

  public void setAlignment(String box1, String box2, String posename) {

    Transform2d relativePose1 = m_points.getPoint(posename).minus(m_points.getPoint(box1)
        .transformBy(new Transform2d(new Translation2d(0, 0), m_points.getPoint(posename).getRotation())));

    Transform2d relativePose2 = m_points.getPoint(posename).minus(m_points.getPoint(box2)
        .transformBy(new Transform2d(new Translation2d(0, 0), m_points.getPoint(posename).getRotation())));

    if (relativePose1.getTranslation().getX() < relativePose2.getTranslation().getX()) {
      m_points.setAlignment(box1, false);
      m_points.setAlignment(box2, true);
    }

    else {
      m_points.setAlignment(box1, true);
      m_points.setAlignment(box2, false);
    }

    Globals.debug10 = relativePose1.toString();
    Globals.debug9 = relativePose2.toString();
    SmartDashboard.putString("Relativepose1", relativePose1.toString());
    SmartDashboard.putString("Relativepose2", relativePose2.toString());
    SmartDashboard.putString("box1", box1);
    SmartDashboard.putString("box2", box2);
  }

  

}