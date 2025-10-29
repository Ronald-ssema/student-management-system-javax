
# Student Management System (Starter Pack)

A clean, documented starter for building a Student Management System (SMS).

**Highlights**
- Minimal **Node.js/Express** API (TypeScript-ready)
- **PostgreSQL** (any SQL) schema + seed data
- **OpenAPI** spec & human-readable **API docs**
- **Mermaid** diagrams (ERD + sequences)
- MIT license, contribution guide

---

## Tech stack
- Node.js 20+, npm
- Express 4+
- PostgreSQL 14+ (or SQLite for dev)
- Knex/SQL (plain SQL files in `/database`)

---

## Quick start
```bash
# 1) Install
cd backend
npm install


# 2) Configure env (copy .env.example → .env)
#   DATABASE_URL=postgres://user:pass@localhost:5432/sms

# 3) Run dev server
npm run dev
# API will be at http://localhost:3000

# Create schema & seed
psql "$DATABASE_URL" -f ../database/schema.sql
psql "$DATABASE_URL" -f ../database/seed.sql

## Project structure

```
student-management-system/
├─ backend/           # Express API
│  ├─ src/
│  ├─ package.json
│  └─ .env.example
├─ database/          # SQL schema & seeds
│  ├─ schema.sql
│  └─ seed.sql
├─ docs/              # Requirements, API, diagrams
│  ├─ api.md
│  ├─ architecture.md
│  ├─ erd.md
│  ├─ openapi.yaml
│  └─ sequence-enrollment.md
├─ LICENSE
└─ README.md

## License

---

## docs/requirements.md

```markdown
# Requirements

## Functional
1. Manage Students: create, view, update, archive.
2. Manage Courses: code, title, credits, capacity, status.
3. Enrollment: add/drop student ↔ course, check capacity, avoid duplicates.
4. Instructors: assign to courses (1..N); track office/contact.
5. Attendance & Grades: record per enrollment; compute GPA.
6. Search/filter: by name, course, semester, status.
7. Audit logs: who did what & when (admin endpoints).
8. Authentication (future): roles – admin, instructor, registrar.

## Non‑Functional
- **Security**: parameterized queries, validation, rate limiting, CORS.
- **Reliability**: migrations, seed data, structured logging.
- **Performance**: indexes on join/search fields.
- **Observability**: health endpoint `/health`, request logging.
- **Portability**: Docker (future) and `.env` configuration.

# Architecture

## Overview
A thin Express API exposing CRUD + workflow endpoints backed by a relational database. SQL kept in `/database` for transparency and portability.

## Components
- **API Layer**: Express routers per resource (students, courses, enrollments, instructors).
- **Service Layer**: input validation, transactions, domain rules (capacity checks, GPA calc).
- **Data Layer**: parameterized SQL; `pg` client.

## Request lifecycle
1. Request → Router matches → Controller parses & validates.
2. Service enforces rules in a DB transaction if needed.
3. Repository runs SQL and maps rows → DTOs.
4. Response → consistent JSON envelope.

## Error model
```json
{
  "ok": false,
  "error": { "code": "COURSE_FULL", "message": "Course is at capacity" }
}

## Future work

---

## docs/erd.md

