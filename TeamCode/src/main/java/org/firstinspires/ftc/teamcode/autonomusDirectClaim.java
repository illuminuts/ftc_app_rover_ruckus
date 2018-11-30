package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

//
// Starting Point /  Template for all AUTONOMOUS Modes
//

@Autonomous(name = "AutonomousDirectClaim")

public class autonomusDirectClaim extends LinearOpMode {

    private illuminutsRobot robot = new illuminutsRobot();

    @Override
    public void runOpMode() {

        robot.init(hardwareMap, telemetry);

        robot.iconHold();

        robot.winchUp();
        sleep(1000);
        robot.winchStop();

        while (!isStarted()) {
            robot.winchHold();
        }

        robot.message("started");

        robot.winchDrop();

        // Clear Lander
        robot.motorStraight(0.3, 500);

        // Retract the grabber
        robot.winchRetract();

        //drive to depot
        robot.motorStraight(0.4, 2000);

        robot.iconDrop();
    }
}
