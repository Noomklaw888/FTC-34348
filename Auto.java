package org.firstinspires.ftc.teamcode;


import java.util.concurrent.TimeUnit;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous


public class Auto extends LinearOpMode {
private DcMotor leftDrive = null;
  private DcMotor rightDrive = null;
  private DcMotor rollerDrive = null;
  public void runOpMode() {

    // Connect variables to the names you set in "Configure Robot"
    leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
    rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
    rollerDrive = hardwareMap.get(DcMotor.class, "intake");
    
    forward(4);
    left(3.5);//this is 90 degrees
    forward(9.5);
    right(4);//also a 90
    forward(9);
    right(5);
    forward(6);
    in();
    right(3.5);
    out();
    
    
    
    
}
//defining the functions
private void forward(double ticks) {
  leftDrive.setPower(1);
  rightDrive.setPower(-1); 
  rollerDrive.setPower(0);
  try {
      TimeUnit.MILLISECONDS.sleep((int)(ticks * 100.0));
  } catch (InterruptedException e) {
    e.printStackTrace();}
  leftDrive.setPower(0);
  rightDrive.setPower(0);  
  rollerDrive.setPower(0); 
  try {
      TimeUnit.MILLISECONDS.sleep(100);
  } catch (InterruptedException e) {
    e.printStackTrace();}
}
private void backward(double ticks) {
  leftDrive.setPower(-1);
  rightDrive.setPower(1); 
  rollerDrive.setPower(0);
  try {
      TimeUnit.MILLISECONDS.sleep((int)(ticks * 100.0));
  } catch (InterruptedException e) {
    e.printStackTrace();}
  leftDrive.setPower(0);
  rightDrive.setPower(0);  
  rollerDrive.setPower(0); 
  try {
      TimeUnit.MILLISECONDS.sleep(1000);
  } catch (InterruptedException e) {
    e.printStackTrace();}
}
private void right(double ticks) {
  leftDrive.setPower(1);
  rightDrive.setPower(1); 
  rollerDrive.setPower(0);
  try {
      TimeUnit.MILLISECONDS.sleep((int)(ticks * 100.0));
  } catch (InterruptedException e) {
    e.printStackTrace();}
  leftDrive.setPower(0);
  rightDrive.setPower(0);  
  rollerDrive.setPower(0); 
  try {
      TimeUnit.MILLISECONDS.sleep(1000);
  } catch (InterruptedException e) {
    e.printStackTrace();}
}
private void left(double ticks) {
  leftDrive.setPower(-1);
  rightDrive.setPower(-1); 
  rollerDrive.setPower(0);
  try {
      TimeUnit.MILLISECONDS.sleep((int)(ticks * 100.0));
  } catch (InterruptedException e) {
    e.printStackTrace();}
  leftDrive.setPower(0);
  rightDrive.setPower(0);  
  rollerDrive.setPower(0);
  try {
      TimeUnit.MILLISECONDS.sleep(1000);
  } catch (InterruptedException e) {
    e.printStackTrace();}
}
private void in() {
  rollerDrive.setPower(-1);
  leftDrive.setPower(0.5);
  rightDrive.setPower(-0.5);
  try {
      TimeUnit.MILLISECONDS.sleep(1000);
  } catch (InterruptedException e) {
    e.printStackTrace();}
  rollerDrive.setPower(0);
  leftDrive.setPower(0);
  rightDrive.setPower(0);
  try {
      TimeUnit.MILLISECONDS.sleep(1000);
  } catch (InterruptedException e) {
    e.printStackTrace();}
}
private void out() {
  rollerDrive.setPower(1);
  try {
      TimeUnit.MILLISECONDS.sleep(500);
  } catch (InterruptedException e) {
    e.printStackTrace();}
  rollerDrive.setPower(0);
  try {
      TimeUnit.MILLISECONDS.sleep(1000);
  } catch (InterruptedException e) {
    e.printStackTrace();}
}
}
