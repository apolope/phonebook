import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {ServicePeople} from "../people.service";
import {ActivatedRoute} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-add-people',
  standalone: true,
    imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './add-people.component.html',
  styleUrls: ['./add-people.component.css']
})
export class AddPeopleComponent {
  FormData!: FormGroup;
  isloading! : boolean;
  id!: any;

  constructor(
      private builder: FormBuilder,
      private service: ServicePeople,
      private route: ActivatedRoute,
      private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.FormData = this.builder.group({
      name: new FormControl(''),
      email: new FormControl(''),
      phone: new FormControl(''),
    });
  }

  onSubmit(formData: any) {
    console.log(formData)
    this.service.createPeople(formData).then(r => {
      this.toastr.success('Criado');
      setTimeout(() => {
        location.href = '/'
      }, 2000);
    })
  }
}
