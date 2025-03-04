import { Routes } from '@angular/router';
import { ErrorComponent } from './shared/components/error/error.component';

export const routes: Routes = [
  { 
    path: '', 
    redirectTo: 'home', 
    pathMatch: 'full' 
  },
  {
    path: 'home',
    loadChildren: () => import('./modules/home/home.module').then(m => m.HomeModule)
  },
  {
    path: 'clients',
    loadChildren: () => import('./modules/clients/clients.module').then(m => m.ClientsModule)
  },
  {
    path: 'games',
    loadChildren: () => import('./modules/games/games.module').then(m => m.GamesModule)
  },
  {
    path: 'prestamos',
    loadChildren: () => import('./modules/prestamos/prestamos.module').then(m => m.PrestamosModule)
  },
  { 
    path: '404', 
    component: ErrorComponent 
  },
  { 
    path: '**', 
    redirectTo: '404' 
  },
];