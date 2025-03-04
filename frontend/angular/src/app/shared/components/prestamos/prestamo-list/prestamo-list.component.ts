import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PrestamoService } from '../../../../core/services/prestamos/prestamo.service';
import { Prestamo } from '../../../../core/models/prestamos/prestamo.model';
import { PrestamoFiltersComponent } from '../prestamo-filters/prestamo-filters.component';
import { PrestamoPaginationComponent } from '../prestamo-pagination/prestamo-pagination.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-prestamo-list',
  standalone: true,
  imports: [CommonModule, PrestamoFiltersComponent, PrestamoPaginationComponent],
  templateUrl: './prestamo-list.component.html',
  styleUrls: ['./prestamo-list.component.css']
})
export class PrestamoListComponent implements OnInit {
  prestamos: Prestamo[] = [];
  totalPages: number = 0;
  currentPage: number = 0;
  filters: { nombreJuego: string, nombreCliente: string, fecha: string } = { nombreJuego: '', nombreCliente: '', fecha: '' };

  constructor(private prestamoService: PrestamoService) {}

  ngOnInit(): void {
    this.loadPrestamos();
  }

  loadPrestamos() {
    this.prestamoService.filterPrestamos(this.filters.nombreJuego, this.filters.nombreCliente, this.filters.fecha, this.currentPage, 4).subscribe(
      (data: any) => {
        this.prestamos = data.content;
        this.totalPages = data.totalPages;
      },
      (error: string) => {
        this.showError(error);
      }
    );
  }

  onFilter(filters: { nombreJuego: string, nombreCliente: string, fecha: string }) {
    this.filters = filters;
    this.currentPage = 0;
    this.loadPrestamos();
  }

  onClear() {
    this.filters = { nombreJuego: '', nombreCliente: '', fecha: '' };
    this.currentPage = 0;
    this.loadPrestamos();
  }

  onPageChange(page: number) {
    this.currentPage = page;
    this.loadPrestamos();
  }

  private showError(error: string) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: error,
      confirmButtonText: 'OK'
    });
  }
}