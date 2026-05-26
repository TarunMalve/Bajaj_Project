# Bajaj Finserv Health API Challenge - Spring Boot Solution

## Project Overview
This project is a production-ready Spring Boot 3.x REST API implementation for the BFHL challenge.
It exposes:
- `POST /bfhl` for data processing
- `GET /health` for health check

It uses Java 21, clean package organization, constructor injection, centralized exception handling, and automated tests.

## Architecture
Base package: `com.bfhl`

- `controller` - REST controllers
- `service` - service contracts
- `service.impl` - business logic implementation
- `dto` - request/response data contracts
- `exception` - custom exception and global exception handler
- `config` - reserved for configuration classes
- `util` - reserved for utility classes

## API Documentation

### 1) POST `/bfhl`
Processes mixed input and returns transformed fields.

#### Request Body
```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```

#### Response Body (Example)
```json
{
  "is_success": true,
  "user_id": "tarun_malve_ddmmyyyy",
  "email": "YOUR_EMAIL",
  "roll_number": "YOUR_ROLL_NUMBER",
  "odd_numbers": ["1"],
  "even_numbers": ["334", "4"],
  "alphabets": ["A", "R"],
  "special_characters": ["$"],
  "sum": "339",
  "concat_string": "Ra"
}
```

### 2) GET `/health`
#### Response
```json
{
  "status": "UP"
}
```

## Sample Requests

### cURL - POST /bfhl
```bash
curl --location 'http://localhost:8080/bfhl' \
--header 'Content-Type: application/json' \
--data '{
  "data": ["A", "ABCD", "DOE"]
}'
```

### cURL - GET /health
```bash
curl --location 'http://localhost:8080/health'
```

## Sample Responses
For `{"data": ["A", "ABCD", "DOE"]}`:
```json
{
  "is_success": true,
  "user_id": "tarun_malve_ddmmyyyy",
  "email": "YOUR_EMAIL",
  "roll_number": "YOUR_ROLL_NUMBER",
  "odd_numbers": [],
  "even_numbers": [],
  "alphabets": ["A", "ABCD", "DOE"],
  "special_characters": [],
  "sum": "0",
  "concat_string": "EoDdCbAa"
}
```

## Local Setup Instructions
1. Ensure Java 21 and Maven are installed.
2. Update placeholder identity values in `src/main/resources/application.properties`:
   - `bfhl.email`
   - `bfhl.roll-number`
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Run tests:
   ```bash
   mvn test
   ```

## Render Deployment Instructions
1. Push this repository to GitHub.
2. Create a new **Web Service** on Render.
3. Use environment:
   - Runtime: Docker or Java
   - Build command: `mvn clean package`
   - Start command: `java -jar target/bajaj-project-1.0.0.jar`
4. Deploy and verify:
   - `GET /health`
   - `POST /bfhl`
