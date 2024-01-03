import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { environment } from 'src/environments/environment';
import { Credentials } from 'src/app/features/login/data/credentials';
import { Token } from '../models/token';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url = environment.baseUrl + "/user"

  public user: User = new User("","",NaN);

  public isAuthenticated = false

  constructor(private http: HttpClient){}
  
  public login(creds: Credentials){
    return this.http.post<Token>(`${this.url}/login`, creds)
    .pipe(catchError(this.handleError));
  }

  public loadUser(username: String) {
    return this.http.get<User>(`${this.url}/${username}`)
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
