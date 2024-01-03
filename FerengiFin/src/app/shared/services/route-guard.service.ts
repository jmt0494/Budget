import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree, Router, UrlSegment } from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from './user-service.service';

@Injectable({
  providedIn: 'root'
})
export class RouteGuardService implements CanActivate {

  currentRoute: UrlSegment[] = [new UrlSegment("budget", {})];

  constructor(private userService: UserService, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    // check if we are logged in. If we are return true
    if (this.userService.isAuthenticated) return true;
    //else save current route for the login component to redirect back to
    this.currentRoute = route.url;
    // and route to the login page
    this.router.navigate(["login"]);
    return false;
  }
}
