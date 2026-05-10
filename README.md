# XYZ Bank Automation Testing Framework 🏦

A professional, enterprise-level test automation framework built for the [XYZ Bank (GlobalSQA)](https://globalsqa.com/angularJs-protractor/BankingProject/#/login) web application.

## 🛠️ Technology Stack
- **Language**: Java 17
- **Web Automation Tool**: Selenium WebDriver 4.x
- **Testing Framework**: TestNG
- **Build Tool**: Maven
- **Driver Management**: WebDriverManager

## 🏗️ Architecture & Design Patterns
- **Page Object Model (POM)**: Strictly implemented to separate test logic from UI locators, ensuring high maintainability and reducing code duplication.
- **Explicit Waits (`WebDriverWait`)**: Fully replaces `Thread.sleep()` to handle dynamic AngularJS elements efficiently and avoid flaky tests.

## 📂 Project Structure
```text
XYZBankAutomation/
├── pom.xml                   # Maven dependencies and build plugins
├── testng.xml                # TestNG suite configuration
└── src/
    ├── main/java/pages/      # Page Object Classes
    │   ├── LoginPage.java
    │   ├── DashboardPage.java
    │   ├── DepositPage.java
    │   ├── WithdrawalPage.java
    │   ├── TransactionsPage.java
    │   ├── ManagerDashboardPage.java
    │   ├── AddCustomerPage.java
    │   ├── OpenAccountPage.java
    │   └── CustomersListPage.java
    └── test/java/tests/      # TestNG Test Classes
        ├── TestBase.java     # WebDriver Setup & Teardown
        ├── LoginTest.java
        ├── BankFunctionalityTest.java
        └── ManagerFunctionalityTest.java
```

## 🚀 How to Run

### Prerequisites
- JDK 17 or higher installed
- Maven installed
- Chrome Browser installed

### Execution
You can run the tests directly using your IDE (IntelliJ IDEA / Eclipse) or via the command line using Maven:

```bash
# Clean and compile the project
mvn clean test-compile

# Run all tests
mvn test
```

## 🧪 Test Coverage
1. **Customer Scenarios**:
   - Customer Login.
   - Deposit functionality and success verification.
   - Withdrawal functionality.
   - Transactions history verification and resetting.
2. **Bank Manager Scenarios**:
   - Manager Login.
   - Adding a new customer.
   - Opening a new account for a customer.
   - Searching and deleting a customer from the database.
