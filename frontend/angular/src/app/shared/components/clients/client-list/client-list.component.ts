import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClientService } from '../../../../core/services/clients/client.service';
import { Client } from '../../../../core/models/clients/client.model';
import { ClientUpdateComponent } from '../client-update/client-update.component';

@Component({
  selector: 'app-client-list',
  standalone: true,
  imports: [CommonModule, ClientUpdateComponent],
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {
  clients: Client[] = [];
  selectedClient: Client | null = null;

  constructor(private clientService: ClientService) {}

  ngOnInit(): void {
    this.clientService.getClients().subscribe((data: Client[]) => {
      this.clients = data;
    });
  }

  openUpdateModal(client: Client) {
    this.selectedClient = { ...client };
  }

  closeUpdateModal() {
    this.selectedClient = null;
  }

  updateClient(updatedClient: Client) {
    // Aquí puedes llamar a un servicio para actualizar el cliente en el backend
    const index = this.clients.findIndex(client => client.id === updatedClient.id);
    if (index !== -1) {
      this.clients[index] = updatedClient;
    }
    this.closeUpdateModal();
  }
}