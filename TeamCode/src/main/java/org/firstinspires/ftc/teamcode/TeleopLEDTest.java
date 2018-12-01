package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleopLEDTest")
public class TeleopLEDTest extends LinearOpMode {

    private illuminutsRobot robot = new illuminutsRobot();

    private double turnPower = 0;

    @Override
    public void runOpMode() {

        robot.init(hardwareMap, telemetry);

        // LED Control
        if (gamepad1.x) { robot.LEDblue(); }
        if (gamepad1.a) { robot.LEDgreen(); }
        if (gamepad1.b) { robot.LEDred(); }
        if (gamepad1.y) { robot.LEDoff(); }
    }
}

