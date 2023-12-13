import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }


  loginForm = new FormGroup ({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  });

  login() {
    this.userService.username = this.loginForm.controls.username.value!;
    this.userService.password = this.loginForm.controls.password.value!;

    this.userService.getUser().subscribe((user) => {
      this.userService.user=user;
      if (this.userService.user) {
        console.log(user.username);
        this.router.navigateByUrl("/budget")
      }
    });

  }

}
