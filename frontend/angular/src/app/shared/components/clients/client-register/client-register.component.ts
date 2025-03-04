import { Component, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-client-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './client-register.component.html',
  styleUrls: ['./client-register.component.css']
})
export class ClientRegisterComponent {
  clientName: string = '';
  @Output() register = new EventEmitter<{ nombre: string }>();
  @Output() close = new EventEmitter<void>();

  onSubmit() {
    const newClient = { nombre: this.clientName }; // Enviar solo el nombre
    this.register.emit(newClient);
  }

  onClose() {
    this.close.emit();
  }
}