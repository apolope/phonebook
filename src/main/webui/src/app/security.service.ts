import { Injectable } from '@angular/core';
import { Observable } from 'rxjs'
import {User, SecurityServiceInterface} from './core/openapi'

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  constructor(private api:  SecurityServiceInterface) { }

  getToken(user: User): Observable<User> {
    return this.api.securityPost(user)
  }
}
