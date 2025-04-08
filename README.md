# 💠 DEMO UI Test Automation Project for Swag Labs

# Overview
This repository contains a test task designed to demonstrate a UI automation project setup, structure, functionality.
The project based on Selenium, TestNG, and Allure for reporting.

#  🧩 Architecture Overview

The project follows a layered architecture to ensure modularity, scalability, and ease of maintenance. Below are the key components of the architecture:
1. Test Layer

This layer contains all the test cases, written using TestNG. Each test case is structured around the following principles:

    Modularity: Tests are independent and modular to ensure they can be run in isolation.
    Parameterization: Some tests are parameterized to handle different input conditions, allowing flexible execution across environments.
    Thread Safety: The tests are designed to be thread-safe, ensuring reliable execution in parallel test runs. This allows tests to be run concurrently without conflicts or interference between threads.

2. POM - Page object model Architecture

Each web page in the application under test (AUT) is represented by a separate class, called a "Page Object".
This class contains:
- Locators for the elements on that page (e.g., buttons, input fields).
- Methods that represent the actions that can be performed on that page (e.g., "login", "add to cart").

The test cases then interact with the AUT through these Page Object classes, rather than directly with the web elements.


 ## Page Classes:
 Classes like LoginPage, HomePage, and ProductPage.  Each of these represents a specific page in the web application.
Locators:
- Within these page classes, define locators (using By) for the web elements on that page.

For example: 
- private static final By USERNAME_INPUT = By.id("user-name");

Methods: 
- The page classes also contain methods that represent user interactions with the page.

For example, in LoginPage:
- public void doLogIn(String username, String password, boolean pressEnterKey)

## Test Cases:
Test classes (e.g., LoginTests, HomePageTests, ProductTests) use these Page Object methods to interact with the application. 

For example:
- loginPage.doLogIn(prop.getProperty("username"), prop.getProperty("password"), false);

## Benefits of this architecture:

✅ Maintainability: If the UI of the AUT changes, you only need to update the locators or methods in the corresponding Page Object class.  Your test cases remain unchanged.

✅ Readability: Test cases become more readable because they use meaningful method names (e.g., loginPage.doLogIn()) rather than low-level Selenium commands.

✅ Reusability: Page Object methods can be reused across multiple test cases.

✅Reduced Duplication: Common page interactions are encapsulated in Page Objects, avoiding code duplication in test cases.

3. Utilities Layer

   Helper Classes: These provide utility functions for actions such as data generation, API calls, and file handling.
   Test Data Management: Test data is stored in external files (JSON, XML, etc.), ensuring easy updates without code changes. The data is loaded dynamically into the test cases.

4. Configuration Layer
   Environment Configuration: Configuration settings like environment URLs and other dynamic data are defined externally in remote.properties. This allows easy switching between different environments (remote, local) without changing the test code.
   like : Configuration.java, Common.property;
Logging: The project includes logging at various levels (INFO, DEBUG, ERROR) to capture detailed runtime information.

5. Reporting
   Allure Reporting: The project integrates with Allure to generate detailed test execution reports. Allure reports provide insights into test results, including failure logs, step-by-step execution, and detailed logs.



## 🗂 Project Structure
- **`src/main/java`**: Contains the main framework code.
- **`src/test/java`**: Contains test cases written using TestNG.
- **`src/test/resources`**: Holds configuration files (e.g., test data, driver paths, xml).
- **`target/allure-results`**: Stores test execution results for Allure reports.

##  ⚙️ Technologies Used
- **Java 17**
- **Maven** (for build automation)
- **Selenium 4.31.0** (for browser automation.

Why Use WebDriver implementation as Singleton?

Ensures only one instance of WebDriver exists.
Reduces resource consumption.
Prevents SessionNotFoundException from multiple driver instances.
Makes WebDriver management more efficient in parallel execution).

- **TestNG 7.11.0** (for test execution and assertions)
- **SLF4J & Logback** (for logging and debug)
- **AspectJ Weaver** (for AOP-based logging and tracing)
- **Allure 2.21.0** (for test reporting)

## Prerequisites
- Java 17 installed
- Maven installed
- Chrome or Firefox browser installed

## 🛠 Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/bulazhenko/test-Task-Qinshift.git
   cd test-Task-Qinshift
   ```
2. Build the project:
   ```bash
   mvn clean install
   ```

## Running Tests
To execute tests, run:
```bash
mvn test
```

## Generating Allure Reports
After running tests, generate the Allure report using:
```bash
mvn io.qameta.allure:allure-maven:2.10.0:serve
```
This will open an interactive test report in your browser.

## Logging
The framework uses SLF4J with Logback for logging test execution details. Logs can be found under `target/logs/`.

## Continuous Integration
The project is configured for CI/CD with tools like Jenkins and GitLab CI/CD.

## Contribution
1. Fork the repository
2. Create a new branch (`feature-branch`)
3. Commit changes with detailed comment and push (`git push origin feature-branch`)
4. Open a Pull Request

## License
- Owner: Bohdan Bulazhenko
- This project is licensed under the MIT License.
