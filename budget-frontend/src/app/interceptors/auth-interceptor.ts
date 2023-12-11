// auth.interceptor.ts
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserService } from '../services/user-service.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor (private userService: UserService) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const username = this.userService.username;
    const password = this.userService.password;

    const base64Credentials = btoa(`${username}:${password}`);

    const authRequest = request.clone({
        setHeaders: {
        Authorization: `Basic ${base64Credentials}`
        }
    });

    return next.handle(authRequest);
    }
}
