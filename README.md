# The Booking

The Booking is a full stack hotel booking application with a React frontend and a Spring Boot backend. It allows users to browse available rooms, make bookings, and manage reservations. Administrators can manage rooms and view all bookings.

## Project Structure

- **FE/**: Frontend (React, Vite)
- **BE/**: Backend (Spring Boot, Java)

## Features

- User registration and authentication (JWT)
- Browse and filter available rooms
- Book rooms and view booking history
- Admin panel for managing rooms and bookings
- Responsive design

## Getting Started

### Prerequisites

- Node.js (Frontend)
- Java 17+ (Backend)
- MySQL

### Backend Setup

1. Navigate to the `BE` directory:
   ```
   cd BE
   ```
2. Configure your database in `src/main/resources/application.properties`.
3. Build and run:
   ```
   ./gradlew bootRun
   ```

### Frontend Setup

1. Navigate to the `FE` directory:
   ```
   cd FE
   ```
2. Install dependencies:
   ```
   npm install
   ```
3. Start the development server:
   ```
   npm run dev
   ```

## Usage

- Frontend: [http://localhost:5173](http://localhost:5173)
- Backend: [http://localhost:8080](http://localhost:8080)
