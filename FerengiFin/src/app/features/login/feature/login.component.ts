import { RouteGuardService } from 'src/app/shared/services/route-guard/route-guard.service';
import { BudgetService } from 'src/app/shared/services/budget/budget.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/shared/services/user/user.service';
import { Credentials } from '../data/credentials';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {

  constructor(private userService: UserService, private router: Router, private budgetService: BudgetService, private routeGuardService: RouteGuardService) { }

  ngOnInit(): void {
  }


  loginForm = new FormGroup ({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  });

  login() {
    const creds = new Credentials(this.loginForm.controls.username.value!, this.loginForm.controls.password.value!)
    //get token
    this.userService.login(creds).subscribe(token => {
      //save token to session storage
      sessionStorage.setItem("token", `Bearer ${token.token}`)

      //get user data
      this.userService.loadUser(creds.username).subscribe(user => {
        this.userService.user = user;
        this.userService.isAuthenticated = true;

        const redirPath = this.routeGuardService.currentRoute
          .map(pathSeg => pathSeg.path)
          .reduce((path, nextSeg) => `${path}/${nextSeg}`);

        //redirect to next page
        this.router.navigateByUrl(redirPath);
      })
    })


  }

}
