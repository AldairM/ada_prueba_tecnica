import { Component, OnInit, ViewChild, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';
import { MatPaginatorModule, MatPaginator } from '@angular/material/paginator';
import { MatSortModule, MatSort } from '@angular/material/sort';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { UserService } from '../../core/services/user.service';
import { User } from '../../core/models/user.models';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule
  ],
  template: `
    <div class="user-container">
      <mat-card>
        <mat-card-header>
          <mat-card-title>Usuarios</mat-card-title>
        </mat-card-header>
        
        <mat-card-content>
          <mat-form-field appearance="outline" class="filter-field">
            <mat-label>Buscar</mat-label>
            <input matInput (keyup)="applyFilter($event)" placeholder="Search by name, email, city..." #input>
          </mat-form-field>

          <div class="table-container mat-elevation-z8">
            <table mat-table [dataSource]="dataSource" matSort>
              <!-- Name Column -->
              <ng-container matColumnDef="name">
                <th mat-header-cell *matHeaderCellDef mat-sort-header> nombre </th>
                <td mat-cell *matCellDef="let element"> {{element.name}} </td>
              </ng-container>

              <!-- Phone Column -->
              <ng-container matColumnDef="phone">
                <th mat-header-cell *matHeaderCellDef mat-sort-header> Telefono </th>
                <td mat-cell *matCellDef="let element"> {{element.phone}} </td>
              </ng-container>

              <!-- Email Column -->
              <ng-container matColumnDef="email">
                <th mat-header-cell *matHeaderCellDef mat-sort-header> correo </th>
                <td mat-cell *matCellDef="let element"> {{element.email}} </td>
              </ng-container>
             

              <!-- Company Column -->
              <ng-container matColumnDef="company">
                <th mat-header-cell *matHeaderCellDef mat-sort-header> compañia </th>
                <td mat-cell *matCellDef="let element"> {{element.company.name}} </td>
              </ng-container>
          
              <tr mat-header-row *matHeaderRowDef="displayedColumns"></tr>
              <tr mat-row *matRowDef="let row; columns: displayedColumns;"></tr>

              <!-- Row shown when there is no matching data. -->
              <tr class="mat-row" *matNoDataRow>
                <td class="mat-cell" colspan="7">la información no coincide con el filtro "{{input.value}}"</td>
              </tr>
            </table>

            <mat-paginator [pageSizeOptions]="[5, 10, 25, 100]" aria-label="Seleccionar página de usuarios"></mat-paginator>
          </div>
        </mat-card-content>
      </mat-card>
    </div>
  `,
  styles: [`
    .user-container {
      padding: 20px;
    }
    .filter-field {
      width: 100%;
      margin-bottom: 20px;
    }
    .table-container {
      overflow: auto;
    }
    table {
      width: 100%;
    }
    .mat-column-id {
      width: 60px;
    }
  `]
})
export class UserComponent implements OnInit {
  private userService = inject(UserService);

  displayedColumns: string[] = ['name', 'phone', 'email', 'company'];
  dataSource = new MatTableDataSource<User>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  ngOnInit(): void {
    this.userService.getUsers().subscribe({
      next: (users) => {
        this.dataSource.data = users;
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;

        // Custom filter predicate to handle nested objects (address.city, company.name)
        this.dataSource.filterPredicate = (data: User, filter: string) => {
          const searchData = [
            data.name,
            data.email,
            data.username,
            data.address.city,
            data.company.name,
            data.website
          ].join(' ').toLowerCase();
          return searchData.includes(filter);
        };
      },
      error: (err) => console.error('Error fetching users:', err)
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}
