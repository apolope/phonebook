import { Injectable } from '@angular/core';
import { User, SecurityService } from './core/openapi'
import { Router } from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class ServiceSecurity {

  constructor(private api:  SecurityService,
              private router: Router) { }

  login(user: User) {
    return this.api.apiLogin(user)
  }

  logout(): void {
    localStorage.removeItem("auth_token")
    this.router.navigate(['/login']);
  }
}
