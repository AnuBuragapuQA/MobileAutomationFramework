Mobile Parallel Automation Framework
Overview
This framework is designed to automate mobile application testing for both Android and iOS platforms, supporting parallel execution across multiple devices or emulators. It leverages Appium, TestNG, and Java for robust, scalable, and maintainable test automation.

Key Features
Supports Android and iOS mobile apps.
Parallel test execution on multiple devices/emulators using TestNG.
Cross-platform compatibility.
Page Object Model (POM) for maintainable code.
Configurable Appium server setup for multiple devices.
Integrated logging with Log4j2.
Generates TestNG reports and optional custom reports.
Supports real devices and emulators/simulators.

Tech Stack
Component
Technology
Test Framework
TestNG
Automation Tool
Appium
Programming Language
Java
Build Tool
Maven/Gradle
Logging
Log4j2
Reporting
TestNG Reports / ExtentReports (optional)
Version Control
Git / GitHub

Project Structure
MobileAutomationFramework/
│
├── src/main/java/
│   ├── base/                 # Base classes (Appium driver setup, config)
│   ├── pages/                # Page Object classes
│   ├── utils/                # Utility classes (helpers, data provider)
│
├── src/test/java/
│   ├── tests/                # Test scripts
│
├── resources/
│   ├── config.properties      # App and device configurations
│   ├── testdata/              # Test data files
│
├── testng.xml                # TestNG suite for parallel execution
├── pom.xml / build. gradle     # Maven/Gradle build file
└── README.md                 # Project documentation

Setup Instructions
1. Prerequisites
Java JDK 17 or above
Maven or Gradle
Android Studio (for emulators)
Node.js & Appium (npm install -g appium)
Connected devices or configured emulators
Git (optional, for version control)

2. Clone Repository
git clone <repository-url>
cd MobileAutomationFramework

3. Install Dependencies
If using Maven:
mvn clean install

4. Configure Devices
Update config.properties with your device details:
deviceName1=emulator-5554
deviceName2=emulator-5556
platformName=Android
appPath=src/main/resources/General-Store.apk
systemPort1=8200
systemPort2=8201

5. Running Tests
Single device execution:
mvn test -DsuiteXmlFile=testng.xml
Parallel execution: Ensure multiple devices/emulators are running and use the same command.



6.Logging & Reporting
Log4j2 is integrated for logging.
TestNG reports are generated automatically after execution.
You can add ExtentReports for HTML reporting if needed.

7 Extent Report Integration
ExtentReportManager   
This class is responsible for:  
Initializing Extent Report  
Setting framework details (name, author, environment)  
Managing thread-safe test instances for parallel execution

8TestNG Listener 
Creates test entries automatically
Logs PASS / FAIL / SKIP status
Attaches screenshots on failure
Works safely in parallel execution

Author
Annapurna Buragapu
QA Engineer

