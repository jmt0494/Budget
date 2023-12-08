import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../pages/login/login.component';
import { UnassignedTransactionsComponent } from '../components/unassigned-transactions/unassigned-transactions.component';

const routes: Routes = [
  {path: "", redirectTo: "/login", pathMatch: 'full'},
  { path: 'login', component: LoginComponent },
  { path: "test", component: UnassignedTransactionsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }