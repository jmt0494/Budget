import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './features/login/feature/login.component';
import { BudgetComponent } from './features/budget/feature/budget/budget.component';
import { LoginRoutingModule, loginRoutes } from './features/login/feature/login-routing.module';
import { BudgetRoutingModule, budgetRoutes } from './features/budget/feature/budget-shell/budget-routing.module';
import { NotFoundPageComponent } from './core/not-found-page/not-found-page.component';

const routes: Routes = [
  ...loginRoutes,
  ...budgetRoutes,
  {path: "**", component: NotFoundPageComponent}
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