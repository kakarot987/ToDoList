import { Component, EventEmitter, Input, OnChanges, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Todo, TodoRequest, TaskStatus } from '../../../core/models/todo.model';
import { ReplacePipe } from '../../../shared/pipes/replace.pipe';

@Component({
  selector: 'app-task-form',
  standalone: true,
  imports: [CommonModule, FormsModule, ReplacePipe],
  templateUrl: './task-form.component.html',
})
export class TaskFormComponent implements OnChanges {
  @Input() editTask: Todo | null = null;
  @Output() submitted = new EventEmitter<TodoRequest>();
  @Output() cancelled = new EventEmitter<void>();

  form: TodoRequest = this.emptyForm();

  ngOnChanges(): void {
    if (this.editTask) {
      this.form = {
        taskName: this.editTask.taskName,
        label: this.editTask.label,
        status: this.editTask.status,
        dueDate: this.editTask.dueDate,
      };
    } else {
      this.form = this.emptyForm();
    }
  }

  submit(): void {
    if (!this.form.taskName.trim()) return;
    this.submitted.emit({ ...this.form });
    this.form = this.emptyForm();
  }

  cancel(): void {
    this.form = this.emptyForm();
    this.cancelled.emit();
  }

  private emptyForm(): TodoRequest {
    return { taskName: '', label: '', status: 'PENDING', dueDate: '' };
  }

  get isEditing(): boolean {
    return !!this.editTask;
  }

  readonly statuses: TaskStatus[] = ['PENDING', 'IN_PROGRESS', 'COMPLETED'];
}
