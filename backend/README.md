# EventTicket Platform - Microservices Architecture

A production-grade Java Spring Boot distributed system for event ticketing with modular microservices architecture, domain-driven design, and clean architecture principles.

## 🏗️ Architecture Overview

The EventTicket platform follows a **modular microservices architecture** with:

- **Domain-Driven Design (DDD)** principles
- **Hexagonal/Clean Architecture** (Ports & Adapters pattern)
- **Controller-Service-Repository** structure
- **Strategy Pattern** for payment providers
- **Observer Pattern** for event-driven communication
- **Factory Pattern** for QR ticket generation
- **Builder Pattern** for complex object construction

## 🧩 Microservices

| Service | Port | Description | Architecture Pattern |
|---------|------|-------------|---------------------|
| **user-service** | 8081 | User management, authentication, roles | Clean Architecture |
| **event-service** | 8082 | Event CRUD, approval flow, categories | Hexagonal Architecture |
| **order-service** | 8083 | Order creation, cart management | Transaction-safe Design |
| **payment-service** | 8084 | Payment processing, multiple providers | Strategy Pattern |
| **ticket-service** | 8085 | QR generation, ticket lifecycle | Snowflake ID Generation |
| **notification-service** | 8086 | Async messaging, pub/sub | Event-driven Architecture |
| **review-service** | 8087 | Ratings, reviews, validation | CRUD with Validation |
| **admin-service** | 8088 | Admin dashboard, audit logs | Admin Management |
| **api-gateway** | 8080 | API Gateway, routing, auth | Spring Cloud Gateway |

## 🧰 Shared Modules

- **common-utils**: Error handling, time utilities, exception mappers, response wrappers
- **domain-events**: Event bus models, audit base classes, inter-service DTOs
- **security-core**: JWT/Firebase integration, filters, auth context helpers

## 🚀 Quick Start

### Prerequisites

- Java 17+
- Maven 3.8+
- Docker & Docker Compose
- Azure SQL Server (or use H2 for development)

### Local Development

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd EventTicket
   ```

2. **Start the infrastructure**
   ```bash
   docker-compose up -d sqlserver redis kafka
   ```

3. **Build shared modules**
   ```bash
   cd common-utils
   mvn clean install
   ```

4. **Build and run microservices**
   ```bash
   # Build all services
   mvn clean install -DskipTests
   
   # Run individual services (example)
   cd user-service
   mvn spring-boot:run
   ```

5. **Or run all services with Docker Compose**
   ```bash
   docker-compose up -d
   ```

### Service URLs

- **API Gateway**: http://localhost:8080
- **User Service**: http://localhost:8081
- **Event Service**: http://localhost:8082
- **Order Service**: http://localhost:8083
- **Payment Service**: http://localhost:8084
- **Ticket Service**: http://localhost:8085
- **Notification Service**: http://localhost:8086
- **Review Service**: http://localhost:8087
- **Admin Service**: http://localhost:8088

### Swagger Documentation

Each service includes Swagger UI:
- User Service: http://localhost:8081/swagger-ui.html
- Event Service: http://localhost:8082/swagger-ui.html
- And so on...

## 🗃️ Database Schema

The platform uses a comprehensive database schema with 15+ tables:

- **Users & Authentication**: `users`, `user_roles`, `user_role_mapping`
- **Event Management**: `events`, `event_categories`, `venues`
- **Ticketing**: `ticket_types`, `tickets`, `orders`, `order_items`
- **Payments**: `payments`
- **User Engagement**: `event_reviews`, `event_favorites`, `notifications`
- **Audit & Security**: `audit_logs`

## 🔒 Security & Authentication

- **Firebase Auth** integration for user authentication
- **JWT tokens** for service-to-service communication
- **Role-based access control** (Admin, Organizer, Attendee)
- **Spring Security** with method-level security
- **Audit logging** for compliance and security

## 💳 Payment Processing

The payment service implements the **Strategy Pattern** to support multiple payment providers:

- **Mock Payment** (default for development)
- **Stripe** (configurable)
- **Razorpay** (configurable)
- **Extensible** for additional providers

## 🎫 Ticket Management

- **Snowflake ID generation** for unique ticket numbers
- **QR code generation** using ZXing library
- **Ticket lifecycle management** (Generated → Delivered → Scanned → Used)
- **Duplicate prevention** and validation

## 📬 Notifications

- **Async processing** for better performance
- **Multiple channels**: Email, SMS, Push notifications
- **Event-driven architecture** using Kafka (optional)
- **Template-based messaging** with localization support

## 🧪 Testing

Each service includes:

- **Unit tests** with JUnit 5 + Mockito
- **Integration tests** for database operations
- **API tests** with REST-assured
- **Test containers** for database testing

## 🚀 Deployment

### Google Cloud Platform (GCP)

The platform is designed for deployment on GCP:

- **Cloud Run** for containerized services
- **Firebase Auth** for authentication
- **Azure SQL Server** for database
- **Cloud Build** for CI/CD
- **Cloud Monitoring** for observability

### Docker Deployment

```bash
# Build all services
docker-compose build

# Deploy to production
docker-compose -f docker-compose.prod.yml up -d
```

## 📊 Monitoring & Observability

- **Spring Boot Actuator** for health checks
- **Prometheus metrics** (configurable)
- **Structured logging** with correlation IDs
- **Audit trails** for compliance
- **Performance monitoring** and alerting

## 🔧 Configuration

Each service supports multiple profiles:

- **dev**: H2 database, debug logging
- **docker**: Containerized environment
- **prod**: Production settings

Environment variables for configuration:
- Database connection
- Firebase credentials
- Payment provider settings
- Redis/Kafka configuration

## 📈 Scalability Features

- **Stateless services** for horizontal scaling
- **Database connection pooling**
- **Redis caching** for performance
- **Rate limiting** to prevent abuse
- **Circuit breakers** for resilience
- **Load balancing** ready

## 🤝 Contributing

1. Follow the **clean architecture** principles
2. Write **comprehensive tests**
3. Use **meaningful commit messages**
4. Follow **Java coding conventions**
5. Update **documentation** for new features

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Support

For support and questions:
- Create an issue in the repository
- Check the documentation
- Review the Swagger API docs

---

**Built with ❤️ using Spring Boot, Clean Architecture, and Domain-Driven Design** 