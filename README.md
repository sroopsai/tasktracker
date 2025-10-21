# 🧩 TaskTracker CLI Tool

A simple yet powerful **Command Line Interface (CLI)** tool to track your daily tasks.  
Built in **Java**, it helps you manage tasks with ease — from adding and updating to marking progress or completion — all through your terminal.

---

## 🚀 Features

- Add new tasks
- Update existing tasks
- Delete tasks
- Mark tasks as `todo`, `in-progress`, or `done`
- List all tasks or filter by status
- Persistent storage using a JSON file
- Fully immutable task model using Java Records

---

## 🧱 Tech Stack

- **Language:** Java 17+
- **Build Tool:** Maven
- **Storage:** JSON file in the working directory
- **Design Pattern:** Layered architecture (Controller → Service → Repository → Model)

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the repository
```bash
git clone https://github.com/sroopsai/tasktracker.git
cd tasktracker