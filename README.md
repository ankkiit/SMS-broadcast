This project is a Spring Boot–based web application that enables users to send single or bulk SMS messages. It features user authentication, message scheduling, campaign management, and Do Not Disturb (DND) number filtering. SMS messages are stored in a database, and their status updates automatically from pending to sent after the scheduled time.

🔧 Tech Stack
Backend: Java, Spring Boot, Spring Security, Spring Data JPA

Frontend: HTML, CSS

Database: MySQL

✅ Key Features
User login with Spring Security (in-memory authentication)

Send individual SMS with campaign and schedule support

Upload file to broadcast SMS in bulk

DND number check and message redirection to dndmessage table

Auto status update logic (e.g., from pending to sent)

Report screen showing counts of sent, pending, and DND messages



<img width="1890" height="835" alt="Screenshot 2025-07-29 131232" src="https://github.com/user-attachments/assets/2a5b18c6-c156-45c9-b2c9-84bf1428d9b5" />

<img width="1913" height="423" alt="Screenshot 2025-07-29 131420" src="https://github.com/user-attachments/assets/5af9e57c-67e0-4898-9cbe-9fbb3f563f66" />

<img width="1844" height="844" alt="Screenshot 2025-07-29 131552" src="https://github.com/user-attachments/assets/213f9193-4d8a-44bc-97b1-c58c7fefbb5d" />
