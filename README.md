# Job Portal — Microservices

A refactor of the monolithic Job Portal application into 3 independent Spring Boot microservices, each with its own database.

## Services

| Service | Port | Responsibility | Database |
|---|---|---|---|
| `companyms` | 8081 | Company management | `company` (PostgreSQL) |
| `jobms` | 8082 | Job postings | `job` (PostgreSQL) |
| `reviewms` | 8084 | Company reviews | `review` (PostgreSQL) |

Each service is fully independent — there is currently **no service discovery, API gateway, or inter-service communication** between them. This is a planned next step (see Roadmap).

## Tech Stack

- **Java 17**
- **Spring Boot 3.3.4**
- **Spring Data JPA** (Hibernate)
- **PostgreSQL** — one database per service
- **H2** — available as a dependency, currently commented out in favor of PostgreSQL
- **Maven**

## API Endpoints

### companyms — `/companies`
| Method | Path | Description |
|---|---|---|
| GET | `/companies` | List all companies |
| POST | `/companies` | Create a company |
| GET | `/companies/{id}` | Get a company by ID |
| PUT | `/companies/{id}` | Update a company |
| DELETE | `/companies/{id}` | Delete a company |

### jobms — `/job`
| Method | Path | Description |
|---|---|---|
| GET | `/job` | List all jobs |
| POST | `/job` | Create a job |
| GET | `/job/{id}` | Get a job by ID |
| PUT | `/job/{id}` | Update a job |
| DELETE | `/job/{id}` | Delete a job |

### reviewms — `/reviews`
| Method | Path | Description |
|---|---|---|
| GET | `/reviews?companyId={id}` | List reviews for a company |
| POST | `/reviews?companyId={id}` | Create a review for a company |
| GET | `/reviews/{reviewId}` | Get a review by ID |
| PUT | `/reviews/{reviewId}` | Update a review |
| DELETE | `/reviews/{reviewId}` | Delete a review |

Note: `companyId` is stored as a plain foreign key value in `jobms` and `reviewms` rather than a JPA relationship — each service owns its own data independently, since they no longer share a database with `companyms`.

## Container Images

Each service has its own Dockerfile and is published independently to Docker Hub:

- `jawahar27/companyms`
- `jawahar27/jobms`
- `jawahar27/reviewms`

Build and run any service locally:
docker build -t jawahar27/<service-name> .
docker run -p <port>:<port> jawahar27/<service-name>


## Running Locally

Each service needs its own local PostgreSQL database (`company`, `job`, `review`) and runs independently. Set `DB_USERNAME` and `DB_PASSWORD` environment variables before running:

cd companyms && ./mvnw spring-boot:run # port 8081
cd jobms && ./mvnw spring-boot:run # port 8082
cd reviewms && ./mvnw spring-boot:run # port 8084


## Known Limitations / Roadmap

This project currently demonstrates splitting a monolith into independently deployable services with separate databases. Not yet implemented:

- Service discovery / registry (Eureka)
- API Gateway as a single entry point
- Inter-service communication (Feign / WebClient)
- Orchestration (Kubernetes)
- Authentication and authorization
- Automated tests beyond the default context-load test
- CI/CD pipeline

These are the planned next steps to make this a more complete microservices reference project.


