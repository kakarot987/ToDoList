import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TaskStatus } from '../../../core/models/todo.model';

export type FilterOption = TaskStatus | 'ALL';

@Component({
  selector: 'app-task-filter',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="filter-bar">
      <button
        *ngFor="let f of filters"
        class="filter-btn"
        [class.active]="current === f.value"
        (click)="select(f.value)"
      >
        <span class="filter-dot" [class]="'dot-' + f.value.toLowerCase()"></span>
        {{ f.label }}
      </button>
    </div>
  `
})
export class TaskFilterComponent {
  @Output() filterChanged = new EventEmitter<FilterOption>();

  current: FilterOption = 'ALL';

  filters: { label: string; value: FilterOption }[] = [
    { label: 'All', value: 'ALL' },
    { label: 'Pending', value: 'PENDING' },
    { label: 'In Progress', value: 'IN_PROGRESS' },
    { label: 'Completed', value: 'COMPLETED' },
  ];

  select(value: FilterOption): void {
    this.current = value;
    this.filterChanged.emit(value);
  }
}
