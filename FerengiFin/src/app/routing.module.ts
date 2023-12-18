import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './features/login/feature/login.component';
import { BudgetComponent } from './features/budget/feature/budget/budget.component';
import { LoginRoutingModule, loginRoutes } from './features/login/feature/login-routing.module';
import { BudgetRoutingModule, budgetRoutes } from './features/budget/feature/budget-shell/budget-routing.module';

const routes: Routes = [
  ...loginRoutes,
  ...budgetRoutes

];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    LoginRoutingModule,
    BudgetRoutingModule,
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }