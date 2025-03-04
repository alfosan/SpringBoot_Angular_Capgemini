import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClientService } from '../../../../core/services/clients/client.service';
import { Client } from '../../../../core/models/clients/client.model';
import { ClientUpdateComponent } from '../client-update/client-update.component';
import { ClientDeleteComponent } from '../client-delete/client-delete.component';
import { ClientRegisterComponent } from '../client-register/client-register.component';

@Component({
  selector: 'app-client-list',
  standalone: true,
  imports: [CommonModule, ClientUpdateComponent, ClientDeleteComponent, ClientRegisterComponent],
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {
  clients: Client[] = [];
  selectedClient: Client | null = null;
  clientToDelete: Client | null = null;
  showRegisterModal: boolean = false;

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
    if (this.selectedClient) {
      this.clientService.updateClient(this.selectedClient.id, updatedClient).subscribe((client: Client) => {
        const index = this.clients.findIndex(c => c.id === client.id);
        if (index !== -1) {
          this.clients[index] = client;
        }
        this.closeUpdateModal();
      });
    }
  }

  openDeleteModal(client: Client) {
    this.clientToDelete = { ...client };
  }

  closeDeleteModal() {
    this.clientToDelete = null;
  }

  deleteClient(clientId: number) {
    this.clientService.deleteClient(clientId).subscribe(() => {
      this.clients = this.clients.filter(client => client.id !== clientId);
      this.closeDeleteModal();
    });
  }

  openRegisterModal() {
    this.showRegisterModal = true;
  }

  closeRegisterModal() {
    this.showRegisterModal = false;
  }

  registerClient(newClient: Client) {
    this.clientService.registerClient(newClient).subscribe((client: Client) => {
      this.clients.push(client);
      this.closeRegisterModal();
    });
  }
}