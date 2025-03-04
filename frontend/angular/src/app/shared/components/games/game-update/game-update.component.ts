import { Component, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Game } from '../../../../core/models/games/game.model';

@Component({
  selector: 'app-game-update',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './game-update.component.html',
  styleUrls: ['./game-update.component.css']
})
export class GameUpdateComponent implements OnChanges {
  @Input() game: Game | null = null;
  @Output() update = new EventEmitter<Game>();
  @Output() close = new EventEmitter<void>();

  gameTitle: string = '';

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['game'] && this.game) {
      this.gameTitle = this.game.titulo;
    }
  }

  onSubmit() {
    if (this.game) {
      this.game.titulo = this.gameTitle;
      this.update.emit(this.game);
    }
  }

  onClose() {
    this.close.emit();
  }
}