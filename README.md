# 💰 E-Wallet Microservices System

A scalable **microservices-based E-Wallet application** built using Spring Boot, designed for user management, wallet balance handling, and transaction processing using **event-driven architecture with Apache Kafka**.

---

## 🚀 Features

### 👤 User Service
- Create and manage users  
- Store user details (name, email, phone)  
- Provides user data to other microservices  

---

### 🔄 Transaction Service
- Handles money transfer between users  
- Creates transaction records  
- Publishes transaction events to Kafka  
- Tracks transaction status  

---

### 💰 Wallet Service
- Maintains user wallet balance  
- Updates balance based on transaction events  
- Consumes Kafka messages asynchronously  
- Ensures data consistency across services  

---

## 🧠 Architecture

This project follows **Microservices Architecture + Event-Driven Design**

User Service → Kafka → Wallet Service


Transaction Service → Kafka → Wallet Service


Each service is independent and communicates via Kafka events.

---

## 📡 Event Streaming (Kafka)

- Apache Kafka is used for asynchronous communication  
- Producer Services:
  - User Service  
  - Transaction Service  
- Consumer Service:
  - Wallet Service  
- Ensures loose coupling and high scalability  

---

## 🛠️ Tech Stack

- Backend: Spring Boot (Microservices)  
- Messaging: Apache Kafka  
- Architecture: Microservices + Event Driven  
- Database: MySQL (per service)  
- Build Tool: Maven  
- Language: Java  

---

## 📁 Project Structure

### 👤 User Service
- Controller  
- Service  
- Repository  
- DTO  
- Models  

### 🔄 Transaction Service
- Controller  
- Service  
- Repository  
- DTO  
- Models  

### 💰 Wallet Service
- Service  
- Repository  
- Models  
- Kafka Consumer  

---

## 🔗 API Endpoints

### 👤 User Service
- POST `/users` → Create User  

### 🔄 Transaction Service
- POST `/transactions` → Create Transaction  

### 💰 Wallet Service
- Internal service (Kafka-based updates)

---

## 🔄 System Flow

1. User is created via User Service  
2. Transaction is initiated via Transaction Service  
3. Transaction event is sent to Kafka  
4. Wallet Service consumes event  
5. Wallet balance is updated automatically  

---

## 🔐 Key Concepts Used

- Microservices Architecture  
- Event-Driven Design  
- Apache Kafka Producer–Consumer  
- REST APIs  
- DTO Pattern  
- Service Layer Architecture  

---

## 🚀 Future Enhancements

- API Gateway integration  
- JWT Authentication  
- Redis caching for wallet balance  
- Docker containerization  
- Kubernetes deployment  
- Payment gateway integration  

---

## 👨‍💻 Author

- GitHub: https://github.com/KArtickCode  

---

## ⭐ Support

If you like this project, please give it a ⭐ on GitHub!
