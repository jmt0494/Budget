import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes } from '@angular/router';
import { BudgetComponent } from '../budget/budget.component';

const routes: Routes = [
  { path: "budget", component: BudgetComponent}
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class BudgetRoutingModule { }
export const budgetRoutes = routes;
