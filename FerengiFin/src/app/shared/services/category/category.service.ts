import { BudgetService } from 'src/app/shared/services/budget/budget.service';
import { UserService } from 'src/app/shared/services/user/user.service';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Category } from '../../models/category';
import { catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  url = `${environment.baseUrl}/${this.userService.user.username}/category`

  constructor(private http: HttpClient, private userService: UserService, private budgetService: BudgetService) { }

  public loadCategories(budgetId: number) {
    this.http.get<Category[]>(`${this.url}/${budgetId}`)
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
