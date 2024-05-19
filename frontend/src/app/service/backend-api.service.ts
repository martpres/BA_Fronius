import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {QueryDslResponse} from "../dto/queryDslResponse.model";
import {CurrentAC} from "../dto/currentAC.model";
import {DcPowerPv} from "../dto/dcPowerPv.model";
import {AcPowerGrid} from "../dto/acPowerGrid.model";

@Injectable({
  providedIn: 'root'
})
export class BackendApiService {

  private baseUrl = 'http://localhost:8080/api/'

  constructor(private httpClient: HttpClient) {
  }

  public loadCurrentAC(filter?: any): Observable<QueryDslResponse<CurrentAC>> {
    return this.httpClient.get<QueryDslResponse<CurrentAC>>(`${this.baseUrl}meter-realtime-data/current-ac`,{params: filter});
  }

  public loadDcPowerPv(filter?: any): Observable<QueryDslResponse<DcPowerPv>> {
    return this.httpClient.get<QueryDslResponse<DcPowerPv>>(`${this.baseUrl}power-flow-realtime-data/dc-power-pv`,{params: filter});
  }

  public loadAcPowerGrid(filter?: any): Observable<QueryDslResponse<AcPowerGrid>> {
    return this.httpClient.get<QueryDslResponse<AcPowerGrid>>(`${this.baseUrl}power-flow-realtime-data/ac-power-grid`,{params: filter});
  }
}
