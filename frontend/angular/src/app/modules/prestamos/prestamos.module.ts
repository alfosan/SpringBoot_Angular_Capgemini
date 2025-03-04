import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { PrestamosRoutingModule } from './prestamos-routing.module';
import { PrestamoListComponent } from '../../shared/components/prestamos/prestamo-list/prestamo-list.component';

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    PrestamosRoutingModule,
    PrestamoListComponent
  ]
})
export class PrestamosModule { }