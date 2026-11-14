package frc.robot;

import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Meters;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ElevatorSubsystem;
import yams.motorcontrollers.SmartMotorControllerCommandRegistry;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
/**
 * The RobotContainer.java of the WPILib 2027 Command v2 Robot template, with the ElevatorSubsystem
 * added by YAMG. Replace your project's RobotContainer.java with it.
 *
 * <p>This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  
  // Added by YAMG.
  private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();

  private final CommandXboxController controller = new CommandXboxController(0);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // When no other command is using the elevator, hold the elevator where it starts.
    elevatorSubsystem.setDefaultCommand(
      elevatorSubsystem.set(0)
    );

    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link org.wpilib.command2.button.CommandGenericHID}'s
   * subclasses for {@link CommandGamepad Gamepad} gamepads or {@link
   * org.wpilib.command2.button.CommandJoystick Flight joysticks}.
   */
  private void configureBindings() {
    // Added by YAMG, in place of the template's example on the right face button.
    // Go to where the elevator starts while the bottom (A) face button is held, and halfway between its limits while the right (B) one is.
    controller.a()
      .whileTrue(elevatorSubsystem.run(Meters.of(0.0)).until(elevatorSubsystem.near(Meters.of(0.0), Inches.of(0.5))));
    controller
      .y()
      .whileTrue(elevatorSubsystem.run(Meters.of(0.5)).until(elevatorSubsystem.near(Meters.of(0.0), Inches.of(0.5))));
    // Drive the elevator with a duty cycle while the left (X) or top (Y) face button is held.
    controller.b().whileTrue(elevatorSubsystem.set(1));
    controller.x().whileTrue(elevatorSubsystem.set(-1));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    // return Autos.exampleAuto(exampleSubsystem);
    return null;
  }
}
