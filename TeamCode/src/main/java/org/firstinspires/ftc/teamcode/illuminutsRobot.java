package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class illuminutsRobot {

    private Telemetry       telemetry;

    private DcMotor         motorLeftFront;
    private DcMotor         motorLeftRear;
    private DcMotor         motorRightFront;
    private DcMotor         motorRightRear;

    private Servo           servoWinch;
    private Servo           servoIcon;

    private DigitalChannel  limitSwitch;

    private DigitalChannel  LEDred;
    private DigitalChannel  LEDgreen;
    private DigitalChannel  LEDblue;

    private final static double WINCHSTOP    = 0.5;
    private final static double WINCHLIFT    = 0.9;
    private final static double WINCHDROP    = 0.2;
    private final static double WINCHHOLD    = 0.7;
    private final static double WINCHUP      = 0.2;
    private final static double WINCHDOWN    = 0.9;

    private final static double ICONOPEN     = 0.4;
    private final static double ICONCLOSE    = 0.9;


    public illuminutsRobot() { }

    public void init(HardwareMap h, Telemetry t) {

        HardwareMap hardwareMap = h;
        telemetry       = t;

        motorLeftFront  = hardwareMap.get(DcMotor.class, "motorLeftFront");
        motorLeftRear   = hardwareMap.get(DcMotor.class, "motorLeftRear");
        motorRightFront = hardwareMap.get(DcMotor.class, "motorRightFront");
        motorRightRear  = hardwareMap.get(DcMotor.class, "motorRightRear");

        servoWinch      = hardwareMap.get(Servo.class, "servoWinch");
        servoIcon       = hardwareMap.get(Servo.class, "servoIcon");

        limitSwitch     = hardwareMap.get(DigitalChannel.class, "sensorDigital");
        limitSwitch.setMode(DigitalChannel.Mode.INPUT);

        LEDred          = hardwareMap.get(DigitalChannel.class, "LEDred");
        LEDgreen        = hardwareMap.get(DigitalChannel.class, "LEDgreen");
        LEDblue         = hardwareMap.get(DigitalChannel.class, "LEDblue");

        LEDred.setMode(DigitalChannel.Mode.OUTPUT);
        LEDgreen.setMode(DigitalChannel.Mode.OUTPUT);
        LEDblue.setMode(DigitalChannel.Mode.OUTPUT);
    }

    //
    // Telemetry
    //
    public void message(String line) {
        telemetry.addLine(line);
        telemetry.update();
    }

    //
    // Winch Drive
    //
    public void winchDrive(double power) {
        if (power > WINCHSTOP && !limitSwitch.getState()) {
            servoWinch.setPosition(WINCHHOLD);
        } else {
            servoWinch.setPosition(power);
        }
    }

    public void winchUp() {
        winchDrive(WINCHUP);
    }

    public void winchDown() {
        winchDrive(WINCHDOWN);
    }

    public void winchStop() {
        winchDrive(WINCHSTOP);
    }

    public void winchHold() {
        winchDrive(WINCHHOLD);
    }

    public void winchRetract() {
        while (limitSwitch.getState()) {
            servoWinch.setPosition(WINCHLIFT);
        }
        winchStop();
    }

    private final static long WINCHDROPTIME = 1750;

    public void winchDrop() {
        winchDrive(WINCHDROP);
        sleep(WINCHDROPTIME);
        winchStop();
    }

    public void winchLift() {
        winchDrive(WINCHLIFT);
    }

    //
    // Motor Drive
    //
    public void motorDrive(double left, double right) {
        motorLeftFront.setPower(left);
        motorLeftRear.setPower(left);
        motorRightFront.setPower(-right);
        motorRightRear.setPower(-right);
    }

    public void motorStop() {
        motorDrive(0,0);
    }

    public void motorStraight(double power, int time) {
        motorDrive(power, power);
        sleep(time);
        motorStop();
    }

    public void motorTurn(double power, int time) {
        motorDrive(power, -power);
        sleep(time);
        motorStop();
    }

    //
    // Utils
    //
    public final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    //
    // LED control
    //
    public void LEDoff () {
        LEDred.setState(false);
        LEDgreen.setState(false);
        LEDblue.setState(false);
    }
    public void LEDred () {
        LEDred.setState(true);
        LEDgreen.setState(false);
        LEDblue.setState(false);
    }

    public void LEDgreen () {
        LEDred.setState(false);
        LEDgreen.setState(true);
        LEDblue.setState(false);
    }

    public void LEDblue () {
        LEDred.setState(false);
        LEDgreen.setState(false);
        LEDblue.setState(true);
    }

    public void LEDwhite() {
        LEDred.setState(true);
        LEDgreen.setState(true);
        LEDblue.setState(true);
    }

    //
    // Token Release control
    //
    public void iconDrop(){
        servoIcon.setPosition(ICONOPEN);
        sleep(500);
    }
    public void iconHold(){
        servoIcon.setPosition(ICONCLOSE);
    }
}
