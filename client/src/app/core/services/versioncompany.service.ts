import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { VersionCompany, CreateVersionCompanyRequest } from '../models/versioncompany.models';
import { environment } from '../../../environments/environment';
import { ApiResponse } from '../models/common.models';

@Injectable({
  providedIn: 'root'
})
export class VersionCompanyService {
  private http = inject(HttpClient);
  private apiUrl = `${environment.apiUrl}/versioncompanies`;

  getAll(): Observable<VersionCompany[]> {
    return this.http.get<ApiResponse<VersionCompany[]>>(this.apiUrl).pipe(
      map(response => response.data)
    );
  }

  getById(id: string): Observable<VersionCompany> {
    return this.http.get<ApiResponse<VersionCompany>>(`${this.apiUrl}/${id}`).pipe(
      map(response => response.data)
    );
  }

  create(data: CreateVersionCompanyRequest): Observable<VersionCompany> {
    return this.http.post<ApiResponse<VersionCompany>>(this.apiUrl, data).pipe(
      map(response => response.data)
    );
  }

  update(id: string, data: CreateVersionCompanyRequest): Observable<VersionCompany> {
    return this.http.put<ApiResponse<VersionCompany>>(`${this.apiUrl}/${id}`, data).pipe(
      map(response => response.data)
    );
  }

  delete(id: string): Observable<void> {
    return this.http.delete<ApiResponse<void>>(`${this.apiUrl}/${id}`).pipe(
      map(response => response.data)
    );
  }
}
