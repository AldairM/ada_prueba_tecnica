import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { Company, CreateCompanyRequest } from '../models/company.models';
import { environment } from '../../../environments/environment';
import { ApiResponse } from '../models/common.models';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {
  private http = inject(HttpClient);
  private apiUrl = `${environment.apiUrl}/companies`;

  getAll(): Observable<Company[]> {
    return this.http.get<ApiResponse<Company[]>>(this.apiUrl).pipe(
      map(response => response.data)
    );
  }

  getById(id: string): Observable<Company> {
    return this.http.get<ApiResponse<Company>>(`${this.apiUrl}/${id}`).pipe(
      map(response => response.data)
    );
  }

  create(data: CreateCompanyRequest): Observable<Company> {
    return this.http.post<ApiResponse<Company>>(this.apiUrl, data).pipe(
      map(response => response.data)
    );
  }

  update(id: string, data: CreateCompanyRequest): Observable<Company> {
    return this.http.put<ApiResponse<Company>>(`${this.apiUrl}/${id}`, data).pipe(
      map(response => response.data)
    );
  }

  delete(id: string): Observable<void> {
    return this.http.delete<ApiResponse<void>>(`${this.apiUrl}/${id}`).pipe(
      map(response => response.data)
    );
  }
}
