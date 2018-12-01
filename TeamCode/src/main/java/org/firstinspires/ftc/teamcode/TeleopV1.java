package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleopV1")
public class TeleopV1 extends LinearOpMode {

    private illuminutsRobot robot = new illuminutsRobot();

    private double turnPower = 0;

    @Override
    public void runOpMode() {

        robot.init(hardwareMap, telemetry);

        robot.message("waitForStart()");

        robot.LEDred();
        robot.sleep(1000);
        robot.LEDblue();
        robot.sleep(1000);
        robot.LEDred();
        robot.sleep(1000);
        robot.LEDoff();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            robot.LEDwhite();
            sleep(500);
            robot.LEDoff();
            sleep(500);
            robot.LEDwhite();

            // Drive Control
            turnPower = gamepad1.left_stick_x;

            double drivePower = gamepad1.left_trigger - gamepad1.right_trigger;

            // When NOT Turbo
            if (!gamepad1.left_stick_button) {
                turnPower = Math.pow(turnPower, 3) / 2;
                drivePower = Math.pow(drivePower, 3) / 2;
            }

            robot.motorDrive(drivePower - turnPower , drivePower + turnPower);

            // Winch Control
            if (gamepad1.dpad_up) {
                robot.winchUp();
            }
            else if(gamepad1.dpad_down){
                robot.winchDown();
            }
            else {
                robot.winchStop();
            }

            if(gamepad1.dpad_left) {
                robot.iconDrop();
            }

            if(gamepad1.dpad_right) {
                robot.iconHold();
            }

            // LED Control
            if (gamepad1.x) { robot.LEDblue(); }
            if (gamepad1.a) { robot.LEDgreen(); }
            if (gamepad1.b) { robot.LEDred(); }
            if (gamepad1.y) { robot.LEDoff(); }
        }
    }
}

