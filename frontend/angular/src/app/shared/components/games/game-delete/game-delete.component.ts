import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Game } from '../../../../core/models/games/game.model';

@Component({
  selector: 'app-game-delete',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './game-delete.component.html',
  styleUrls: ['./game-delete.component.css']
})
export class GameDeleteComponent {
  @Input() game: Game | null = null;
  @Output() delete = new EventEmitter<number>();
  @Output() close = new EventEmitter<void>();

  onDelete() {
    if (this.game) {
      this.delete.emit(this.game.id);
    }
  }

  onClose() {
    this.close.emit();
  }
}