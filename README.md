# 🏛️ Java Modern Banking Application

A professional, modular Java Swing banking application featuring a modern dark-themed UI, custom graphics, secure credential handling, and SQLite database persistence. Built as a college project to demonstrate Object-Oriented Programming (OOP) and database management in Java.

---

## ✨ Features

* **Modern UI:** A sleek, fully dark-themed user interface with custom-painted shadow borders and gradients.
* **Database Persistance:** Uses SQLite (`bank.db`) to safely store credentials and balances locally.
* **Auto-Registration:** Smooth login flow. If a username does not exist, the app automatically creates a new account for you instantly!
* **Banking Operations:** Support for Deposit, Withdrawal, and viewing Account Balance in Indian Rupees (₹).
* **Modular Codebase:** Clean separation of UI, Database logic, and custom Java Swing Components.

---

## 🛠️ Technology Stack

* **Language:** Java (JDK 8 or higher)
* **GUI Framework:** Java Swing (with custom 2D Graphics)
* **Database:** SQLite
* **Driver:** JDBC (`sqlite-jdbc.jar`)

---

## 🚀 How to Run the Project (Step-by-Step)

There are three ways you can run this project locally on your machine.

### Prerequisites
Make sure you have [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/) installed on your computer.

### Method 1: The Easiest Way (Windows Only)
If you are on Windows, we have included a script that compiles and runs everything for you automatically.

1. Clone or Download the repository as a ZIP and extract it.
2. Open the extracted project folder.
3. Double-click the **`run.bat`** file.
4. The application will compile the code, link the database, and launch automatically!

### Method 2: Running the Executable JAR
If you want to run the pre-packaged application without compiling code:

1. Go to the **Releases** tab on the right side of this GitHub page and download the `BankingApp.jar` file.
2. **Important:** Download the repository ZIP file as well, because the `.jar` needs to connect to the database driver. 
3. Place `BankingApp.jar` inside the main project folder (it must be next to the `lib` folder!).
4. Double-click `BankingApp.jar` to open the app.

### Method 3: Using an IDE (VS Code, Eclipse, IntelliJ)
1. Open the project folder in your preferred IDE.
2. **Crucial Step:** Ensure that the `lib/sqlite-jdbc.jar` is added to your project's Build Path / Classpath!
3. Locate `BankingApp.java` in the main folder.
4. Click **Run**.

---

## 🔑 How to Log In (Testing the App)

This application has an **Auto-Registration** feature designed to make testing extremely easy! You do not need to hunt for default usernames.

1. Launch the application.
2. On the **Login Screen**, type **ANY** Username you want (e.g., `Admin`, `Student123`).
3. Type **ANY** Password you want.
4. Click **Log In**.

**What happens?**
If the username does not exist in the database, the app automatically creates a brand new account for you with a `₹0.0` balance and logs you in. The next time you open the app, just use those exact same credentials to access your saved balance!

---

## 🔄 User Flow (How the App Works Step-by-Step)

Here is a detailed breakdown of how the application operates from launch to logout:

### 1. Application Launch (`BankingApp.java`)
* The application starts by initializing the SQLite database. If `bank.db` doesn't exist, it is generated automatically alongside the required `users` table.
* The main `JFrame` window is created, centrally positioned, styled with the dark theme (`Theme.java`), and the `LoginScreen` panel is loaded.

### 2. Login & Auto-Registration (`LoginScreen.java`)
* The user is presented with a login form containing username and password fields (`ModernTextField`, `ModernPasswordField`).
* When the user clicks **Log In**, the credentials are submitted to `DatabaseManager.java`.
* **Verification:** The database checks if the username exists. 
   * If **Yes**, it verifies the password.
   * If **No**, it automatically registers a new user with a starting balance of ₹0.0 and immediately logs them in.
* Upon successful authentication, the unique `userId` is saved, and the view switches to the `DashboardScreen`.

### 3. Main Menu (`DashboardScreen.java`)
* This is the central hub. It greets the user and displays a grid of stylish option buttons.
* Options available:
   * **Check Balance**: Navigates to `BalanceScreen`.
   * **Deposit**: Navigates to `TransactionScreen` initialized in 'Deposit' mode.
   * **Withdraw**: Navigates to `TransactionScreen` initialized in 'Withdraw' mode.
   * **Logout**: Clears the session and returns to `LoginScreen`.

### 4. Transactions (`TransactionScreen.java`)
* Depending on whether the user clicked Deposit or Withdraw, the screen adapts its title and button text.
* The user enters an amount to process.
* **Deposit:** Increments the user's balance in the database by the specified amount.
* **Withdrawal:** First checks if the current balance is greater than or equal to the requested amount. If valid, it deducts the amount; otherwise, it shows an "Insufficient funds" warning.
* All queries run via `DatabaseManager` to ensure the local `bank.db` is perfectly up-to-date.

### 5. Checking Balance (`BalanceScreen.java`)
* Queries the database for the currently logged-in user's updated wealth and displays it clearly on screen with a custom currency formatting layout.

### 6. Logout
* Clears all session variables inside the application and returns the user safely to the `LoginScreen.java`, securing their account.

---

## 📁 Project Structure

```text
JAVA/
├── .vscode/               # VS Code workspace settings
├── bin/                   # Compiled .class files (generated automatically)
├── db/                    # Contains bank.db (SQLite Database)
├── lib/                   # Contains sqlite-jdbc.jar (Database Driver)
├── src/                   # Contains all Java source code files
│   ├── BankingApp.java        # Main App entry point
│   ├── DatabaseManager.java   # Contains all SQL queries and logic
│   ├── LoginScreen.java       # Login UI
│   ├── DashboardScreen.java   # Main Menu UI
│   ├── BalanceScreen.java     # Check Balance UI
│   ├── TransactionScreen.java # Deposit/Withdraw UI
│   ├── Theme.java             # Color palettes and styling rules
│   └── (Various custom UI components...)
```
👥 Team Contributions

This project was developed collaboratively, with each member focusing on a key part of the application to ensure a well-structured and fully functional system.

👨‍💻 Anshu — UI & Frontend Development

Responsible for designing the user interface and overall user experience of the application.

Key Contributions:

Developed all major screens using Java Swing:
Login Screen
Dashboard
Balance Screen
Transaction Screen
Designed and implemented a clean dark theme UI
Styled components like buttons, labels, and layouts for better usability
Created and maintained a consistent design system using Theme.java

Summary:
Focused on making the application visually appealing, user-friendly, and easy to navigate.

🗄️ Kunal Sandhu — Database Management

Handled all database-related operations and data storage.

Key Contributions:

Set up and managed the SQLite database (bank.db)
Designed database schema to store:
Usernames
Passwords
Account balances
Integrated SQLite with Java using JDBC
Implemented core database operations in DatabaseManager.java:
User registration (auto-create account)
Login validation
Balance updates
Data retrieval

Summary:
Ensured reliable data storage and smooth interaction between the application and the database.

⚙️ Nikhil Samanta — Application Logic & Functionality

Focused on implementing the core logic and connecting different parts of the application.

Key Contributions:

Implemented banking operations:
Deposit functionality
Withdraw functionality with balance validation
Connected UI actions (buttons/events) with backend logic
Managed navigation between different screens
Controlled the overall application workflow using BankingApp.java

Summary:
Built the core functionality of the app, ensuring everything works correctly and efficiently.

🔗 Final Overview

This project is a complete integration of:

Frontend (UI): Handles user interaction and visual design
Backend (Logic): Manages application behavior and operations
Database: Stores and retrieves user data securely

Together, these components create a smooth and functional banking application.
