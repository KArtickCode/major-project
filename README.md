<h1 align="center">💰 E-Wallet System</h1>

<p align="center">
A scalable <b>Microservices-based E-Wallet Application</b> built using Spring Boot with Apache Kafka for event-driven communication between services.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot-Microservices-green?style=for-the-badge&logo=springboot"/>
  <img src="https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=java"/>
  <img src="https://img.shields.io/badge/Apache%20Kafka-Event%20Driven-black?style=for-the-badge&logo=apachekafka"/>
  <img src="https://img.shields.io/badge/MySQL-Database-blue?style=for-the-badge&logo=mysql"/>
  <img src="https://img.shields.io/badge/Maven-Build-red?style=for-the-badge&logo=apachemaven"/>
</p>

---

## ✨ Overview

The **E-Wallet System** is a distributed backend application built using **microservices architecture**, designed for user management, wallet operations, and transaction processing with **Kafka-based asynchronous communication**.

---

## 🚀 Microservices Architecture

### 👤 User Service
- Handles user registration and management  
- Stores user details (name, email, phone)  
- Provides data to other services  

### 🔄 Transaction Service
- Handles money transfer requests  
- Creates transaction records  
- Publishes events to Kafka  

### 💰 Wallet Service
- Manages wallet balance  
- Consumes Kafka events  
- Updates balance automatically  

---

## 📡 Event-Driven Communication (Kafka)

- Apache Kafka is used for asynchronous messaging  
- Producer Services:
  - User Service  
  - Transaction Service  
- Consumer Service:
  - Wallet Service  
- Ensures loose coupling and high scalability  

---

## 🔄 System Flow

User Service → Transaction Service → Kafka → Wallet Service


---

## 🧠 Tech Stack

- ☕ Java 17  
- 🌱 Spring Boot (Microservices)  
- 📡 Apache Kafka  
- 🗄️ MySQL (per service)  
- 🔧 Maven  
- 🧩 REST APIs  

---

## 📁 Project Structure

User Service
├── Controller
├── Service
├── Repository
├── DTO
└── Models

Transaction Service
├── Controller
├── Service
├── Repository
├── DTO
└── Models

Wallet Service
├── Service
├── Repository
├── Models
└── Kafka Consumer


---

## 🔗 API Endpoints

### 👤 User Service
- `POST /users` → Create User  

### 🔄 Transaction Service
- `POST /transactions` → Create Transaction  

### 💰 Wallet Service
- Kafka-based internal processing  

---

## 🔐 Key Concepts Used

- Microservices Architecture  
- Event-Driven Design  
- Apache Kafka Producer–Consumer  
- DTO Pattern  
- Service Layer Architecture  
- Distributed System Communication  

---

## 🚀 Future Enhancements

- API Gateway integration  
- JWT Authentication  
- Redis caching for wallet balance  
- Docker & Kubernetes deployment  
- Payment gateway integration  

---

## 👨‍💻 Author

- GitHub: https://github.com/KArtickCode  

---

## ⭐ Support

If you like this project, give it a ⭐ on GitHub!
