// auth.interceptor.ts
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpHeaders } from '@angular/common/http';
import { Observable, EMPTY, NEVER } from 'rxjs';
import { UserService } from '../../shared/services/user/user.service';
import { jwtDecode } from "jwt-decode";
import { Router } from '@angular/router';



@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor (private userService: UserService, private router: Router) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        console.log("intercepted")
        let authRequest = request
        if (!authRequest.url.includes("/user/login")) authRequest = this.addTokenToHeader(authRequest)
        authRequest = this.addUsernameToUrl(authRequest);
        return next.handle(authRequest);
    }

    private addTokenToHeader(request: HttpRequest<any>): HttpRequest<any> {
        let token = sessionStorage.getItem("token");
        if (token) {
            console.log(Math.floor(Date.now()/1000))
            const headers = new HttpHeaders({
                "Authorization": token
            });
            request = request.clone({ headers });
        }
        return request;
    }

    private addUsernameToUrl(request: HttpRequest<any>): HttpRequest<any> {
        const username = this.userService.user.username;
        let url = request.url;
        let pathSegments = url.split("/");
        if(pathSegments[3] != "user") {
            pathSegments.splice(3,0,username);
            url = pathSegments.join("/");
            request = request.clone({url:url});
        }
        return request;
    }
}
