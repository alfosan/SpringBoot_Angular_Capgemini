import { Component, Output, EventEmitter, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-prestamo-pagination',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './prestamo-pagination.component.html',
  styleUrls: ['./prestamo-pagination.component.css']
})
export class PrestamoPaginationComponent {
  @Input() totalPages: number = 0;
  @Input() currentPage: number = 0;
  @Output() pageChange = new EventEmitter<number>();

  onPageChange(page: number) {
    this.pageChange.emit(page);
  }
}