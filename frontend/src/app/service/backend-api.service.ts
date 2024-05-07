import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {QueryDslResponse} from "../dto/queryDslResponse.model";
import {CurrentAC} from "../dto/currentAC.model";
import {CurrentAcFilter} from "../dto/current-ac-filter.model";
import {PowerDC} from "../dto/powerDC.model";

@Injectable({
  providedIn: 'root'
})
export class BackendApiService {

  private baseUrl = 'http://localhost:8080/api/'

  constructor(private httpClient: HttpClient) {
  }

  public loadCurrentAC(filter?: any): Observable<QueryDslResponse<CurrentAC>> {
    return this.httpClient.get<QueryDslResponse<CurrentAC>>(`${this.baseUrl}current-ac/`,{params: filter});
  }

  public loadPowerDC(filter?: any): Observable<QueryDslResponse<PowerDC>> {
    return this.httpClient.get<QueryDslResponse<PowerDC>>(`${this.baseUrl}power-dc/`,{params: filter});
  }

}
