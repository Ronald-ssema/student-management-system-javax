
# API (v1) – Summary

Base URL: `/api/v1`

## Students
- `GET /students` – list with pagination & search
- `GET /students/{id}` – details
- `POST /students` – create
- `PUT /students/{id}` – update
- `DELETE /students/{id}` – delete (soft)

## Courses
- `GET /courses`
- `POST /courses`
- `GET /courses/{id}`

## Enrollments
- `POST /enrollments`
- `GET /students/{id}/enrollments`
- `DELETE /enrollments/{id}`

Full OpenAPI spec in `docs/openapi.yaml`.
