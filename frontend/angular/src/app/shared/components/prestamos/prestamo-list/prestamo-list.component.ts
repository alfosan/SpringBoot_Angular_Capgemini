import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PrestamoService } from '../../../../core/services/prestamos/prestamo.service';
import { Prestamo } from '../../../../core/models/prestamos/prestamo.model';
import { PrestamoFiltersComponent } from '../prestamo-filters/prestamo-filters.component';
import { PrestamoPaginationComponent } from '../prestamo-pagination/prestamo-pagination.component';
import { PrestamoCreateComponent } from '../prestamos-create/prestamo-create.component';
import { PrestamoDeleteComponent } from '../prestamo-delete/prestamo-delete.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-prestamo-list',
  standalone: true,
  imports: [CommonModule, PrestamoFiltersComponent, PrestamoPaginationComponent, PrestamoCreateComponent, PrestamoDeleteComponent],
  templateUrl: './prestamo-list.component.html',
  styleUrls: ['./prestamo-list.component.css']
})
export class PrestamoListComponent implements OnInit {
  prestamos: Prestamo[] = [];
  totalPages: number = 0;
  currentPage: number = 0;
  filters: { nombreJuego: string, nombreCliente: string, fecha: string } = { nombreJuego: '', nombreCliente: '', fecha: '' };
  showCreateModal: boolean = false;
  showDeleteModal: boolean = false;
  prestamoToDelete: Prestamo | null = null;

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
      (error: any) => {
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

  openCreateModal() {
    this.showCreateModal = true;
  }

  closeCreateModal() {
    this.showCreateModal = false;
  }

  createPrestamo(prestamo: Partial<Prestamo>) {
    this.prestamoService.createPrestamo(prestamo).subscribe(
      (newPrestamo: Prestamo) => {
        this.prestamos.push(newPrestamo);
        this.closeCreateModal();
      },
      (error: any) => {
        this.showError(error);
      }
    );
  }

  openDeleteModal(prestamo: Prestamo) {
    this.prestamoToDelete = prestamo;
    this.showDeleteModal = true;
  }

  closeDeleteModal() {
    this.showDeleteModal = false;
    this.prestamoToDelete = null;
  }

  deletePrestamo(id: number) {
    this.prestamoService.deletePrestamo(id).subscribe(
      () => {
        this.prestamos = this.prestamos.filter(prestamo => prestamo.id !== id);
        this.closeDeleteModal();
      },
      (error: any) => {
        this.showError(error);
      }
    );
  }

  private showError(error: any) {
    const errorMessage = error || 'An unknown error occurred!';
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: errorMessage,
      confirmButtonText: 'OK'
    });
  }
}