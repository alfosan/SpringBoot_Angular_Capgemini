import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Prestamo } from '../../models/prestamos/prestamo.model';
import { environment } from '../../../../enviroments/environment';

@Injectable({
  providedIn: 'root'
})
export class PrestamoService {
  private apiUrl = `${environment.apiUrl}prestamos`;

  constructor(private http: HttpClient) {}

  getPrestamos(page: number, size: number = 4): Observable<any> {
    let params = new HttpParams().set('page', page).set('size', size);
    return this.http.get<any>(`${this.apiUrl}/filter`, { params }).pipe(
      catchError(this.handleError)
    );
  }

  filterPrestamos(nombreJuego: string, nombreCliente: string, fecha: string, page: number, size: number = 4): Observable<any> {
    let params = new HttpParams().set('page', page).set('size', size);
    if (nombreJuego) {
      params = params.set('nombreJuego', nombreJuego);
    }
    if (nombreCliente) {
      params = params.set('nombreCliente', nombreCliente);
    }
    if (fecha) {
      params = params.set('fecha', fecha);
    }
    return this.http.get<any>(`${this.apiUrl}/filter`, { params }).pipe(
      catchError(this.handleError)
    );
  }

  createPrestamo(prestamo: Partial<Prestamo>): Observable<Prestamo> {
    return this.http.post<Prestamo>(this.apiUrl, prestamo).pipe(
      catchError(this.handleError)
    );
  }

  deletePrestamo(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'An unknown error occurred!';
    if (error.error instanceof ErrorEvent) {
      // Client-side or network error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Backend error
      errorMessage = error.error.message || error.message || 'An unknown error occurred!';
    }
    return throwError(errorMessage);
  }
}