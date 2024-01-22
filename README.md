# Test project BRDO

## Introduction

This project is a Spring Boot application designed for handling and displaying comments data from an external API. It
features functionalities like data fetching, processing, storing in MySQL database, and presenting data on a
Thymeleaf-based web interface. The application also includes user authentication and authorization using Spring
Security.

## Prerequisites

- JDK 17
- MySQL
- Gradle

## Setup and Installation

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/aleporyadin/brdo.git
   cd brdo
   ```

2. **Configure MySQL Database:**

   Ensure MySQL is installed and running on your machine.

   **Create a new database with name `brdo` for the application**.

   Update `src/main/resources/application.properties` with your MySQL username and password.


3. **Build the Application:**

```bash
gradle build
```

4. **Run the Application**

```bash
gradle bootRun
```
Usage Once the application is running, navigate to http://localhost:8080 in your browser to view the user interface. You
can log in, view, and interact with the comments data fetched from the external API.