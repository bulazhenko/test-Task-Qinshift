# 💠 DEMO UI Test Automation Project for Swag Labs

## 📋 Table of Contents
- [Overview](#overview)
- [Architecture](#-architecture-overview)
- [Design Patterns](#-design-patterns)
- [Project Structure](#-project-structure)
- [Technologies](#-technologies-used)
- [Prerequisites](#prerequisites)
- [Installation](#-installation)
- [Running Tests](#running-tests)
- [Reports](#generating-allure-reports)
- [Logging](#logging)
- [CI/CD](#continuous-integration)
- [Contributing](#contribution)
- [License](#license)

## Overview
This repository contains a test task designed to demonstrate a UI automation project setup, structure, and functionality. The project is built using Selenium, TestNG, and Allure for comprehensive test reporting.

## 🧩 Architecture Overview

### 1. Test Layer
- **TestNG Framework**: All test cases are implemented using TestNG
- **Key Features**:
  - Modular and independent test cases
  - Parameterized tests for flexible execution
  - Thread-safe design for parallel execution
  - Comprehensive test coverage
  - Data-driven testing support
  - Test grouping and prioritization

### 2. Page Object Model (POM) Architecture
- **Page Classes**: Each web page is represented by a dedicated class
  - `LoginPage`
  - `HomePage`
  - `ProductPage`
  - etc.

- **Locators**: Defined using Selenium's `By` class with explicit wait strategies
  ```java
  private static final By USERNAME_INPUT = By.id("user-name");
  ```

- **Methods**: Encapsulate user interactions with fluent interface pattern
  ```java
  public LoginPage doLogIn(String username, String password, boolean pressEnterKey)
  ```

#### Benefits of POM:
- ✅ **Maintainability**: UI changes only require updates in Page Objects
- ✅ **Readability**: Tests use meaningful method names
- ✅ **Reusability**: Page Object methods are reusable across tests
- ✅ **Reduced Duplication**: Common interactions are centralized
- ✅ **Test Stability**: Centralized element location management
- ✅ **Code Organization**: Clear separation of concerns

### 3. Utilities Layer
- **Helper Classes**: Utility functions for:
  - Data generation using Builder pattern
  - API interactions with REST Assured
  - File handling with Apache Commons IO
- **Test Data Management**: External data storage (JSON, XML)
- **Configuration Management**: Property file handling
- **Custom Waits**: Explicit wait implementations

### 4. Configuration Layer
- **Environment Configuration**: Externalized in `remote.properties`
- **Dynamic Configuration**: Supports multiple environments
- **Logging**: Comprehensive logging using SLF4J & Logback
- **Aspect-Oriented Programming**: Cross-cutting concerns management

### 5. Reporting
- **Allure Reports**: Detailed test execution reports
- **Features**:
  - Test result visualization
  - Step-by-step execution details
  - Failure analysis
  - Historical trends
  - Custom attachments
  - Environment information

## 🎯 Design Patterns

### 1. Singleton Pattern
- **Purpose**: WebDriver instance management
- **Implementation**: Thread-safe singleton with double-checked locking
- **Benefits**:
  - Single instance across test execution
  - Resource optimization
  - Prevention of SessionNotFoundException
  - Efficient parallel execution

### 2. Factory Pattern
- **Purpose**: WebDriver creation and management
- **Implementation**: Factory classes for different browsers
- **Benefits**:
  - Flexible browser selection
  - Encapsulated creation logic
  - Easy extension for new browsers

### 3. Builder Pattern
- **Purpose**: Test data object creation
- **Implementation**: Fluent interface for object construction
- **Benefits**:
  - Clear object construction
  - Optional parameter handling
  - Immutable objects

### 4. Strategy Pattern
- **Purpose**: Different wait strategies
- **Implementation**: Interface-based wait implementations
- **Benefits**:
  - Flexible wait handling
  - Easy strategy switching
  - Test-specific wait implementations

### 5. Facade Pattern
- **Purpose**: Simplified API for complex subsystems
- **Implementation**: High-level interfaces for common operations
- **Benefits**:
  - Simplified test code
  - Reduced complexity
  - Better maintainability

## 🗂 Project Structure
```
project-root/
├── src/
│   ├── main/java/        # Framework code
│   │   ├── core/         # Core framework components
│   │   ├── pages/        # Page Object classes
│   │   ├── utils/        # Utility classes
│   │   └── config/       # Configuration classes
│   ├── test/java/        # TestNG test cases
│   └── test/resources/   # Configuration files
└── target/
    └── allure-results/   # Test execution results
```

## ⚙️ Technologies Used
- **Java 17**: Core programming language
- **Maven**: Build automation and dependency management
- **Selenium 4.31.0**: Browser automation
- **TestNG 7.11.0**: Test execution framework
- **SLF4J & Logback**: Logging framework
- **AspectJ Weaver**: AOP-based logging
- **Allure 2.21.0**: Test reporting
- **REST Assured**: API testing
- **Apache Commons**: Utility libraries
- **Jackson**: JSON processing

### WebDriver Implementation
- Singleton pattern for WebDriver management
- Thread-safe implementation
- Custom wait strategies
- Browser factory pattern
- Benefits:
  - Single instance management
  - Resource optimization
  - Prevention of SessionNotFoundException
  - Efficient parallel execution
  - Flexible browser selection
  - Customizable wait strategies

## Prerequisites
- Java 17
- Maven
- Chrome or Firefox browser
- Allure command-line tool

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
Execute tests using:
```bash
mvn test
```

For specific test groups:
```bash
mvn test -Dgroups=smoke
```

For parallel execution:
```bash
mvn test -DthreadCount=4
```

## Generating Allure Reports
Generate and view reports:
```bash
mvn io.qameta.allure:allure-maven:2.10.0:serve
```

## Logging
- Framework uses SLF4J with Logback
- Logs location: `target/logs/`
- Multiple log levels (INFO, DEBUG, ERROR)
- AOP-based logging for test methods
- Custom log appenders
- Log rotation configuration

## Continuous Integration
- Jenkins CI/CD integration
- Automated test execution
- Report generation
- Email notifications
- Test result trend analysis
- Parallel test execution

## Contribution
1. Fork the repository
2. Create a feature branch (`git checkout -b feature-branch`)
3. Commit changes (`git commit -m 'Detailed description'`)
4. Push to branch (`git push origin feature-branch`)
5. Open a Pull Request

## License
- Owner: Bohdan Bulazhenko
- License: MIT License