
# Architecture Overview

```mermaid
flowchart LR
User[Web / Mobile] --> API[REST API (Express)]
API --> DB[(Relational DB)]
API --> Auth[JWT/MFA / University SSO]
subgraph Services
API
Auth
end
```

## Component Diagram
```mermaid
flowchart TB
Client --> Router
Router --> StudentsController
Router --> CoursesController
Router --> EnrollmentsController
StudentsController --> StudentsService
CoursesController --> CoursesService
EnrollmentsController --> EnrollmentsService
StudentsService --> Repo[(StudentRepo)]
CoursesService --> Repo2[(CourseRepo)]
EnrollmentsService --> Repo3[(EnrollRepo)]
Repo --- DB[(PostgreSQL/MySQL)]
```

