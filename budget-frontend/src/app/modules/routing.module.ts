import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../pages/login/login.component';
import { UnassignedTransactionsComponent } from '../components/unassigned-transactions/unassigned-transactions.component';
import { BudgetComponent } from '../pages/budget/budget.component';

const routes: Routes = [
  {path: "", redirectTo: "/login", pathMatch: 'full'},
  { path: 'login', component: LoginComponent },
  { path: "budget", component: BudgetComponent},
  { path: "test", component: UnassignedTransactionsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }