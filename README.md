# 🎓 Role-Based Quiz System in Java

This project is a **simple Java-based quiz system** with role-based access. It allows **admin users** to add quiz questions and **student users** to take a 10-question quiz randomly generated from a question bank. Data is stored in local JSON files for persistence.

---

## 📄 Project Summary

The system supports two types of users:
1. **Admin** – Adds multiple-choice questions (MCQs) to the quiz bank.
2. **Student** – Takes a quiz of 10 random MCQs and receives a score at the end.

Data is stored in:
- `users.json` – stores login credentials and roles.
- `quiz.json` – stores all MCQs.

---

## 🧰 Technology Stack

| Component     | Description         |
|---------------|---------------------|
| Language       | Java                |
| Data Storage   | JSON Files          |
| Library Used   | JSON.simple (for parsing JSON) |

---

## ⚙️ Prerequisites

- ✅ Install **Java JDK**
- ✅ Use an IDE (e.g., IntelliJ IDEA, Eclipse) or command line
- ✅ Add `json-simple` JAR to your project classpath (if not using Maven/Gradle)

---

## ▶️ How to Run

1. **Clone the project**
2. Place `users.json` and `quiz.json` inside your project’s `resources/` or root folder
3. Open the project in your preferred IDE or run from terminal
4. Run the main class (e.g., `Main.java`)

---

## 🧪 Login Credential

| Role    | Username | Password |
| ------- | -------- | -------- |
| Admin   | `admin`  | `1234`   |
| Student | `salman` | `1234`   |

## Demo Output
[<video src="https://user-images.githubusercontent.com/.../video.mp4" controls></video>](https://github.com/user-attachments/assets/feac919f-9dd8-46ba-a9bc-24234130158a)
