import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormGroup, FormBuilder, FormControl, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ServicePeople } from "../people.service";
import { People } from "../../core/openapi";

@Component({
  selector: 'app-update-people',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './update-people.component.html',
  styleUrls: ['./update-people.component.css']
})
export class UpdatePeopleComponent {
  FormData!: FormGroup;
  isloading! : boolean;
  people$!: People;
  id!: any;

  constructor(
      private builder: FormBuilder,
      private service: ServicePeople,
      private route: ActivatedRoute,
      private toastr: ToastrService
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.id = params.get('id');
      this.service.getPeople(this.id).subscribe((res) => {
        this.people$ = res;
        this.FormData.patchValue(this.updateFormValues());
      });
    });

    this.FormData = this.builder.group({
        id: new FormControl(''),
      name: new FormControl(''),
      email: new FormControl(''),
      phone: new FormControl(''),
    });
  }

    onSubmit(formData: any) {
        this.service.updatePeople(formData).then(r => {
            this.toastr.success('Atualizado');
            setTimeout(() => {
                location.href = '/'
            }, 2000);
        })
    }

    updateFormValues() {
        return {
            id: this.people$.id,
            name: this.people$.name,
            email: this.people$.email,
            phone: this.people$.phone,
        };
    }
}
