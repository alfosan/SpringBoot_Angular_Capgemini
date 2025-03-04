import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GamesRoutingModule } from './games-routing.module';
import { GameListComponent } from '../../shared/components/games/game-list/game-list.component';

@NgModule({
  imports: [
    CommonModule,
    GamesRoutingModule,
    GameListComponent
  ]
})
export class GamesModule { }