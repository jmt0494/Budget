import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AppRoutingModule } from '../routing.module';
import { NavComponent } from './nav/nav.component';
import { NotFoundPageComponent } from './not-found-page/not-found-page.component';



@NgModule({
  declarations: [NavComponent, NotFoundPageComponent],
  imports: [
    CommonModule,
    MatToolbarModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    AppRoutingModule,
  ],
  exports: [
    NavComponent
  ]
})
export class CoreModule { }
