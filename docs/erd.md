
# Entity‑Relationship Diagram (ERD)

```mermaid
erDiagram
    STUDENT ||--o{ ENROLLMENT : enrolls
    COURSE  ||--o{ ENROLLMENT : has
    COURSE  ||--o{ MODULE : contains
    STUDENT ||--o{ ATTENDANCE : marks
    STUDENT ||--o{ GRADE : receives
    LECTURER ||--o{ MODULE : teaches

    STUDENT {
      int id PK
      string student_no UNIQUE
      string first_name
      string last_name
      string email UNIQUE
      date dob
      string status
      datetime created_at
    }

    LECTURER {
      int id PK
      string staff_no UNIQUE
      string first_name
      string last_name
      string email UNIQUE
    }

    COURSE {
      int id PK
      string code UNIQUE
      string name
      text description
    }

    MODULE {
      int id PK
      int course_id FK
      string code UNIQUE
      string title
      int lecturer_id FK
    }

    ENROLLMENT {
      int id PK
      int student_id FK
      int course_id FK
      date enrolled_on
      string status
    }

    ATTENDANCE {
      int id PK
      int student_id FK
      int module_id FK
      date session_date
      string status
    }

    GRADE {
      int id PK
      int student_id FK
      int module_id FK
      float score
      string letter
      string term
    }
```
