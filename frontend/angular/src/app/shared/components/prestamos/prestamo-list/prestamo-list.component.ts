import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PrestamoService } from '../../../../core/services/prestamos/prestamo.service';
import { Prestamo } from '../../../../core/models/prestamos/prestamo.model';

@Component({
  selector: 'app-prestamo-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './prestamo-list.component.html',
  styleUrls: ['./prestamo-list.component.css']
})
export class PrestamoListComponent implements OnInit {
  prestamos: Prestamo[] = [];

  constructor(private prestamoService: PrestamoService) {}

  ngOnInit(): void {
    this.prestamoService.getPrestamos().subscribe((data: Prestamo[]) => {
      this.prestamos = data;
    });
  }
}