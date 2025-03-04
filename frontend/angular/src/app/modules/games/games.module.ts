import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { GamesRoutingModule } from './games-routing.module';
import { GameListComponent } from '../../shared/components/games/game-list/game-list.component';

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    GamesRoutingModule,
    GameListComponent
  ]
})
export class GamesModule { }