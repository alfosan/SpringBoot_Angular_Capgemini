import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Client } from '../../../../core/models/clients/client.model';

@Component({
  selector: 'app-client-delete',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './client-delete.component.html',
  styleUrls: ['./client-delete.component.css']
})
export class ClientDeleteComponent {
  @Input() client: Client | null = null;
  @Output() delete = new EventEmitter<number>();
  @Output() close = new EventEmitter<void>();

  onDelete() {
    if (this.client) {
      this.delete.emit(this.client.id);
    }
  }

  onClose() {
    this.close.emit();
  }
}