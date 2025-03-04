import { Component, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Game } from '../../../../core/models/games/game.model';

@Component({
  selector: 'app-game-create',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './game-create.component.html',
  styleUrls: ['./game-create.component.css']
})
export class GameCreateComponent {
  gameTitle: string = '';
  @Output() create = new EventEmitter<{ titulo: string }>();
  @Output() close = new EventEmitter<void>();

  onSubmit() {
    const newGame = { titulo: this.gameTitle };
    this.create.emit(newGame);
  }

  onClose() {
    this.close.emit();
  }
}