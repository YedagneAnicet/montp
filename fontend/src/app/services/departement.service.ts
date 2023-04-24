import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DepartementService {
  private baseUrl = 'http://localhost:8080';

  constructor(private _http : HttpClient) { }

  getAllDerpartement() : Observable<any>{
    const url = `${this.baseUrl}/getAllDepartement`;
    return this._http.get(url);
  }
}
