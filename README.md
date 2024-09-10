# 🍽️ ABC Restaurant

ABC Restaurant is a renowned brand in Sri Lanka, known for its diverse menu and outstanding customer service. To better cater to tech-savvy customers, we are launching a new web application to streamline customer interactions. This platform will allow users to book tables, order meals, request additional services, and make secure payments—all from their own devices.

## 🎯 Objectives

The main objectives of this project are:

- **Online Reservation System:** Facilitate online table bookings, menu viewing, and meal ordering.
- **Enhanced Customer Interaction:** Enable customers to easily communicate with restaurant staff about reservations and services.
- **Streamlined Payment Processes:** Integrate secure payment gateways for online orders and reservations.
- **Administrative Tools:** Provide management tools for restaurant staff to handle reservations, process orders, respond to customer inquiries, and generate business reports.
- **User-Friendly Interface:** Ensure the web application is accessible and intuitive for all types of users.

## 🚀 Getting Started

### Prerequisites

Before setting up the project, make sure the following tools and dependencies are installed on your machine:

- Docker (to containerize the application and handle all dependencies)
- Git (to clone the repository)

**Note:** You do not need to manually install Java, Node.js, or any specific versions of libraries, as Docker will manage these dependencies for you.

### Installation

To set up the project locally, follow these steps:

1. **Clone the Repository:**

    ```bash
    git clone https://github.com/prageethmilan/ABC-Restaurant.git
    cd ABC-Restaurant
    ```

2. **Run the Application with Docker Compose:**

    Ensure you have Docker and Docker Compose installed, then run:

    ```bash
    docker-compose up --build
    ```

    This command will build the Docker images for both the backend and frontend and start the containers.

3. **Access the Application:**

    - **Frontend:** Open your browser and navigate to [http://localhost:3000](http://localhost:3000).
    - **Backend API:** Access backend services at [http://localhost:8080](http://localhost:8080).

## 🛠️ Project Structure

- **Backend:** The backend is built using Spring Boot and provides REST APIs for the frontend to interact with. It uses MySQL as the database and includes features like security, reservation management, and order processing.
- **Frontend:** The frontend is developed using ReactJS, offering a user-friendly interface for customers and staff.

## 🔧 Development

If you want to run the project without Docker, ensure the following:

### Backend

1. Navigate to the Backend Directory:

    ```bash
    cd abc-restaurant-backend
    ```

2. Build the Backend with Maven:

    ```bash
    mvn clean package
    ```

3. Run the Backend:

    ```bash
    java -jar target/abc-restaurant-backend.jar
    ```

### Frontend

1. Navigate to the Frontend Directory:

    ```bash
    cd abc-restaurant-frontend
    ```

2. Install Node Modules:

    ```bash
    npm install
    ```

3. Run the Frontend:

    ```bash
    npm start
    ```

## 📦 Docker Deployment

To make the application easily deployable and avoid the hassle of environment setup, we recommend using Docker.

- **Backend Dockerfile:** Located in `abc-restaurant-backend/Dockerfile`.
- **Frontend Dockerfile:** Located in `abc-restaurant-frontend/Dockerfile`.
- **Docker Compose File:** Located in the root directory as `docker-compose.yml`.

## 📑 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

We welcome contributions! Please read our [CONTRIBUTING.md](CONTRIBUTING.md) for more information on how to get started.

## 📧 Contact

For any inquiries or issues, please contact the project maintainer at [prageethmilan1999@gmail.com](mailto:prageethmilan1999@gmail.com).
