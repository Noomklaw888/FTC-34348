package org.firstinspires.ftc.teamcode;
//Controls
//Left joystick: Forwards and Backwards
//Right joystick: Left and Right
//Trigger: 
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Flippertigibbet")
public class Flippertigibbet extends LinearOpMode {

  // Declare your motor variables
  private DcMotor leftDrive = null;
  private DcMotor rightDrive = null;
  private DcMotor rollerDrive = null;
  private DcMotor flipperDrive = null;
  @Override
  public void runOpMode() {

    // Connect variables to the names you set in "Configure Robot"
    leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
    rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
    rollerDrive = hardwareMap.get(DcMotor.class, "intake");
    flipperDrive = hardwareMap.get(DcMotor.class, "flipper");
    // Reverse one motor so both spin forward together
    leftDrive.setDirection(DcMotor.Direction.FORWARD);
    rightDrive.setDirection(DcMotor.Direction.REVERSE);

    telemetry.addData("Status", "Initialized! Press Play.");
    telemetry.update();

    // Wait for you to press PLAY on the Driver Station
    waitForStart();

    // Loop continuously while the OpMode is running
    while (opModeIsActive()) {

      // FTC joysticks read negative (-1.0) when pushed UP, so we flip the sign (-)
      double updownstick  = -gamepad1.left_stick_y;
      double rightleftstick = gamepad1.right_stick_x;
      
      double leftPower = updownstick + rightleftstick;
      double rightPower = updownstick - rightleftstick;
      
// Roller bar control logic
      double barPower = 0.0;

      if (gamepad1.left_trigger > 0.5) {
        barPower = 1.0;  // Spin forward while holding D-Pad Up
      } else if (gamepad1.left_bumper) {
        barPower = -1.0; // Spin reverse while holding D-Pad Down
      } else {
        barPower = 0.0;  // Stop when no buttons are held
      }


      
      
      // Send power to the motors
      leftDrive.setPower(leftPower);
      rightDrive.setPower(rightPower);
      rollerDrive.setPower(barPower);
      

      // Show power values on the Driver Station screen
      telemetry.addData("Left Motor Power", leftPower);
      telemetry.addData("Right Motor Power", rightPower);
      telemetry.addData("Left Stick", updownstick);
      telemetry.addData("Right Stick", rightleftstick);
      telemetry.addData("Roller Bar Power", barPower);
      telemetry.update();
    }
  }
}
