package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous (name = "A: DirectClaimPark")

public class A_autonomusDirectClaimPark extends LinearOpMode {

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

        //drive to depot
        robot.motorStraight(0.4, 2600);

        robot.iconDrop();

        //turn to face crater
        robot.motorTurn(0.4, 300);

        //drive to crater
        robot.motorStraight(-0.4,4500);
    }
}
