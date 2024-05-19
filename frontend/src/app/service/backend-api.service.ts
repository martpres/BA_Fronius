import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {QueryDslResponse} from "../dto/queryDslResponse.model";
import {AcCurrentGrid} from "../dto/acCurrentGrid.model";
import {DcPowerPv} from "../dto/dcPowerPv.model";
import {AcPowerGrid} from "../dto/acPowerGrid.model";
import {AcPowerInverter} from "../dto/acPowerInverter.model";
import {DcVoltagePv} from "../dto/dcVoltagePv.model";

@Injectable({
  providedIn: 'root'
})
export class BackendApiService {

  private baseUrl = 'http://localhost:8080/api/'

  constructor(private httpClient: HttpClient) {
  }

  public loadCurrentAC(filter?: any): Observable<QueryDslResponse<AcCurrentGrid>> {
    return this.httpClient.get<QueryDslResponse<AcCurrentGrid>>(`${this.baseUrl}meter-realtime-data/ac-current-grid`,{params: filter});
  }

  public loadDcPowerPv(filter?: any): Observable<QueryDslResponse<DcPowerPv>> {
    return this.httpClient.get<QueryDslResponse<DcPowerPv>>(`${this.baseUrl}power-flow-realtime-data/dc-power-pv`,{params: filter});
  }

  public loadDcVoltagePv(filter?: any): Observable<QueryDslResponse<DcVoltagePv>> {
    return this.httpClient.get<QueryDslResponse<DcVoltagePv>>(`${this.baseUrl}common-inverter-data/dc-voltage-pv`,{params: filter});
  }

  public loadAcPowerGrid(filter?: any): Observable<QueryDslResponse<AcPowerGrid>> {
    return this.httpClient.get<QueryDslResponse<AcPowerGrid>>(`${this.baseUrl}power-flow-realtime-data/ac-power-grid`,{params: filter});
  }

  public loadAcPowerInverter(filter?: any): Observable<QueryDslResponse<AcPowerInverter>> {
    return this.httpClient.get<QueryDslResponse<AcPowerInverter>>(`${this.baseUrl}common-inverter-data/ac-power-inverter`,{params: filter});
  }

}
