# Backend for Self Learning System

**Self Learning Platform** is a minimal yet powerful template for quickly bootstrapping a Spring Boot application. This template provides a clean and structured foundation, allowing developers to skip repetitive setup and jump straight into building their features.

---

## 🚀 Features

* Spring Boot pre-configured with sensible defaults
* Modular folder structure
* Ready-to-use application.yaml configs for quick setup
* Example controller, service, and repository layers
* Simple REST endpoint to verify the setup
* Maven build support
* Easily extensible for microservices or monolithic architectures

---

## 📆 Getting Started

### Prerequisites

* Java 21 (compatible with Spring Boot 3.x)
* Maven
* IDE of your choice (IntelliJ IDEA, Eclipse, VSCode, etc.)

### Clone the Repository

```bash
git clone https://github.com/your-username/BlueprintSpring.git
cd BlueprintSpring
```

### Run the Application

#### Using Maven:

```bash
./mvnw spring-boot:run
```

### Verify Setup

Once the application is running, open your Terminal or Postman and hit:

```
curl --location 'http://localhost:8080/api/auth/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "abcd@gmail.com",
    "password": "abcd@1234"
}'
```

You should see a sample response like:

```json
{
    "id": 1,
    "email": "abcd@gmail.com",
    "userIdentity": "abcd@gmail.com"
}
```

---

## 🛠 Project Structure

```
src
└── main
    ├── java
    │   └── co.in.nextgencoder.learningpath
    │       ├── config
    │       ├── controller
    │       ├── domain
    │       │   ├── enumeration
    │       ├── repository
    │       ├── service
    │       │   ├── dto
    │       │   ├── exception
    │       │   ├── mapper
    │       ├── dto
    │       └── SpringBlueprintApplication.java
    └── resources
        └── resources
            ├── application.yaml
            ├── application-dev.yaml
            └── application-prod.yaml
```

This structure makes it easy to follow the standard Spring Boot project layout and helps maintain the separation of concerns.

---

## 📚 Customization

Feel free to modify:

* `application.yaml` for your own configuration
* Add new endpoints or services as needed

