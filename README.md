# DNA Sequence Challenge

This is a Spring Boot application designed to analyze DNA sequences and determine whether a given DNA sequence is from a mutant or not. The application exposes a REST API that can be used to interact with the DNA sequence logic.

## Prerequisites

Before you begin, ensure you have the following installed on your machine:

- **Docker**: For building and running the containerized application.
- **Docker Compose**: For managing multi-container Docker applications.
  
- **JDK 21** (or higher): If you want to run the app locally without Docker.
- **Maven**: If you want to build the app locally without Docker.

## Running the Application with Docker

The application is containerized using Docker, and we use Docker Compose to manage both the application container and the PostgreSQL database.

### Steps to Run the Application

1. **Clone the Repository**

   First, clone the repository to your local machine:

   ```bash
   git clone https://your-repository-url.git
   cd your-repository-directory

2. **Build and Run the Application with Docker Compose**

   Make sure Docker and Docker Compose are installed and running. To build and start the application with the database container, use the following command:

   ```bash
   docker-compose up --build

2. **Access the Application**

   Once the containers are running, you can access the APIs via HTTP requests. Here are some examples of endpoints you can use:

   - POST /api/dna/mutant/ - Submit a DNA sequence to check if it's from a mutant.
      - Request Body:
         ```json
         {
            "dna": ["ATCGTA", "TGGGGC"]
         }
         
      - Response: HTTP Status 200 for mutants, 403 for non-mutants.
        
   - GET /api/dna/stats/ - Get statistics on the number of mutants and non-mutants in the database.
      - Response: A JSON object with mutant count, non-mutant count, and ratio.
        
### Stop Running the containers

To stop the containers, run:

    ```bash
    docker-compose down

This will stop and remove the containers, but the data will persist in the database volume.

### Optional: Running the Application Locally (Without Docker)
If you prefer to run the application without Docker, follow these steps:

1. **Build the Application Locally**

   If you have Maven installed, you can build the application using:

   ```bash
   mvn clean install

2. **Run the Application Locally**

   Once built, you can run the application with:

   ```bash
   mvn spring-boot:run

2. **Start PostgreSQL Locally**

   Ensure you have a PostgreSQL instance running on localhost:5432 with the correct database (dna), username (postgres), and password (postgres).


## Juan Diego Naranjo Tafur
