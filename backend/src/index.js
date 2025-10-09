
import express from 'express';
import morgan from 'morgan';
import cors from 'cors';
import dotenv from 'dotenv';

dotenv.config();
const app = express();
app.use(morgan('dev'));
app.use(cors());
app.use(express.json());

const prefix = '/api/v1';

// In-memory demo store
let students = [
  { id: 1, student_no: 'S1001', first_name: 'Ada', last_name: 'Lovelace', email: 'ada@example.edu' },
  { id: 2, student_no: 'S1002', first_name: 'Alan', last_name: 'Turing', email: 'alan@example.edu' }
];

app.get('/', (_req, res) => res.json({ ok: true, service: 'Student Management System API' }));

// Students
app.get(`${prefix}/students`, (req, res) => {
  const q = (req.query.q || '').toString().toLowerCase();
  const data = q ? students.filter(s => Object.values(s).join(' ').toLowerCase().includes(q)) : students;
  res.json({ data });
});

app.get(`${prefix}/students/:id`, (req, res) => {
  const s = students.find(x => x.id === Number(req.params.id));
  if (!s) return res.status(404).json({ error: 'Not found' });
  res.json(s);
});

app.post(`${prefix}/students`, (req, res) => {
  const nextId = Math.max(0, ...students.map(s => s.id)) + 1;
  const s = { id: nextId, ...req.body };
  students.push(s);
  res.status(201).json(s);
});

app.put(`${prefix}/students/:id`, (req, res) => {
  const id = Number(req.params.id);
  const idx = students.findIndex(s => s.id === id);
  if (idx === -1) return res.status(404).json({ error: 'Not found' });
  students[idx] = { ...students[idx], ...req.body };
  res.json(students[idx]);
});

app.delete(`${prefix}/students/:id`, (req, res) => {
  const id = Number(req.params.id);
  const before = students.length;
  students = students.filter(s => s.id != id);
  if (students.length === before) return res.status(404).json({ error: 'Not found' });
  res.json({ ok: true });
});

const port = process.env.PORT || 3000;
app.listen(port, () => console.log(`SMS API listening on http://localhost:${port}`));
