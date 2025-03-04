import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GameService } from '../../../../core/services/games/game.service';
import { Game } from '../../../../core/models/games/game.model';
import { GameUpdateComponent } from '../game-update/game-update.component';
import { GameDeleteComponent } from '../game-delete/game-delete.component';
import { GameCreateComponent } from '../game-create/game-create.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-game-list',
  standalone: true,
  imports: [CommonModule, GameUpdateComponent, GameDeleteComponent, GameCreateComponent],
  templateUrl: './game-list.component.html',
  styleUrls: ['./game-list.component.css']
})
export class GameListComponent implements OnInit {
  games: Game[] = [];
  selectedGame: Game | null = null;
  gameToDelete: Game | null = null;
  showRegisterModal: boolean = false;

  constructor(private gameService: GameService) {}

  ngOnInit(): void {
    this.loadGames();
  }

  loadGames() {
    this.gameService.getGames().subscribe(
      (data: Game[]) => {
        this.games = data;
      },
      (error: string) => {
        this.showError(error);
      }
    );
  }

  openUpdateModal(game: Game) {
    this.selectedGame = { ...game };
  }

  closeUpdateModal() {
    this.selectedGame = null;
  }

  updateGame(updatedGame: Game) {
    if (this.selectedGame) {
      this.gameService.updateGame(this.selectedGame.id, updatedGame).subscribe(
        (game: Game) => {
          const index = this.games.findIndex(g => g.id === game.id);
          if (index !== -1) {
            this.games[index] = game;
          }
          this.closeUpdateModal();
        },
        (error: string) => {
          this.showError(error);
        }
      );
    }
  }

  openDeleteModal(game: Game) {
    this.gameToDelete = { ...game };
  }

  closeDeleteModal() {
    this.gameToDelete = null;
  }

  deleteGame(gameId: number) {
    this.gameService.deleteGame(gameId).subscribe(
      () => {
        this.games = this.games.filter(game => game.id !== gameId);
        this.closeDeleteModal();
      },
      (error: string) => {
        this.showError(error);
      }
    );
  }

  openRegisterModal() {
    this.showRegisterModal = true;
  }

  closeRegisterModal() {
    this.showRegisterModal = false;
  }

  registerGame(newGame: { titulo: string }) {
    this.gameService.registerGame(newGame).subscribe(
      (game: Game) => {
        this.games.push(game);
        this.closeRegisterModal();
      },
      (error: string) => {
        this.showError(error);
      }
    );
  }

  private showError(error: string) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: error,
      confirmButtonText: 'OK'
    });
  }
}