import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {QueryDslResponse} from "../dto/queryDslResponse.model";
import {AcCurrentGridPhases} from "../dto/acCurrentGridPhases.model";
import {DcPowerPv} from "../dto/dcPowerPv.model";
import {AcPowerGrid} from "../dto/acPowerGrid.model";
import {AcPowerInverter} from "../dto/acPowerInverter.model";
import {DcVoltagePv} from "../dto/dcVoltagePv.model";
import {DcPowerAkku} from "../dto/dcPowerAkku.model";
import {AcPowerLoad} from "../dto/acPowerLoad.model";
import {Autonomy} from "../dto/autonomy.model";
import {SelfConsumption} from "../dto/selfConsumption.model";
import {StateOfChargeAkku} from "../dto/stateOfChargeAkku.model";
import {AcEnergyInverterDay} from "../dto/acEnergyInverterDay.model";
import {EnergyDay} from "../dto/energyDay.model";
import {AcPowerGridPhases} from "../dto/acPowerGridPhases.model";

@Injectable({
  providedIn: 'root'
})
export class BackendApiService {

  private baseUrl = 'http://localhost:8080/api/'

  constructor(private httpClient: HttpClient) {
  }

  public loadAcCurrentGridPhases(filter?: any): Observable<QueryDslResponse<AcCurrentGridPhases>> {
    return this.httpClient.get<QueryDslResponse<AcCurrentGridPhases>>(
      `${this.baseUrl}meter-realtime-data/ac-current-grid-phases`, {params: filter});
  }

  public loadAcPowerGridPhases(filter?: any): Observable<QueryDslResponse<AcPowerGridPhases>> {
    return this.httpClient.get<QueryDslResponse<AcPowerGridPhases>>(
      `${this.baseUrl}meter-realtime-data/ac-power-grid-phases`, {params: filter});
  }

  public loadDcPowerPv(filter?: any): Observable<QueryDslResponse<DcPowerPv>> {
    return this.httpClient.get<QueryDslResponse<DcPowerPv>>(
      `${this.baseUrl}power-flow-realtime-data/dc-power-pv`, {params: filter});
  }

  public loadDcVoltagePv(filter?: any): Observable<QueryDslResponse<DcVoltagePv>> {
    return this.httpClient.get<QueryDslResponse<DcVoltagePv>>(
      `${this.baseUrl}common-inverter-data/dc-voltage-pv`, {params: filter});
  }

  public loadAcPowerGrid(filter?: any): Observable<QueryDslResponse<AcPowerGrid>> {
    return this.httpClient.get<QueryDslResponse<AcPowerGrid>>(
      `${this.baseUrl}power-flow-realtime-data/ac-power-grid`, {params: filter});
  }

  public loadAcPowerInverter(filter?: any): Observable<QueryDslResponse<AcPowerInverter>> {
    return this.httpClient.get<QueryDslResponse<AcPowerInverter>>(
      `${this.baseUrl}common-inverter-data/ac-power-inverter`, {params: filter});
  }

  public loadDcPowerAkku(filter?: any): Observable<QueryDslResponse<DcPowerAkku>> {
    return this.httpClient.get<QueryDslResponse<AcPowerInverter>>(
      `${this.baseUrl}power-flow-realtime-data/dc-power-akku`, {params: filter});
  }

  public loadAcPowerLoad(filter?: any): Observable<QueryDslResponse<AcPowerLoad>> {
    return this.httpClient.get<QueryDslResponse<AcPowerLoad>>(
      `${this.baseUrl}power-flow-realtime-data/ac-power-load`, {params: filter});
  }

  public loadAutonomy(filter?: any): Observable<QueryDslResponse<Autonomy>> {
    return this.httpClient.get<QueryDslResponse<Autonomy>>(
      `${this.baseUrl}power-flow-realtime-data/autonomy`, {params: filter});
  }

  public loadSelfConsumption(filter?: any): Observable<QueryDslResponse<SelfConsumption>> {
    return this.httpClient.get<QueryDslResponse<SelfConsumption>>(
      `${this.baseUrl}power-flow-realtime-data/self-consumption`, {params: filter});
  }

  public loadStateOfChargeAkku(filter?: any): Observable<QueryDslResponse<StateOfChargeAkku>> {
    return this.httpClient.get<QueryDslResponse<StateOfChargeAkku>>(
      `${this.baseUrl}power-flow-realtime-data/state-of-charge-akku`, {params: filter});
  }

  public loadAcEnergyInverterDay(filter?: any): Observable<QueryDslResponse<AcEnergyInverterDay>> {
    return this.httpClient.get<QueryDslResponse<AcEnergyInverterDay>>(
      `${this.baseUrl}common-inverter-data/ac-energy-inverter-day/latest`, {params: filter});
  }

  public loadCalculatedAcEnergyInverterDay(filter?: any): Observable<EnergyDay> {
    return this.httpClient.get<EnergyDay>(
      `${this.baseUrl}common-inverter-data/ac-energy-inverter-day/calculated/latest`, {params: filter});
  }

  public loadCalculatedDcEnergyPvDay(filter?: any): Observable<EnergyDay> {
    return this.httpClient.get<EnergyDay>(
      `${this.baseUrl}power-flow-realtime-data/dc-energy-pv-day/calculated/latest`, {params: filter});
  }

}
