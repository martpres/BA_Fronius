import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BackendApiService {

  private baseUrl = 'http://localhost:8080/api/'

  constructor(private httpClient: HttpClient) {
  }

  // public loadCurrentAC(): Observable<any> {
  //   return this.httpClient.get('${this.baseUrl}current-ac')
  // }

}
