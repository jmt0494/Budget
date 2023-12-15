// auth.interceptor.ts
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserService } from '../../shared/services/user-service.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor (private userService: UserService) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        console.log("intercepted")
    const username = this.userService.username;
    const password = this.userService.password;

    const headers = new HttpHeaders({
        Authorization: `Basic ${btoa(`${username}:${password}`)}`
    });

    const authRequest = request.clone({ headers });

    return next.handle(authRequest);
    }
}
