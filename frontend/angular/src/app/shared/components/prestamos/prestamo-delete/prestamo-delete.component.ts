import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Prestamo } from '../../../../core/models/prestamos/prestamo.model';

@Component({
  selector: 'app-prestamo-delete',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './prestamo-delete.component.html',
  styleUrls: ['./prestamo-delete.component.css']
})
export class PrestamoDeleteComponent {
  @Input() prestamo: Prestamo | null = null;
  @Output() delete = new EventEmitter<number>();
  @Output() close = new EventEmitter<void>();

  onDelete() {
    if (this.prestamo) {
      this.delete.emit(this.prestamo.id);
    }
  }

  onClose() {
    this.close.emit();
  }
}