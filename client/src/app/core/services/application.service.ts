import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Application, CreateApplicationRequest } from '../models/application.models';
import { ApiResponse } from '../models/common.models';

@Injectable({
  providedIn: 'root'
})
export class ApplicationService {
  private http = inject(HttpClient);
  private apiUrl = `${environment.apiUrl}/applications`;

  getAll(): Observable<Application[]> {
    return this.http.get<ApiResponse<Application[]>>(this.apiUrl).pipe(
      map(response => response.data)
    );
  }

  getById(id: string): Observable<Application> {
    return this.http.get<ApiResponse<Application>>(`${this.apiUrl}/${id}`).pipe(
      map(response => response.data)
    );
  }

  create(data: CreateApplicationRequest): Observable<Application> {
    return this.http.post<ApiResponse<Application>>(this.apiUrl, data).pipe(
      map(response => response.data)
    );
  }

  update(id: string, data: CreateApplicationRequest): Observable<Application> {
    return this.http.put<ApiResponse<Application>>(`${this.apiUrl}/${id}`, data).pipe(
      map(response => response.data)
    );
  }

  delete(id: string): Observable<void> {
    return this.http.delete<ApiResponse<void>>(`${this.apiUrl}/${id}`).pipe(
      map(response => response.data)
    );
  }
}
