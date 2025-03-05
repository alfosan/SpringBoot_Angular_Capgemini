import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Prestamo } from '../../../../core/models/prestamos/prestamo.model';
import { GameService } from '../../../../core/services/games/game.service';
import { ClientService } from '../../../../core/services/clients/client.service';
import { Game } from '../../../../core/models/games/game.model';
import { Client } from '../../../../core/models/clients/client.model';

@Component({
  selector: 'app-prestamo-create',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './prestamo-create.component.html',
  styleUrls: ['./prestamo-create.component.css']
})
export class PrestamoCreateComponent implements OnInit {
  nombreJuego: string = '';
  nombreCliente: string = '';
  fechaCreacion: string = '';
  fechaDevolucion: string = '';
  juegos: Game[] = [];
  clientes: Client[] = [];

  @Output() create = new EventEmitter<Partial<Prestamo>>();
  @Output() close = new EventEmitter<void>();

  constructor(private gameService: GameService, private clientService: ClientService) {}

  ngOnInit(): void {
    this.loadGames();
    this.loadClients();
  }

  loadGames() {
    this.gameService.getGames().subscribe(
      (data: Game[]) => {
        this.juegos = data;
      },
      (error: any) => {
        console.error('Error loading games', error);
      }
    );
  }

  loadClients() {
    this.clientService.getClients().subscribe(
      (data: Client[]) => {
        this.clientes = data;
      },
      (error: any) => {
        console.error('Error loading clients', error);
      }
    );
  }

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