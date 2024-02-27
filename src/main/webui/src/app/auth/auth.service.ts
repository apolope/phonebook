import { Injectable } from '@angular/core';
import * as jwt_decode from 'jwt-decode';
import { User } from '../core/openapi';
import { Router } from '@angular/router';
import { ServiceSecurity } from "../security.service";

@Injectable({
  providedIn: 'root'
})
export class ServiceAuth {

  constructor(private api: ServiceSecurity,
              private router: Router) { }

  login(user: User) {
    return this.api.login(user);
  }

  logout() {
    localStorage.removeItem('auth_token');
    localStorage.removeItem('user_name');
    this.router.navigate(['/login']);
  }

  isLoggedIn(){
    return localStorage.getItem('auth_token') !== null;
  }

  decodePayloadJWT(): any {
    let token = localStorage.getItem('auth_token')
    if (token) {
      try {
        return jwt_decode.jwtDecode(token);
      } catch (Error) {}
    }
    return null;
  }
}