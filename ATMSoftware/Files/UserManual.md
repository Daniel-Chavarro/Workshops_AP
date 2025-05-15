# ATM System User Manual

## Introduction

The ATM System is a Java application that simulates an Automated Teller Machine. It allows users to perform basic banking operations such as checking account balance, depositing money, and withdrawing money.

## Getting Started

### System Requirements

- Java 13 or higher
- Maven 3.6 or higher

### Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Run the following command:
   ```
   mvn clean compile exec:java -Dexec.mainClass="atmSoftware.controller.AplATM"
   ```

## Using the ATM

### Login

1. Enter your card number in the "Card Number" field
2. Enter your PIN in the "PIN" field
3. Click the "Login" button
4. If the credentials are correct, you will be logged in and taken to the main menu
5. If the credentials are incorrect, you will see an error message
6. After 3 failed attempts, your card will be blocked

### Main Menu

From the main menu, you can:

1. Deposit money
2. Withdraw money
3. Check your account balance
4. Logout

### Deposit Money

1. Click the "Deposit" button in the main menu
2. Enter the amount you want to deposit
3. Click the "Confirm Deposit" button
4. If the deposit is successful, you will see a confirmation message
5. Click the "Back to Menu" button to return to the main menu

### Withdraw Money

1. Click the "Withdraw" button in the main menu
2. Enter the amount you want to withdraw
3. Click the "Confirm Withdrawal" button
4. If the withdrawal is successful, you will see a confirmation message
5. Click the "Back to Menu" button to return to the main menu

### Check Balance

1. Click the "Check Balance" button in the main menu
2. Your current account balance will be displayed
3. Click the "Back to Menu" button to return to the main menu

### Logout

1. Click the "Logout" button at any time to end your session
2. You will be returned to the login screen

## Security Features

- Your card will be blocked after 3 failed login attempts
- You must authenticate before performing any transaction
- The system validates that you have sufficient funds before allowing withdrawals
- The system validates that the ATM has sufficient cash before allowing withdrawals

## Test Accounts

The system is initialized with several test accounts. The card numbers and PINs are printed to the console when the application starts. You can use these credentials to log in and test the system.

## Troubleshooting

### Card Blocked

If your card is blocked, you will need to contact customer service to unblock it. In the test environment, you can restart the application to reset the blocked status.

### Invalid Input

If you enter an invalid input (e.g., non-numeric values for amounts), you will see an error message. Make sure to enter valid numeric values.

### Insufficient Funds

If you try to withdraw more money than you have in your account, you will see an error message. Check your balance and try again with a smaller amount.

### ATM Cash Limit

If you try to withdraw more money than the ATM has available, you will see an error message. Try again with a smaller amount.

## Support

For any issues or questions, please contact the development team.
