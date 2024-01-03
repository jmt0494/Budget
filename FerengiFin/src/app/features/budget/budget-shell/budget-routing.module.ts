import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes } from '@angular/router';
import { BudgetComponent } from '../feature/budget/budget.component';
import { RouteGuardService } from 'src/app/shared/services/route-guard/route-guard.service';

const routes: Routes = [
  { path: "budget", component: BudgetComponent, canActivate: [RouteGuardService]}
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class BudgetRoutingModule { }
export const budgetRoutes = routes;
