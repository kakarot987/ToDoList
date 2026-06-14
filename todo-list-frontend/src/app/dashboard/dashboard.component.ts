import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Todo, TodoRequest } from '../core/models/todo.model';
import { TodoService } from '../core/services/todo.service';
import { TaskFormComponent } from './componets/task-form/task-form.component';
import { TaskCardComponent } from './componets/task-card/task-card.component';
import { TaskFilterComponent, FilterOption } from './componets/task-filter/task-filter.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, TaskFormComponent, TaskCardComponent, TaskFilterComponent],
  templateUrl: './dashboard.component.html',
})
export class DashboardComponent implements OnInit {
  tasks: Todo[] = [];
  editingTask: Todo | null = null;
  activeFilter: FilterOption = 'ALL';
  loading = false;
  errorMsg = '';
  successMsg = '';

  constructor(private todoService: TodoService) {}

  ngOnInit(): void {
    this.loadTasks();
  }

  loadTasks(): void {
    this.loading = true;
    this.todoService.getAll().subscribe({
      next: (data) => { this.tasks = data; this.loading = false; },
      error: () => { this.errorMsg = 'Failed to load tasks.'; this.loading = false; }
    });
  }

  onSubmit(request: TodoRequest): void {
    if (this.editingTask) {
      this.todoService.update(this.editingTask.id, request).subscribe({
        next: (updated) => {
          this.tasks = this.tasks.map(t => t.id === updated.id ? updated : t);
          this.editingTask = null;
          this.flash('Task updated!');
        },
        error: () => this.errorMsg = 'Failed to update task.'
      });
    } else {
      this.todoService.create(request).subscribe({
        next: (created) => {
          this.tasks = [created, ...this.tasks];
          this.flash('Task added!');
        },
        error: () => this.errorMsg = 'Failed to create task.'
      });
    }
  }

  onEdit(task: Todo): void {
    this.editingTask = task;
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  onDelete(id: number): void {
    if (!confirm('Delete this task?')) return;
    this.todoService.delete(id).subscribe({
      next: () => {
        this.tasks = this.tasks.filter(t => t.id !== id);
        this.flash('Task deleted.');
      },
      error: () => this.errorMsg = 'Failed to delete task.'
    });
  }

  onFilterChange(filter: FilterOption): void {
    this.activeFilter = filter;
  }

  onCancelEdit(): void {
    this.editingTask = null;
  }

  get filteredTasks(): Todo[] {
    if (this.activeFilter === 'ALL') return this.tasks;
    return this.tasks.filter(t => t.status === this.activeFilter);
  }

  get stats() {
    return {
      total: this.tasks.length,
      pending: this.tasks.filter(t => t.status === 'PENDING').length,
      inProgress: this.tasks.filter(t => t.status === 'IN_PROGRESS').length,
      completed: this.tasks.filter(t => t.status === 'COMPLETED').length,
    };
  }

  private flash(msg: string): void {
    this.successMsg = msg;
    this.errorMsg = '';
    setTimeout(() => this.successMsg = '', 3000);
  }

  trackById(_: number, task: Todo): number {
    return task.id;
  }
}
