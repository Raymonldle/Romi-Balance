// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.RomiDrivetrain;
import frc.robot.subsystems.RomiGyro;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class Balance extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final RomiDrivetrain m_drivebase;
  private final RomiGyro m_RomiGyro;
  PIDController m_PIDController = new PIDController(0, 0, 0);
  private boolean isDetected;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Balance(RomiDrivetrain m_drivebase, RomiGyro m_RomiGyro) {
    this.m_drivebase = m_drivebase;
    this.m_RomiGyro = m_RomiGyro;
  
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivebase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      m_drivebase.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      if(isDetected){
        m_drivebase.arcadeDrive(m_PIDController.calculate(m_RomiGyro.getAngleY(),0),m_PIDController.calculate(m_RomiGyro.getAngleZ(),0));
      }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      m_drivebase.arcadeDrive(0 , 0);
  } 

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (m_RomiGyro.getAngleY() && m_RomiGyro.getAngleZ() >= 0);
  }
}
