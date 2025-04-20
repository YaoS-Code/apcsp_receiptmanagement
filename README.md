# APCSP Receipt Management

This project is a Receipt Management System designed to help users manage and analyze receipts efficiently. The system allows users to create, search, and list receipts based on various criteria such as date range, type, and name. It also provides summary statistics for receipts, such as totals, averages, and more.

---

## Features

- **Create Receipts**: Add new receipts with details like date, name, type, description, amount, and tip.
- **Search Receipts**: Search for receipts by name.
- **Filter by Date Range**: List receipts within a specific date range.
- **Filter by Type**: List receipts of a specific type and view summary statistics.
- **View All Receipts**: Display all stored receipts.
- **Sort Receipts**: Sort receipts by date, amount, or reset to the original order.
- **Persistent Storage**: Receipts are saved to a file (`assets/database.txt`) for future use.

---

## Code Overview

### 1. [`Receipt.java`](Receipt.java)
This file defines the `Receipt` class, which represents a single receipt. It includes:

- **Instance Variables**:
  - `receiptNumber`: Unique identifier for each receipt.
  - `receiptDate`, `receiptName`, `receiptType`, `receiptDescription`: Metadata about the receipt.
  - `receiptAmount`, `receiptTaxRate`, `receiptTaxAmount`, `receiptTip`: Financial details.
  - `receiptStatus`: Status of the receipt (e.g., completed, refunded).
  - `receiptList`: A static list to store all receipts.

- **Constructor**:
  - Initializes a new receipt with the provided details.
  - Automatically calculates tax and assigns a unique receipt number.

- **Methods**:
  - `getReceiptTotal()`: Calculates the total amount (amount + tax + tip).
  - `generateTax()`: Computes the tax amount based on the tax rate.
  - `toString()`: Returns a formatted string representation of the receipt.

---

### 2. [`Management.java`](Management.java)
This file contains the main application logic and user interface. It includes:

- **Main Method**:
  - Calls `readFile()` to load receipts from `assets/database.txt`.
  - Starts the application by calling `app()`.

- **Menu System**:
  - `printMenu()`: Displays the main menu options.
  - `userOptions()`: Handles user input and calls the appropriate methods.

- **Receipt Operations**:
  - `createReceipt()`: Prompts the user to input receipt details and saves the receipt to the file.
  - `searchReceipt()`: Searches for a receipt by name and allows returning it.
  - `displayReceipts()`: Lists receipts by type, date range, or all receipts.
  - `sortReceipts()`: Sorts receipts by date, amount, or resets to the original order.

- **File Operations**:
  - `readFile()`: Reads receipts from `assets/database.txt` and recreates `Receipt` objects.
  - `writeToFile()`: Appends a new receipt to `assets/database.txt`.

---

### 3. [`test.py`](test.py)
This file is a utility script for generating sample receipt data. It includes:

- **Random Data Generation**:
  - Generates random receipt details such as date, name, type, amount, tax, and tip.
  - Outputs 30 sample receipts in the same format as `assets/database.txt`.

---

### 4. [`assets/database.txt`](assets/database.txt)
This file serves as the persistent storage for receipts. Each receipt is stored in a structured format, including details like receipt number, date, name, type, amount, tax, tip, and status.

---

## Usage

1. **Run the Application**:
   - Execute the `Management.java` file to start the application.
   - Use the menu options to create, search, filter, sort, and view receipts.

2. **Generate Sample Data**:
   - Run `test.py` to generate sample receipts for testing.

3. **Persistent Storage**:
   - Receipts are automatically saved to `assets/database.txt` and loaded when the application starts.

---

## Repository

[GitHub Repository](https://github.com/YaoS-Code/apcsp_receiptmanagement.git)

---

## Future Improvements

- Add support for exporting receipts to CSV or JSON format.
- Implement a graphical user interface (GUI) for easier interaction.
- Enhance error handling and input validation.
- Add more sorting and filtering options.