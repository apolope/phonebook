import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { Router } from  '@angular/router';
import { ServiceAuth } from  '../auth.service';
import { Component, OnInit } from "@angular/core";
import { CommonModule, NgClass } from "@angular/common";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgClass,
    CommonModule
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  authForm!: FormGroup;
  isSubmitted  =  false;
  public token$: any;

  constructor(private service: ServiceAuth,
              private router: Router,
              private formBuilder: FormBuilder ) { }
  ngOnInit() {
    this.authForm  =  this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  get formControls() { return this.authForm.controls; }

  signIn(){
    this.isSubmitted = true;
    if(this.authForm.invalid){
      return;
    }
    this.service.login(this.authForm.value).then(r => {
      localStorage.setItem("auth_token", r.data)
      localStorage.setItem("user_name", this.service.decodePayloadJWT().name)
      this.router.navigate(['/']);
    })
  }
}
