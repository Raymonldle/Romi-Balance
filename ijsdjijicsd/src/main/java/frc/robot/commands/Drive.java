// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RomiDrivetrain;

/** An example command that uses an example subsystem. */
public class Drive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final RomiDrivetrain m_drivebase;
  private final XboxController m_xboxController; 

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Drive(RomiDrivetrain m_drivebase, XboxController m_xboxController) {
    this.m_drivebase = m_drivebase;
    this.m_xboxController = m_xboxController;
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
    m_drivebase.arcadeDrive(m_xboxController.getLeftY(), m_xboxController.getRightX());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivebase.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
