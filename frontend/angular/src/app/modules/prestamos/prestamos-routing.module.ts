import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PrestamoListComponent } from '../../shared/components/prestamos/prestamo-list/prestamo-list.component';

const routes: Routes = [
  { path: '', component: PrestamoListComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrestamosRoutingModule { }