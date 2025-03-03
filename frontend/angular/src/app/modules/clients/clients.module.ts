import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClientsRoutingModule } from './clients-routing.module';
import { ClientListComponent } from '../../shared/components/clients/client-list/client-list.component';

@NgModule({
  imports: [
    CommonModule,
    ClientsRoutingModule,
    ClientListComponent 
  ]
})
export class ClientsModule { }