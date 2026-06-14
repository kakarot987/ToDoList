// src/app/shared/pipes/replace.pipe.ts
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'replace', standalone: true })
export class ReplacePipe implements PipeTransform {
  transform(value: string): string {
    return value.replace(/_/g, ' ');
  }
}