import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }


  loginForm = new FormGroup ({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  });

  login() {
    this.userService.username = this.loginForm.controls.username.value!;
    this.userService.password = this.loginForm.controls.password.value!;

    console.log(this.userService.username);

    this.userService.getUser().subscribe((user) => {
      this.userService.user=user;
      console.log(user.email);
    });

  }

}
