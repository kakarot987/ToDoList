export type TaskStatus = 'PENDING' | 'IN_PROGRESS' | 'COMPLETED';

export interface Todo {
  id: number;
  taskName: string;
  label: string;
  status: TaskStatus;
  dueDate: string; // ISO date string yyyy-MM-dd
}

export interface TodoRequest {
  taskName: string;
  label: string;
  status: TaskStatus;
  dueDate: string;
}
