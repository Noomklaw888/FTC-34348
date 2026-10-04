package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "REV_1", group = "LinearOpMode")
public class REV_1 extends LinearOpMode {

    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor intakeDrive = null;
    private DcMotor flyDrive = null;
    private Servo pservo = null;

    @Override
    public void runOpMode() {

        // Connect variables to the names you set in "Configure Robot"
        leftDrive   = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive  = hardwareMap.get(DcMotor.class, "right_drive");
        intakeDrive = hardwareMap.get(DcMotor.class, "intake");
        flyDrive    = hardwareMap.get(DcMotor.class, "flywheel");
        pservo      = hardwareMap.get(Servo.class, "pservo");

        // Reverse one motor so both spin forward together
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized! Press Play.");
        telemetry.update();

        // Wait for start
        waitForStart();

        // Loop continuously while the OpMode is running
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
                intakepower = 1.0;
            } else if (gamepad1.left_bumper) {
                intakepower = -1.0;
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
                telemetry.log().add("set position 1.0");
                pservo.setPosition(0.3);
            } else if (gamepad1.x || gamepad1.b) {
                pservo.setPosition(0.0);
                telemetry.log().add("set position 0.0");

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
            telemetry.addData("Servo Position", pservo.getPosition());
            telemetry.update();
        }
    }
}
