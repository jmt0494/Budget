import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  url = environment.baseUrl + "/user/"

  isAuthenticated = false;

  user?: User

  constructor(private http: HttpClient) { }

  // authenticate(username: string, password: string) {
  //   this.http.get(url + username)
  // }


}
