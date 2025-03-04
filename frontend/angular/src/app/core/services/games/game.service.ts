import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Game } from '../../models/games/game.model';
import { environment } from '../../../../enviroments/environment';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  private apiUrl = `${environment.apiUrl}games`;

  constructor(private http: HttpClient) {}

  getGames(): Observable<Game[]> {
    return this.http.get<Game[]>(this.apiUrl).pipe(
      catchError(this.handleError)
    );
  }

  updateGame(id: number, game: Game): Observable<Game> {
    return this.http.put<Game>(`${this.apiUrl}/${id}`, game).pipe(
      catchError(this.handleError)
    );
  }

  deleteGame(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  registerGame(game: { titulo: string }): Observable<Game> {
    return this.http.post<Game>(this.apiUrl, game).pipe(
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
      errorMessage = error.error.message || 'An unknown error occurred!';
    }
    return throwError(errorMessage);
  }
}