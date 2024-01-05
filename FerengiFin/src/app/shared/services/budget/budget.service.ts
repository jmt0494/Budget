import { DetailedBudget } from './../../../features/budget/data/detailed-budget';
import { UserService } from 'src/app/shared/services/user/user.service';
import { Injectable, OnInit } from '@angular/core';
import { Budget } from '../../models/budget';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError, Observable, BehaviorSubject } from 'rxjs';
import { Month } from 'src/app/shared/enums/month';

@Injectable({
  providedIn: 'root'
})
export class BudgetService {

  url: string = `${environment.baseUrl}/budget`;
  
  budgetListSubject: BehaviorSubject<Budget[]> = new BehaviorSubject<Budget[]>([]);

  budgetList$ = this.budgetListSubject.asObservable();

  currentBudgetSubject: BehaviorSubject<Budget> =new BehaviorSubject<Budget>(new Budget(0, Month.Jan, 0, 0));

  currentBudget$ = this.currentBudgetSubject?.asObservable();

  constructor(private http: HttpClient, private userService: UserService) {}

  setCurrentBudget(budget: Budget) {
    this.currentBudgetSubject.next(budget);
  }

  public getDetailedBudget(id: number): Observable<DetailedBudget>  {
    return this.http.get<DetailedBudget>(`${this.url}/fullpayload/${id}`)
    .pipe(catchError(this.handleError));
  }

  setBudgetList() {
    this.fetchBudgetList().subscribe(budgets =>{
      this.currentBudgetSubject.next(budgets[0])
      this.budgetListSubject?.next(budgets);
    })
  }

  private fetchBudgetList(): Observable<Budget[]> {
    return this.http.get<Budget[]>(`${this.url}`)
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
