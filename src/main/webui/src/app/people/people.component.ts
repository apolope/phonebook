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
  currentPage: number = 1;
  itemsPerPage: number = 10;
  totalItems!: number;

  constructor(
      private service: ServicePeople,
      private router: Router,
      private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.getAllPeoples();
  }

  getAllPeoples() {
    this.service.getPeoples().subscribe((res) => {
      this.peoples$ = res
      this.totalItems = res.length
    });
  }

  nextPage() {
    if (this.currentPage * this.itemsPerPage < this.totalItems) {
      this.currentPage++;
    }
  }

  previousPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
    }
  }

  get totalPages() {
    return Math.ceil(this.totalItems / this.itemsPerPage);
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

  get filteredAndPaginedPeoples() {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    let peoples = this.peoples$?.filter((people: any) =>
        people.name.toLowerCase().includes(this.searchTerm.toLowerCase())
    )

    this.totalItems = peoples.length

    return peoples.slice(startIndex, startIndex + this.itemsPerPage);
  }
}


