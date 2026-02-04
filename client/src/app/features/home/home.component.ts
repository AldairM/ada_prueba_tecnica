import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

@Component({
    selector: 'app-home',
    standalone: true,
    imports: [
        CommonModule,
        RouterOutlet,
        RouterLink,
        RouterLinkActive,
        MatSidenavModule,
        MatListModule,
        MatToolbarModule,
        MatIconModule,
        MatButtonModule
    ],
    template: `
    <mat-sidenav-container class="sidenav-container">
      <mat-sidenav #drawer class="sidenav" fixedInViewport
          [attr.role]="'navigation'"
          [mode]="'side'"
          [opened]="true">
        <mat-toolbar>Menu</mat-toolbar>
        <mat-nav-list>
          <a mat-list-item routerLink="users" routerLinkActive="active-link">
            <mat-icon matListItemIcon>list</mat-icon>
            <span matListItemTitle>Listado</span>
          </a>
        </mat-nav-list>
        <mat-nav-list>
          <a mat-list-item routerLink="users" routerLinkActive="active-link">
            <mat-icon matListItemIcon>edit_document</mat-icon>
            <span matListItemTitle>Formulario</span>
          </a>
        </mat-nav-list>
      </mat-sidenav>
      <mat-sidenav-content>
        <mat-toolbar color="primary">
          <div class="container-actions">
            <button matButton="elevated" class="mx-2">Registrarse</button>
            <button matButton="elevated" class="sign-in-button">Ingresar</button>
          </div>
        </mat-toolbar>
        <div class="main-content">
          <router-outlet></router-outlet>
        </div>
      </mat-sidenav-content>
    </mat-sidenav-container>
  `,
    styles: [`
    .sidenav-container {
      height: 100vh;
    }
    .sidenav {
      width: 200px;
    }
    .main-content {
      padding: 20px;
    }
    .active-link {
      background: rgba(0, 0, 0, 0.04);
      color: #3f51b5;
    }
    .container-actions {
      display: flex;
      justify-content: flex-end;
      align-items: center;
      width: 100%;
    }
    .mx-2 {
      margin-left: 8px;
      margin-right: 8px;
    }
    mat-toolbar.mat-toolbar.mat-primary {
        background-color: #f1f1f2;
    }
    .sign-in-button {
        background-color: #909090 !important;
        color: #fff !important;
    }   
  `]
})
export class HomeComponent { }
