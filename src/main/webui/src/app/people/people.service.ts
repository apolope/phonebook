import { Injectable } from '@angular/core'
import { Observable } from 'rxjs'
import { PeopleService, People } from '../core/openapi'

@Injectable({
  providedIn: 'root',
})
export class ServicePeople {

  constructor(private api: PeopleService) { }

  getPeoples(): Observable<People[]> {
    return this.api.apiPeopleGet()
  }

  getPeople(id: number): Observable<People> {
    return this.api.apiPeopleIdGet(id)
  }

  updatePeople(people: People) {
    return this.api.updatePeople(people)
  }

  deletePeople(id: number): Observable<Boolean> {
    return this.api.apiPeopleDelete(id)
  }

  createPeople(people:People) {
    return this.api.criarPeople(people)
  }
}
