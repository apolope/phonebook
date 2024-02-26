import { Component } from '@angular/core';
import { Router, NavigationEnd, Event as RouterEvent } from '@angular/router';
import { filter } from 'rxjs/operators';
import {ServiceAuth} from "./auth/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isLoginPage: boolean = false;
  title = 'Phonebook';
  userName: string = ''

  constructor(private router: Router,
              private authService: ServiceAuth) {
    this.router.events
        .pipe(
        filter((event: RouterEvent): event is NavigationEnd => event instanceof NavigationEnd)
    ).subscribe((event: NavigationEnd) => {
      this.isLoginPage = event.url === '/login';
    });
  }

  ngOnInit(): void {
    this.updateUserName();
  }

  updateUserName(): void {
    this.userName = localStorage.getItem('user_name') || 'Usuário';
  }

  onLogout() {
    this.authService.logout();
  }
}
