# Career-Craft AI 🎯

Career-Craft AI is an AI-powered Career Guidance System that helps users identify their ideal career paths based on their skills, interests, and goals. This full-stack web application uses a modern frontend with elegant UI/UX and a robust Spring Boot backend.

---

## 🚀 Features

- ✨ AI-based Career Assistance Test (CAT)
- 🧠 Dynamic questionnaire based on user responses
- 📊 Personalized career suggestions
- 🎨 Responsive UI with Bootstrap and animations
- 📁 Resume builder and downloader (future scope)
- 💬 Smooth user interactions and real-time feedback

---

## 🛠 Tech Stack

| Layer      | Technology                       |
|------------|----------------------------------|
| Frontend   | HTML, CSS, JavaScript, Bootstrap |
| UI/UX      | Tailwind (optional), Animations  |
| Backend    | Java Spring Boot (RESTful APIs)  |
| Template   | Thymeleaf                        |
| Database   | MySQL                            |
| Tool       | Spring Tool Suite 4              |
| Versioning | Git & GitHub                     |

---

## 📸 Screenshots

> Include screenshots here (UI, Test Page, Results Page, etc.)

---

## 📂 Project Structure
Career-Craft-AI/
│
├── src/main/java/com/careercraftai/
│ ├── controller/
│ ├── entity/
│ ├── service/
│ ├── repository/
│
├── src/main/resources/
│ ├── templates/ # Thymeleaf HTML files
│ ├── static/ # CSS, JS
│ └── application.properties
│
└── pom.xml # Project dependencies

yaml
Copy
Edit

---

## 🔧 Setup & Run Locally

1. *Clone the repository*
   ```bash
   git clone https://github.com/codewithferozali/Career-Craft-AI.git
   cd Career-Craft-AI
Set up MySQL database

Create a database (e.g., careerdb)

Update DB credentials in application.properties:

properties
Copy
Edit
spring.datasource.url=jdbc:mysql://localhost:3306/careerdb
spring.datasource.username=your_username
spring.datasource.password=your_password
Open the project in Spring Tool Suite 4

Import as a Maven Project

Run CareerCraftAiApplication.java

Visit in browser

arduino
Copy
Edit
http://localhost:8088
💡 Future Enhancements
Resume Builder + Template Selector

Personality Assessment Integration

Admin Panel for managing questions/suggestions

Integration with OpenAI for smarter recommendations

🙌 Author
Feroz Ali
📧 ferozalircr@gmail.com
🔗 LinkedIn
💻 GitHub: codewithferozali
