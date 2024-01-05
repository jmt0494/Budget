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
            //add token from session storage to request header
        let token = sessionStorage.getItem("token");
        if (token) {
            const headers = new HttpHeaders({
                "Authorization": token
            });
            authRequest = request.clone({ headers });
        }
        authRequest = this.addUsernameToUrl(authRequest);
        return next.handle(authRequest);
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
