package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "AutonomousV1")

public class AutonomousV1 extends LinearOpMode {

    private illuminutsRobot robot = new illuminutsRobot();

    @Override
    public void runOpMode() {

        robot.init(hardwareMap, telemetry);

        robot.iconHold();

        robot.winchUp();
        sleep(1000);
        robot.winchStop();

        while(!isStarted()) {
            robot.winchHold();
        }

        robot.message("started");

        robot.winchDrop();

        // Clear Lander
        robot.motorStraight(0.3,500);

        // Retract the grabber
        robot.winchRetract();
    }
}


