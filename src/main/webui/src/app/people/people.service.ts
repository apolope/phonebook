import { Injectable } from '@angular/core'
import { Observable } from 'rxjs'
import { PeopleService, People } from '../core/openapi'

@Injectable({
  providedIn: 'root',
})
export class ServicePeople {

  constructor(private api: PeopleService) { }

  getPeoples(): Observable<People[]> {
    return this.api.peopleGet()
  }

  getPeople(id: number): Observable<People> {
    return this.api.peopleIdGet(id)
  }

  updatePeople(id: number, people: People): Observable<People> {
    return new Observable<People>()
    // return this.api.peopleIdPut(id, people)
  }

  deletePeople(id: number): Observable<Boolean> {
    return this.api.peopleDelete(id)
  }
}
