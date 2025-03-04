import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ClientsRoutingModule } from './clients-routing.module';
import { ClientListComponent } from '../../shared/components/clients/client-list/client-list.component';

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    ClientsRoutingModule,
    ClientListComponent
  ]
})
export class ClientsModule { }