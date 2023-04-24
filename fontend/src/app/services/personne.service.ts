import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Personne } from '../models/personne';
import { Observable, catchError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PersonneService {
  // private baseUrl = 'http://localhost:8080';

  // constructor(private _http: HttpClient) {}

  // getAllPersonne(): Observable<any> {
  //   const url = `${this.baseUrl}/getAllPersonne`;
  //   return this._http.get(url);
  // }

  // addPersonne(personne: Personne) {
  //   const url = `${this.baseUrl}/addPersonne`;
  //   return this._http.post(url, personne);
  // }

  // updatePersonne(id: number, personne: Personne): Observable<any> {
  //   const url = `${this.baseUrl}/updatePersonne/${id}`;
  //   return this._http.put(url, personne).pipe(
  //     catchError((error) => {
  //       throw new Error(error.json().error || 'Server error');
  //     })
  //   );
  // }

  // deletePersonne(id: number): Observable<any> {
  //   const url = `${this.baseUrl}/deletePersonne/${id}`;
  //   return this._http.delete(url).pipe(
  //     catchError((error) => {
  //       throw new Error(error.json().error || 'Server error');
  //     })
  //   );
  // }

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
