import { Component, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-prestamo-filters',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './prestamo-filters.component.html',
  styleUrls: ['./prestamo-filters.component.css']
})
export class PrestamoFiltersComponent {
  nombreJuego: string = '';
  nombreCliente: string = '';
  fecha: string = '';

  @Output() filter = new EventEmitter<{ nombreJuego: string, nombreCliente: string, fecha: string }>();
  @Output() clear = new EventEmitter<void>();

  onFilter() {
    this.filter.emit({
      nombreJuego: this.nombreJuego,
      nombreCliente: this.nombreCliente,
      fecha: this.fecha
    });
  }

  onClear() {
    this.nombreJuego = '';
    this.nombreCliente = '';
    this.fecha = '';
    this.clear.emit();
  }
}