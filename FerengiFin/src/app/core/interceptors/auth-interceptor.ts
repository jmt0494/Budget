// auth.interceptor.ts
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserService } from '../../shared/services/user/user.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor (private userService: UserService) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        console.log("intercepted")
        let authRequest = request;
        if (request.url != "user/login"){
            //add token from session storage to request header
            let token = sessionStorage.getItem("token");
            if (token) {
                const headers = new HttpHeaders({
                    "Authorization": token
                });
                authRequest = request.clone({ headers });
                console.log(authRequest)
            }
        }
        return next.handle(authRequest);
    }
}
