# Autonomous EV3 Forklift Robot

A team robotics project developed using **LEGO Mindstorms EV3**, **Java**, and the **leJOS** robotics framework. The project involved designing and programming an autonomous forklift-style robot capable of following a route, detecting and avoiding obstacles, and transporting pallet-style LEGO objects using a motorised lifting mechanism.

## Overview

The objective of the project was to design and develop an autonomous mobile robot capable of navigating a marked route while interacting with objects in its environment.

The robot combines a differential-drive chassis with a motorised lifting mechanism and multiple EV3 sensors. A colour sensor provides feedback for route following, an ultrasonic sensor detects obstacles, and a touch sensor provides an emergency-stop mechanism.

The software was developed in Java using the **leJOS EV3 API**, which provides interfaces for controlling EV3 motors, reading sensor data, performing differential-drive navigation, and implementing behaviour-based robotic systems.

The project was completed as a **team project**, with development responsibilities distributed across different aspects of the robot including navigation, object handling, obstacle detection, behaviour control, testing, calibration, and system integration.

## 🎥 Project Demo

Watch the Autonomous EV3 Forklift Robot in action, demonstrating the completed physical robot and its autonomous functionality:

[![Watch the Autonomous EV3 Forklift Robot Demo](https://img.youtube.com/vi/5zZOCwuKrnA/hqdefault.jpg)](https://youtube.com/shorts/5zZOCwuKrnA)

**Click the image above to watch the robot demonstration on YouTube.**

## Key Features

- **Autonomous line following** using an EV3 colour sensor to detect and remain on a marked route.
- **Obstacle detection and avoidance** using an ultrasonic sensor to identify nearby objects and trigger navigation manoeuvres.
- **Forklift-style object handling** using a dedicated motorised lifting mechanism.
- **Object pickup and placement functionality** combining chassis movement with operation of the lifting mechanism.
- **Route reacquisition** after navigation and obstacle-avoidance manoeuvres using colour-sensor feedback.
- **Behaviour-based control** using the leJOS subsumption architecture and `Arbitrator`.
- **Emergency-stop functionality** using the EV3 touch sensor or Escape button to stop the drive and lift motors.
- **Sensor-driven movement behaviour** using reflected-light measurements from the colour sensor.
- **Component-level testing** for the lifting mechanism, line-following functionality, and ultrasonic navigation.

## Technologies

| Technology | Purpose |
| --- | --- |
| Java | Core programming language |
| LEGO Mindstorms EV3 | Robotics hardware platform |
| leJOS | Java framework and hardware API for EV3 |
| leJOS `MovePilot` | Differential-drive movement and navigation |
| leJOS Subsumption API | Behaviour-based robot control |
| EV3 Colour Sensor | Line and surface detection |
| EV3 Ultrasonic Sensor | Distance measurement and obstacle detection |
| EV3 Touch Sensor | Physical emergency-stop input |
| EV3 Large Motors | Left and right drivetrain |
| EV3 Medium Motor | Forklift/lifting mechanism |

## Hardware Configuration

The robot was developed using the following EV3 hardware configuration:

| Component | Port |
| --- | --- |
| Ultrasonic Sensor | S1 |
| Colour Sensor | S2 |
| Touch Sensor | S3 |
| Left Drive Motor | A |
| Right Drive Motor | B |
| Lift Motor | C |

The drivetrain uses two independently controlled EV3 large motors to create a differential-drive chassis.

In the main robot configuration, the wheel diameter is configured as **56 mm** with an axle length of **108 mm**.

## System Design

The software is divided into multiple Java classes responsible for different parts of the robot's functionality. This modular structure allows sensing, navigation, object handling, and high-level behaviours to be developed and tested independently before being integrated into the complete system.

### `Driver.java`

Acts as the main entry point for the robot and integrates the different hardware and software components.

Its responsibilities include:

- configuring the left and right drive motors;
- defining the wheel and chassis configuration;
- initialising the `MovePilot` navigation system;
- initialising the ultrasonic, colour, and touch sensors;
- configuring the lifting motor;
- creating the robot's navigation and behaviour objects;
- coordinating the initial object-handling sequence; and
- starting the leJOS behaviour arbitrator.

### `FollowLine.java`

Implements the robot's line-following functionality using reflected-light measurements from the EV3 colour sensor.

The robot uses sensor readings to determine whether it remains aligned with the marked route. When the expected surface is no longer detected, corrective movement and rotation can be performed to locate and reacquire the route.

### `UltrasonicObjectAvoidance.java`

Contains functionality associated with ultrasonic obstacle detection and navigation.

The ultrasonic sensor measures the distance between the robot and nearby objects. The class contains routines used to respond to detected obstacles and perform movements intended to navigate around them.

Colour-sensor feedback is also used to help the robot locate the marked route again following navigation manoeuvres.

### `Backup.java`

Implements a leJOS `Behavior` used by the robot's behaviour-based control system.

The behaviour monitors ultrasonic sensor readings and can take control when an object is detected within the configured distance threshold. It coordinates obstacle-navigation functionality with chassis movement and operation of the lifting mechanism.

### `ArmOperator.java`

Encapsulates control of the robot's forklift mechanism.

The class provides dedicated:

- `lift()` functionality; and
- `lower()` functionality.

These methods control the EV3 medium motor responsible for operating the lifting mechanism.

Separating this functionality into its own class keeps mechanical object-handling operations independent from the navigation logic.

### `EmergencyStop.java`

Implements an emergency-stop behaviour for the robot.

The robot can respond to:

- activation of the EV3 touch sensor; or
- the Escape button on the EV3 brick.

When triggered, the system stops the drivetrain and lifting motor, providing a simple safety mechanism during testing and operation.

### `Light.java` and `Dark.java`

These classes provide sensor-driven behaviours based on reflected-light measurements from the EV3 colour sensor.

The behaviours allow movement characteristics such as linear speed to be adjusted according to the surface detected by the robot.

## Autonomous Workflow

At a high level, the robot's intended autonomous workflow consists of:

1. Initialising the drivetrain, sensors, lifting motor, and behaviour controllers.
2. Using the lifting mechanism and chassis movement to interact with a pallet-style LEGO object.
3. Navigating along the marked route using colour-sensor feedback.
4. Continuously monitoring the surrounding environment using the ultrasonic sensor.
5. Detecting objects or obstacles within the robot's path.
6. Executing navigation manoeuvres in response to detected obstacles.
7. Using the colour sensor to locate and reacquire the marked route.
8. Continuing navigation while transporting or positioning the object.
9. Operating the lifting mechanism to raise or lower the object as required.

The completed system therefore combines **environmental sensing, autonomous navigation, mechanical actuation, and behaviour-based control** within a single EV3 robotic platform.

## Repository Structure

```text
EV3RoboticsProject/
│
├── README.md
└── leJOSProject/
    ├── ArmOperator.java
    ├── ArmOperatorTest.java
    ├── Backup.java
    ├── Dark.java
    ├── Driver.java
    ├── EmergencyStop.java
    ├── FollowLine.java
    ├── FollowLineTester.java
    ├── Light.java
    ├── UltrasonicObjectAvoidance.java
    └── UltrasonicObjectAvoidanceTest.java
```

The repository contains the integrated robot implementation together with smaller test programs used to evaluate individual components during development.

## Running the Project

### Requirements

Running the complete project requires compatible LEGO Mindstorms EV3 hardware and an environment configured for leJOS development.

The principal requirements are:

- LEGO Mindstorms EV3 brick
- EV3 large motors
- EV3 medium motor
- EV3 ultrasonic sensor
- EV3 colour sensor
- EV3 touch sensor
- Java development environment
- leJOS EV3 libraries and runtime

The physical robot should be connected according to the hardware port configuration described above.

### Main Program

The primary robot configuration and execution flow are defined in:

```text
leJOSProject/Driver.java
```

The Java project must be compiled and deployed to a **leJOS-enabled EV3 brick** using an appropriately configured EV3/leJOS development environment.

> **Note:** Movement distances, rotation angles, motor speeds, sensor thresholds, and line-detection ranges were calibrated for the physical robot and environment used during development. Different robot configurations, surfaces, lighting conditions, wheel dimensions, or sensor positions may require recalibration.

## Testing

The repository contains dedicated programs used to test individual robot components and behaviours during development.

### `ArmOperatorTest.java`

Used to test operation of the motorised lifting mechanism independently from the complete autonomous system.

### `FollowLineTester.java`

Supports isolated testing and development of the colour-sensor-based line-following functionality.

### `UltrasonicObjectAvoidanceTest.java`

Supports testing of ultrasonic object detection and associated navigation behaviour.

Testing individual components separately helped identify hardware, sensor, and movement issues before integrating the different subsystems into the complete robot.

## Engineering Concepts Demonstrated

The project provided practical experience with several software engineering and robotics concepts, including:

- Object-oriented programming in Java
- Autonomous robotics
- Sensor integration
- Sensor calibration
- Behaviour-based robotics
- Subsumption architecture
- Differential-drive navigation
- Obstacle detection
- Reactive navigation
- Motor and actuator control
- Hardware/software integration
- Modular software design
- Component-level testing
- Debugging software on physical hardware
- Team-based software development
- Integration of independently developed components

A significant aspect of the project involved translating software logic into reliable physical behaviour. Unlike a purely software-based system, robot performance was affected by factors such as sensor positioning, lighting conditions, motor behaviour, movement accuracy, and the physical construction of the robot.

## Team Project

This project was developed collaboratively as part of a **university team robotics project**.

Development responsibilities were distributed among team members across areas including:

- robot navigation;
- line-following behaviour;
- obstacle detection and avoidance;
- forklift/object-handling functionality;
- behaviour-based control;
- hardware configuration;
- testing and calibration;
- debugging;
- system integration; and
- project documentation.

The final system required the independently developed hardware and software components to operate together as a complete autonomous robot.

## Project Context

This repository contains the implementation of an academic robotics project developed using **LEGO Mindstorms EV3 and leJOS**.

The project focused on applying Java programming, object-oriented software design, robotics, sensor integration, and autonomous navigation concepts to a physical system.

The resulting robot demonstrates how software can integrate sensor input, navigation algorithms, motor control, and mechanical actuation to interact autonomously with a physical environment.

## Status

This repository represents the completed academic team project and is maintained as a portfolio project demonstrating experience with **Java, autonomous robotics, object-oriented programming, sensor integration, behaviour-based control, and hardware/software integration**.
