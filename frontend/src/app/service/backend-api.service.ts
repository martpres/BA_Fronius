import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {QueryDslResponse} from "../dto/queryDslResponse.model";
import {CurrentAC} from "../dto/currentAC.model";
import {PowerFlowRealtimeData} from "../dto/powerFlowRealtimeData.model";

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

  public loadPowerFlowRealtimeData(filter?: any): Observable<QueryDslResponse<PowerFlowRealtimeData>> {
    return this.httpClient.get<QueryDslResponse<PowerFlowRealtimeData>>(`${this.baseUrl}power-flow-realtime-data/`,{params: filter});
  }

}
