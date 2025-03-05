import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { GameService } from '../../../../core/services/games/game.service';
import { ClientService } from '../../../../core/services/clients/client.service';
import { Game } from '../../../../core/models/games/game.model';
import { Client } from '../../../../core/models/clients/client.model';

@Component({
  selector: 'app-prestamo-filters',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './prestamo-filters.component.html',
  styleUrls: ['./prestamo-filters.component.css']
})
export class PrestamoFiltersComponent implements OnInit {
  nombreJuego: string = '';
  nombreCliente: string = '';
  fecha: string = '';
  juegos: Game[] = [];
  clientes: Client[] = [];

  @Output() filter = new EventEmitter<{ nombreJuego: string, nombreCliente: string, fecha: string }>();
  @Output() clear = new EventEmitter<void>();

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