```markdown
# Entity‑Relationship Diagram

```mermaid
erDiagram
  STUDENT ||--o{ ENROLLMENT : "enrolls"
  COURSE  ||--o{ ENROLLMENT : "has"
  INSTRUCTOR ||--o{ COURSE : "teaches"

  STUDENT {
    uuid id PK
    string first_name
    string last_name
    string email UNIQUE
    date   dob
    string status  "active|archived"
    timestamp created_at
    timestamp updated_at
  }

  COURSE {
    uuid id PK
    string code UNIQUE
    string title
    int    credits
    int    capacity
    string semester
    string status  "open|closed|archived"
  }

  INSTRUCTOR {
    uuid id PK
    string first_name
    string last_name
    string email UNIQUE
    string office
  }

  ENROLLMENT {
    uuid id PK
    uuid student_id FK
    uuid course_id  FK
    string grade    "A-F|null"
    int    attendance_percent
    timestamp created_at
  }

---

## docs/sequence-enrollment.md

```markdown
# Sequence – Enroll a student in a course

```mermaid
sequenceDiagram
  participant C as Client
  participant API as Express API
  participant SVC as EnrollmentService
  participant DB as Database

  C->>API: POST /enrollments { studentId, courseId }
  API->>SVC: validate + call
  SVC->>DB: SELECT capacity, count(*) FROM enrollments WHERE course_id = ?
  DB-->>SVC: rows
  SVC->>SVC: if count >= capacity → error
  SVC->>DB: INSERT INTO enrollments ...
  DB-->>SVC: inserted row
  SVC-->>API: dto
  API-->>C: 201 { ok: true, data }

---

## docs/api.md

```markdown
# API (Human‑Readable)
Base URL: `http://localhost:3000`

## Students
- `GET /students` – list
- `POST /students` – create
- `GET /students/:id` – get by id
- `PATCH /students/:id` – update
- `DELETE /students/:id` – archive

### Create student
```bash
curl -X POST http://localhost:3000/students \
  -H 'Content-Type: application/json' \
  -d '{"firstName":"Ada","lastName":"Lovelace","email":"ada@uni.ac.uk"}'

## Health

---

## docs/openapi.yaml (stub)

```yaml
openapi: 3.1.0
info:
  title: Student Management System API
  version: 0.1.0
servers:
  - url: http://localhost:3000
paths:
  /students:
    get:
      summary: List students
      responses:
        '200': { description: OK }
    post:
      summary: Create student
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/NewStudent'
      responses:
        '201': { description: Created }
  /students/{id}:
    get:
      summary: Get student by id
      parameters:
        - in: path
          name: id
          required: true
          schema: { type: string, format: uuid }
      responses:
        '200': { description: OK }
    patch:
      summary: Update student
      parameters:
        - in: path
          name: id
          required: true
          schema: { type: string, format: uuid }
      requestBody:
        required: true
        content:
          application/json:
            schema: { $ref: '#/components/schemas/UpdateStudent' }
      responses:
        '200': { description: Updated }
    delete:
      summary: Archive student
      parameters:
        - in: path
          name: id
          required: true
          schema: { type: string, format: uuid }
      responses:
        '204': { description: No Content }
  /courses:
    get: { summary: List courses, responses: { '200': { description: OK } } }
    post:
      summary: Create course
      requestBody:
        required: true
        content:
          application/json:
            schema: { $ref: '#/components/schemas/NewCourse' }
      responses:
        '201': { description: Created }
  /courses/{id}:
    get: { summary: Get course, responses: { '200': { description: OK } } }
    patch: { summary: Update course, responses: { '200': { description: OK } } }
    delete: { summary: Archive course, responses: { '204': { description: No Content } } }
  /enrollments:
    post:
      summary: Enroll a student in a course
      requestBody:
        required: true
        content:
          application/json:
            schema: { $ref: '#/components/schemas/NewEnrollment' }
      responses:
        '201': { description: Created }
  /enrollments/{id}:
    delete: { summary: Drop enrollment, responses: { '204': { description: No Content } } }
  /health:
    get: { summary: Health check, responses: { '200': { description: OK } } }

components:
  schemas:
    Student:
      type: object
      properties:
        id: { type: string, format: uuid }
        firstName: { type: string }
        lastName: { type: string }
        email: { type: string, format: email }
        status: { type: string, enum: [active, archived] }
    NewStudent:
      type: object
      required: [firstName, lastName, email]
      properties:
        firstName: { type: string }
        lastName: { type: string }
        email: { type: string, format: email }
    UpdateStudent:
      type: object
      properties:
        firstName: { type: string }
        lastName: { type: string }
        email: { type: string, format: email }
        status: { type: string, enum: [active, archived] }
    Course:
      type: object
      properties:
        id: { type: string, format: uuid }
        code: { type: string }
        title: { type: string }
        credits: { type: integer }
        capacity: { type: integer }
        semester: { type: string }
        status: { type: string, enum: [open, closed, archived] }
    NewCourse:
      type: object
      required: [code, title, credits]
      properties:
        code: { type: string }
        title: { type: string }
        credits: { type: integer }
        capacity: { type: integer }
        semester: { type: string }
    NewEnrollment:
      type: object
      required: [studentId, courseId]
      properties:
        studentId: { type: string, format: uuid }
        courseId: { type: string, format: uuid }

## Upload to GitHub (first time)

```bash
git init
git add .
git commit -m "Initial commit: SMS starter"
git branch -M main
git remote add origin https://github.com/<your-username>/<your-repo>.git
git push -u origin main
```

## Scripts

- `npm run dev` – start API with nodemon
- `npm test` – placeholder test script
