package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "REV_3", group = "LinearOpMode")
public class REV_3 extends LinearOpMode {

    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor intakeDrive = null;
    private DcMotor flyDrive = null;
    private Servo pservo = null;
    private CRServo feedo = null;
    
        @Override
    public void runOpMode() {

        // Connect variables to the names you set in "Configure Robot"
        leftDrive   = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive  = hardwareMap.get(DcMotor.class, "right_drive");
        intakeDrive = hardwareMap.get(DcMotor.class, "intake");
        flyDrive    = hardwareMap.get(DcMotor.class, "flywheel");
        pservo      = hardwareMap.get(Servo.class, "pservo");
        feedo       = hardwareMap.get(CRServo.class, "feedo");

        // Reverse one motor so both spin forward together
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized! Press Play.");
        telemetry.update();

        // Wait for start
        waitForStart();

        // Loop continuously while the OpMode is running
        pservo.setPosition(0.45);
        telemetry.log().add("Entering opModeIsactive");
        while (opModeIsActive()) {

            // Arcade drive logic
            double updownstick   = -gamepad1.left_stick_y;
            double rightleftstick = gamepad1.right_stick_x;

            double leftPower  = updownstick + rightleftstick;
            double rightPower = updownstick - rightleftstick;

            // Intake control logic
            double intakepower = 0.0;
            if (gamepad1.left_trigger > 0.5) {
                intakepower = -0.7;
            } else if (gamepad1.left_bumper) {
                intakepower = -0.71;
            } else {
                intakepower = 0.0;
            }

            // Flywheel control logic
            double flypower = 0.0;
            if (gamepad1.right_trigger > 0.5) {
                flypower = 1.0;
            } else if (gamepad1.right_bumper) {
                flypower = -1.0;
            } else {
                flypower = 0.0;
            }

            // Pollen pusher servo logic
            if (gamepad1.y) {
                pservo.setPosition(0.45);
                telemetry.addData("Command", "Y");

            } else if (gamepad1.a) {
                pservo.setPosition(pservo.getPosition() - 0.1);
                telemetry.addData("Command", "A");
            }
            //feedo logic
            
            if (gamepad1.dpadUpWasPressed()) {
                feedo.setPower(1.0);
                telemetry.addData("Command", "Up");

            } else if (gamepad1.dpadDownWasPressed()) {
                feedo.setPower(-1.0);
                telemetry.addData("Command", "Down");
            } else {
                feedo.setPower(0.0);
                telemetry.addData("Command", "None");
            }

            // Send power to the motors
            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);
            intakeDrive.setPower(intakepower);
            flyDrive.setPower(flypower);

            // Show telemetry on Driver Station
            telemetry.addData("Left Motor Power", leftPower);
            telemetry.addData("Right Motor Power", rightPower);
            telemetry.addData("Flywheel Power", flypower);
            telemetry.addData("Intake Power", intakepower);
            telemetry.addData("Pollen Pusher Position", pservo.getPosition());
            telemetry.addData("Feeder Servo Power", feedo.getPower());
            telemetry.update();
        }
    }
}
