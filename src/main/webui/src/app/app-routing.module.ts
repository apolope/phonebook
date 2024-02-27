import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PeopleComponent } from "./people/people.component";
import { AddPeopleComponent } from "./people/add-people/add-people.component";
import { UpdatePeopleComponent } from "./people/update-people/update-people.component";
import { LoginComponent } from "./auth/login/login.component";

const routes: Routes = [
  {
    path: '',
    component: PeopleComponent,
    pathMatch:  'prefix'
  },

  {
    path: 'login',
    component: LoginComponent
  },

  {
    path: 'add-people',
    component: AddPeopleComponent
  },

  {
    path: 'update-people/:id',
    component: UpdatePeopleComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
