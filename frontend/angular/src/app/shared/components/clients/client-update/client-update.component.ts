import { Component, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Client } from '../../../../core/models/clients/client.model';

@Component({
  selector: 'app-client-update',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './client-update.component.html',
  styleUrls: ['./client-update.component.css']
})
export class ClientUpdateComponent implements OnChanges {
  @Input() client: Client | null = null;
  @Output() update = new EventEmitter<Client>();
  @Output() close = new EventEmitter<void>();

  clientName: string = '';

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['client'] && this.client) {
      this.clientName = this.client.nombre;
    }
  }

  onSubmit() {
    if (this.client) {
      this.client.nombre = this.clientName;
      this.update.emit(this.client);
    }
  }

  onClose() {
    this.close.emit();
  }
}