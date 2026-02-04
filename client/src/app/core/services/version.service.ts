import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { environment } from '../../../environments/environment';
import { ApiResponse } from '../models/common.models';
import { Version, CreateVersionRequest } from '../models/version.models';

@Injectable({
  providedIn: 'root'
})
export class VersionService {
  private http = inject(HttpClient);
  private apiUrl = `${environment.apiUrl}/versions`;

  getAll(): Observable<Version[]> {
    return this.http.get<ApiResponse<Version[]>>(this.apiUrl).pipe(
      map(response => response.data)
    );
  }

  getById(id: string): Observable<Version> {
    return this.http.get<ApiResponse<Version>>(`${this.apiUrl}/${id}`).pipe(
      map(response => response.data)
    );
  }

  create(data: CreateVersionRequest): Observable<Version> {
    return this.http.post<ApiResponse<Version>>(this.apiUrl, data).pipe(
      map(response => response.data)
    );
  }

  update(id: string, data: CreateVersionRequest): Observable<Version> {
    return this.http.put<ApiResponse<Version>>(`${this.apiUrl}/${id}`, data).pipe(
      map(response => response.data)
    );
  }

  delete(id: string): Observable<void> {
    return this.http.delete<ApiResponse<void>>(`${this.apiUrl}/${id}`).pipe(
      map(response => response.data)
    );
  }
}
