package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

//
// Starting Point /  Template for all AUTONOMOUS Modes
//

@Autonomous(name = "AutonomousV1")

public class AutonomousV1 extends LinearOpMode {

    private illuminutsRobot robot = new illuminutsRobot();

    @Override
    public void runOpMode() {

        robot.init(hardwareMap, telemetry);

        // Keep hold of the minion
        robot.iconHold();

        // Open the grabber
        robot.winchUp();
        sleep(1000);
        robot.winchStop();

        // grab and hold until PLAY is presses
        while(!isStarted()) {
            robot.winchHold();
        }

        // Land
        robot.winchDrop();

        // Clear Lander
        robot.motorStraight(0.3,500);

        // Retract the grabber
        robot.winchRetract();
    }
}


