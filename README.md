# 🧩 TaskTracker CLI Tool

[![View on GitHub](https://img.shields.io/badge/View%20on-GitHub-black?logo=github)](https://github.com/sroopsai/tasktracker)
[![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Build-Maven-blue?logo=apache-maven)](https://maven.apache.org/)
[![License: Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)

A simple yet powerful **Command Line Interface (CLI)** tool to track your daily tasks.  
Built in **Java**, it helps you manage tasks with ease — from adding and updating to marking progress or completion — all through your terminal.

---

## 🔗 Project Links
- 📦 **Repository:** [https://github.com/sroopsai/tasktracker](https://github.com/sroopsai/tasktracker)
- 🪄 **Issues Board:** [https://github.com/sroopsai/tasktracker/issues](https://github.com/sroopsai/tasktracker/issues)
- 🔧 **Pull Requests:** [https://github.com/sroopsai/tasktracker/pulls](https://github.com/sroopsai/tasktracker/pulls)

---

## 🚀 Features

- Add, update, and delete tasks
- Mark tasks as `todo`, `in-progress`, or `done`
- List all tasks or filter by status
- Persistent storage in a JSON file
- Immutable data model using Java Records
- Simple CLI interface — no external dependencies

---

## 🧱 Tech Stack

| Component | Technology |
|------------|-------------|
| **Language** | Java 17+ |
| **Build Tool** | Maven |
| **Storage** | JSON File |
| **Design** | Layered Architecture (Controller → Service → Repository → Model) |

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the repository
```bash
git clone https://github.com/sroopsai/tasktracker.git
cd tasktracker