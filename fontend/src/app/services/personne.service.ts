import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Personne } from '../models/personne';
import { Observable, catchError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PersonneService {

  private basrUrl = 'http://localhost:8080/personnes'

  constructor(private _http : HttpClient) { }

  getPersonnes() : Observable<Object> {
    return this._http.get(this.basrUrl);
  }

  createPersonne(personne : Personne): Observable<Object> {
    return this._http.post(this.basrUrl, personne);
  }

  updatePersonne(id: number, personne: Personne): Observable<Object> {
    return this._http.put(this.basrUrl + '/' + id, personne);
  }

  deletePersonne(id:number) : Observable<Object>{
    return this._http.delete(this.basrUrl + '/' + id);
  }
}
