# Microservices Project

This project contains four microservices:

1. **EDMS** - Employee Database Management system
2. **Time Off** - Time off management service
3. **Notification** - Notification service
4. **Holiday** - Holiday management service

## Prerequisites

Before running the microservices, make sure you have the following installed:

- [Java 17 or later](https://adoptium.net/)
- [Maven 3.6.3 or later](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/get-started) (if using Docker to run microservices)
- [Redis](https://redis.io/) (if any service requires it)
- [Mongo](https://www.rabbitmq.com/) (if any service uses a message broker)

## Running the Microservices

### 1. EDMS Service

**Description:** The EDMS service manages employees

#### Steps to Run:

1. Navigate to the `edms` directory:
    ```bash
    cd edms
    ```

2. Build the project using Maven:
    ```bash
    mvn clean install
    ```

3. Run the service:
    ```bash
    mvn spring-boot:run
    ```

4. Access the service:
    - The service will be available at `http://localhost:2373`

### 2. Time Off Service

**Description:** The Time Off service handles time off requests for employees.

#### Steps to Run:

1. Navigate to the `time.off` directory:
    ```bash
    cd time.off
    ```

2. Build the project using Maven:
    ```bash
    mvn clean install
    ```

3. Run the service:
    ```bash
    mvn spring-boot:run
    ```

4. Access the service:
    - The service will be available at `http://localhost:2372`

### 3. Notification Service

**Description:** The Notification service handles email notifications.

#### Steps to Run:

1. Navigate to the `notifications` directory:
    ```bash
    cd notification
    ```

2. Build the project using Maven:
    ```bash
    mvn clean install
    ```

3. Run the service:
    ```bash
    mvn spring-boot:run
    ```

4. Access the service:
    - The service will runs in the background

### 4. Holiday Service

**Description:** The Holiday service manages holiday information and queries.

#### Steps to Run:

1. Navigate to the `public.holidays` directory:
    ```bash
    cd public.holidays
    ```

2. Build the project using Maven:
    ```bash
    mvn clean install
    ```

3. Run the service:
    ```bash
    mvn spring-boot:run
    ```

4. Access the service:
    - The service runs in the background

## Running with Docker (Optional)

If you prefer to run the microservices using Docker, ensure Docker is installed and running on your machine.

1. Build Docker images for each microservice:

```bash
cd edms/emds.container
docker build -t edms-service .

cd ../time.off/time.off.container
docker build -t time-off-service .

cd ../notifications/notifications.container
docker build -t notification-service .

cd ../public.holidays/public.holidays.container
docker build -t holiday-service .
