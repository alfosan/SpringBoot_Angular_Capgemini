import { Component, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Prestamo } from '../../../../core/models/prestamos/prestamo.model';

@Component({
  selector: 'app-prestamo-create',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './prestamo-create.component.html',
  styleUrls: ['./prestamo-create.component.css']
})
export class PrestamoCreateComponent {
  nombreJuego: string = '';
  nombreCliente: string = '';
  fechaCreacion: string = '';
  fechaDevolucion: string = '';

  @Output() create = new EventEmitter<Partial<Prestamo>>();
  @Output() close = new EventEmitter<void>();

  onCreate() {
    const newPrestamo: Partial<Prestamo> = {
      nombreJuego: this.nombreJuego,
      nombreCliente: this.nombreCliente,
      fechaCreacion: this.fechaCreacion,
      fechaDevolucion: this.fechaDevolucion
    };
    this.create.emit(newPrestamo);
  }

  onClose() {
    this.close.emit();
  }
}