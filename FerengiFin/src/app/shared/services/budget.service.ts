import { UserService } from 'src/app/shared/services/user-service.service';
import { Injectable } from '@angular/core';
import { Budget } from '../models/budget';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError, Observable, BehaviorSubject } from 'rxjs';
import { Month } from 'src/app/shared/enums/month';

@Injectable({
  providedIn: 'root'
})
export class BudgetService {

  budgetListSubject: BehaviorSubject<Budget[]> = new BehaviorSubject<Budget[]>([]);

  budgetList$ = this.budgetListSubject.asObservable();

  currentBudgetSubject: BehaviorSubject<Budget> =new BehaviorSubject<Budget>(new Budget(0, Month.Jan, 0, 0));

  currentBudget$ = this.currentBudgetSubject?.asObservable();

  constructor(private http: HttpClient, private userService: UserService) {}

  setBudgetList() {
    this.fetchBudgetList().subscribe(budgets =>{
      this.currentBudgetSubject.next(budgets[0])
      this.budgetListSubject?.next(budgets);
    })
  }

  private fetchBudgetList(): Observable<Budget[]> {
    return this.http.get<Budget[]>(`${environment.baseUrl}/${this.userService.user.username}/budget`)
    .pipe(catchError(this.handleError));
  }

  private handleError(httpError: HttpErrorResponse) {
    if (httpError.error instanceof ErrorEvent) {
      console.log('An error has occured: ', httpError.error.message);
    } else {
      console.error();
    }

    return throwError(() => new Error('something went wrong'));
  }
}
