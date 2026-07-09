## Project Overview







BudgetBuy is an AI-powered full-stack shopping platform that helps users make smarter purchasing decisions by aggregating products from multiple sources and recommending the most cost-effective combinations within a user-defined budget.







The platform combines Spring Boot, React.js, JWT Authentication, MySQL, and Large Language Models (LLMs) to deliver personalized recommendations, conversational shopping assistance, and intelligent budget optimization.







## ✨ Features







- Multi-Source Product Aggregation



- AI-Powered Product Recommendations



- Budget-Constrained Shopping Planner



- Dynamic Programming-based Product Optimization



- Intelligent Product Ranking



- Alternative Budget Planning



- Conversational AI Shopping Assistant



- JWT Authentication & Authorization



- User Dashboard



- Admin Dashboard



- Product Search & Filtering



- Wishlist & Shopping Cart



- Order Management



- Responsive User Interface







## 🛠 Tech Stack







### Backend



- Java



- Spring Boot



- Spring Security



- JWT Authentication



- REST APIs







### Database



- MySQL



- Hibernate / JPA







### AI & Algorithms



- Large Language Models (LLMs)



- Dynamic Programming



- Recommendation Engine







### Tools



- Maven



- Git



- GitHub



- Postman



- Docker (Optional)







## 🤖 AI Modules







- Personalized Product Recommendation



- Conversational Shopping Chatbot



- Intelligent Product Ranking



- Budget Planning Assistant



- Smart Shopping Insights







## 💰 Budget Optimization Engine







The recommendation engine uses Dynamic Programming to compute the optimal combination of products under user-defined budget constraints while maximizing:







- Product Quality



- Customer Ratings



- Price Efficiency



- Overall Value







## 🔐 Authentication & Security







- JWT Authentication



- Role-Based Authorization



- BCrypt Password Encryption



- Protected REST APIs











##🏗 System Architecture







                React.js Frontend



                       │



                 REST API Calls



                       │



              Spring Boot Backend



                       │



      ┌────────────┬──────────────┐



      │            │              │



 Authentication   AI Engine    Product Service



      │            │              │



      └────────────┴──────────────┘



                     │



            Recommendation Engine



                     │



             Dynamic Programming



                     │



                  MySQL Database











##📂 Project Structure



BudgetBuy



│



├── frontend



│   ├── components



│   ├── pages



│   ├── services



│   └── assets



│



├── backend



│   ├── controller



│   ├── service



│   ├── repository



│   ├── security



│   ├── model



│   └── config



│



├── database



├── screenshots



└── README.md











## 🚀 Future Enhancements







- Voice Shopping Assistant



- Image-Based Product Search



- Price Prediction using Machine Learning



- Real-Time Price Drop Alerts



- Amazon & Flipkart API Integration



- ElasticSearch for Fast Search



- Redis Caching



- Kafka Event Streaming



- Docker & Kubernetes Deployment







## 📚 Learning Outcomes







- Full-Stack Development with React and Spring Boot



- Secure Authentication using JWT



- REST API Design



- AI-assisted Recommendation Systems



- Dynamic Programming for Optimization



- Database Design with MySQL



- Scalable Client-Server Architecture







## ▶️ Running the Project







### Prerequisites







Make sure you have the following installed:







- Java 21 (or your project version)



- Maven



- Node.js (v18+)



- npm



- MySQL



- Git







---







### 1. Clone the Repository







```bash



git clone https://github.com/yourusername/BudgetBuy.git



cd BudgetBuy



```







---







### 2. Configure the Database







Create a MySQL database:







```sql



CREATE DATABASE budgetbuy;



```







Update your `application.properties` (or `application.yml`):







```properties



spring.datasource.url=jdbc:mysql://localhost:3306/budgetbuy



spring.datasource.username=YOUR_USERNAME



spring.datasource.password=YOUR_PASSWORD







spring.jpa.hibernate.ddl-auto=update



```







---







### 3. Start the Backend







Navigate to the backend directory:







```bash



cd backend



```







Run the application:







```bash



mvn spring-boot:run



```







The backend will be available at:







```



http://localhost:8080



```







---







### 4. Start the Frontend







Open another terminal:







```bash



cd frontend



```







Install dependencies:







```bash



npm install



```







Start the development server:







```bash



npm run dev



```







The frontend will be available at:







```



http://localhost:5173



```







---







### 5. Access the Application







Open your browser and visit:







```



http://localhost:5173



```







---
