# 🎬 Movie Ticket Booking & Review System  
### Java Console Application

---

## 📌 Project Overview
The **Movie Ticket Booking & Review System** is a Java-based console application that simulates a real-world movie ticket booking platform.  
It allows users to browse movies, book tickets, manage wallet-based payments, and submit movie reviews using a menu-driven interface.

This project is built using **Core Java** and focuses on applying **Object-Oriented Programming (OOP)** concepts, secure payment handling, and robust input validation.

---

## ❓ Problem Statement
Many beginner-level Java projects lack real-world functionality such as secure payments, structured workflow, and proper validation.

This project addresses those gaps by implementing an **end-to-end movie booking system** using only Core Java and console-based interaction, closely simulating real-life application behavior.

---

## ✨ Features
- Browse available movies with ticket prices
- Select show timings (Morning / Afternoon / Evening)
- Seat availability tracking and validation
- Ticket booking with booking summary
- Wallet balance management and top-up
- Secure MPIN-based payment verification
- Multiple payment methods:
  - Bank Account
  - UPI
  - Credit Card
- Payment blocking after three incorrect MPIN attempts
- Booking history storage and display
- Movie review and rating system
- Center-aligned and colored console output
- Robust exception handling for invalid inputs

---

## 🛠 Technologies Used

### Programming Language
- Java

### Core Concepts
- Object-Oriented Programming (OOP)
- Interfaces & Abstract Classes
- Inheritance & Polymorphism
- Encapsulation
- Exception Handling
- Arrays
- Static Members

### Tools & Environment
- IntelliJ IDEA / Eclipse
- Java Console

---

## 📁 Project Structure


### Class Responsibilities

**Main.java**
- Entry point of the application
- Displays welcome banners and menus
- Handles navigation and global exception handling
- Coordinates communication between modules

**Movie.java**
- Represents movie details (name, price, available seats)
- Uses encapsulation for controlled data access
- Supports seat availability validation

**Booking.java**
- Stores booking details such as movie, show time, seats, and cost
- Generates booking summary

**TicketBooking.java**
- Controls the complete booking workflow
- Handles movie selection, show timing, and seat validation
- Integrates booking and payment modules

**PaymentMethod.java**
- Interface defining common payment operations
- Ensures consistency across Bank, UPI, and Credit Card payments

**PaymentProcess.java**
- Manages wallet balance and top-up
- Handles MPIN creation and verification
- Blocks payment after three incorrect MPIN attempts
- Processes secure transactions

**BookingHistory.java**
- Stores and displays past booking records
- Improves transparency and user experience

**Reviews.java**
- Stores movie ratings and review comments
- Maintains structured review data

**MovieReviewSystem.java**
- Menu-driven review management system
- Allows adding and viewing movie reviews

---

## 🔄 How the Project Works
1. User selects a movie from the available list  
2. User chooses a show time  
3. User enters the number of seats  
4. Booking summary is displayed  
5. Payment method is verified or linked  
6. MPIN is set and validated  
7. Wallet balance is checked and topped up if needed  
8. Secure payment is processed  
9. Seats are booked successfully  
10. Booking history is saved  
11. User can add or view movie reviews  

---

## ▶ How to Run the Project
1. Install **Java JDK 8 or above**
2. Clone or download this repository
3. Open the project in IntelliJ IDEA or Eclipse
4. Ensure all `.java` files are in the same package
5. Compile and run `Main.java`

---

## 👤 Author
**Shivaji Raj**  
- GitHub: https://github.com/Shivajiraj

---

## 📌 Note
This project is intended for **learning and demonstration purposes**, focusing on Core Java and console-based application design.
