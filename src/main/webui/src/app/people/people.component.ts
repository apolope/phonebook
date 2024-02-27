import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ServicePeople } from "./people.service";
import { AddPeopleComponent } from "./add-people/add-people.component";
import { UpdatePeopleComponent } from "./update-people/update-people.component";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-people',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    AddPeopleComponent,
    UpdatePeopleComponent,
    FormsModule
  ],
  providers: [ServicePeople],
  templateUrl: './people.component.html',
  styleUrls: ['./people.component.css']
})
export class PeopleComponent {
  public peoples$: any;
  public searchTerm: string = '';

  constructor(
      private service: ServicePeople,
      private router: Router,
      private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.getAllBooks();
  }

  getAllBooks() {
    this.service.getPeoples().subscribe((res) => {
      this.peoples$ = res;
    });
  }

  onDelete(id: any) {
    this.service.deletePeople(id).subscribe((res) => {
      if (res) {
        this.toastr.success('Deletado');
        setTimeout(() => {
          location.reload();
        }, 2000);
      } else {
        this.toastr.error('Falhou');
        setTimeout(() => {
          location.reload();
        }, 2000);
      }
    });
  }

  onUpdate(id: any) {
    this.router.navigate([`/update-people/${id}`]);
  }

  get filteredPeoples() {
    return this.peoples$?.filter((people: any) =>
        people.name.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }
}


