import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Todo } from '../../../core/models/todo.model';

@Component({
  selector: 'app-task-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './task-card.component.html',
})
export class TaskCardComponent {
  @Input() task!: Todo;
  @Output() edit = new EventEmitter<Todo>();
  @Output() delete = new EventEmitter<number>();

  get statusClass(): string {
    return {
      PENDING: 'status-pending',
      IN_PROGRESS: 'status-progress',
      COMPLETED: 'status-done',
    }[this.task.status] ?? '';
  }

  get statusLabel(): string {
    return {
      PENDING: 'Pending',
      IN_PROGRESS: 'In Progress',
      COMPLETED: 'Completed',
    }[this.task.status] ?? this.task.status;
  }

  get isOverdue(): boolean {
    if (!this.task.dueDate || this.task.status === 'COMPLETED') return false;
    return new Date(this.task.dueDate) < new Date(new Date().toDateString());
  }

  get formattedDate(): string {
    if (!this.task.dueDate) return '—';
    return new Date(this.task.dueDate).toLocaleDateString('en-US', {
      month: 'short', day: 'numeric', year: 'numeric'
    });
  }
}
