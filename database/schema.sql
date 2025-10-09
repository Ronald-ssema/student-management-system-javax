
-- SQL schema for Student Management System (PostgreSQL/MySQL compatible with slight tweaks)
CREATE TABLE students (
  id SERIAL PRIMARY KEY,
  student_no VARCHAR(20) UNIQUE NOT NULL,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  email VARCHAR(150) UNIQUE NOT NULL,
  dob DATE,
  status VARCHAR(20) DEFAULT 'active',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE lecturers (
  id SERIAL PRIMARY KEY,
  staff_no VARCHAR(20) UNIQUE NOT NULL,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  email VARCHAR(150) UNIQUE NOT NULL
);

CREATE TABLE courses (
  id SERIAL PRIMARY KEY,
  code VARCHAR(20) UNIQUE NOT NULL,
  name VARCHAR(150) NOT NULL,
  description TEXT
);

CREATE TABLE modules (
  id SERIAL PRIMARY KEY,
  course_id INT NOT NULL REFERENCES courses(id),
  code VARCHAR(20) UNIQUE NOT NULL,
  title VARCHAR(150) NOT NULL,
  lecturer_id INT REFERENCES lecturers(id)
);

CREATE TABLE enrollments (
  id SERIAL PRIMARY KEY,
  student_id INT NOT NULL REFERENCES students(id),
  course_id INT NOT NULL REFERENCES courses(id),
  enrolled_on DATE DEFAULT CURRENT_DATE,
  status VARCHAR(20) DEFAULT 'enrolled'
);

CREATE TABLE attendance (
  id SERIAL PRIMARY KEY,
  student_id INT NOT NULL REFERENCES students(id),
  module_id INT NOT NULL REFERENCES modules(id),
  session_date DATE NOT NULL,
  status VARCHAR(20) NOT NULL
);

CREATE TABLE grades (
  id SERIAL PRIMARY KEY,
  student_id INT NOT NULL REFERENCES students(id),
  module_id INT NOT NULL REFERENCES modules(id),
  score DECIMAL(5,2),
  letter VARCHAR(2),
  term VARCHAR(20)
);
