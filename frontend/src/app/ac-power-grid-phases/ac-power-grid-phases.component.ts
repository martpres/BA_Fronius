import {Component, inject, OnDestroy, OnInit} from '@angular/core';
import {BackendApiService} from "../service/backend-api.service";
import {Subscription} from "rxjs";
import {QueryDslResponse} from "../dto/queryDslResponse.model";
import {DateTimeService} from "../service/date-time.service";
import {formatDate} from "@angular/common";
import {localId, timeFormat} from "../dto/const";
import {AcPowerGridPhases} from "../dto/acPowerGridPhases.model";
import * as moment from "moment";

@Component({
  selector: 'app-ac-power-grid-phases',
  templateUrl: './ac-power-grid-phases.component.html',
  styleUrls: ['./ac-power-grid-phases.component.scss']
})
export class AcPowerGridPhasesComponent  implements OnInit, OnDestroy {
  public chartData?: any[];
  public initialDate = new Date();
  public maxDate = new Date();
  private sub?: Subscription;
  private data?: QueryDslResponse<AcPowerGridPhases>;
  private refreshMilliSeconds = 60000;
  private interval?: any;

  constructor(private backendService: BackendApiService, private dateTimeService: DateTimeService) {
  }

  ngOnInit(): void {
    this.sendRequest();
    this.interval = setInterval(() => {
      this.sendRequest();
    }, this.refreshMilliSeconds);
  }

  ngOnDestroy(): void {
    this.sub?.unsubscribe();
    clearInterval(this.interval);
  }

  public convertTime(value: Date): string {
    return formatDate(value, timeFormat, localId);
  }

  public sendRequest(): void {
    const startDate = moment(this.initialDate).startOf('day').utc();
    const endDate = moment(this.initialDate).endOf('day').utc();

    this.sub = this.backendService.loadAcPowerGridPhases(this.dateTimeService.createFilterForMoment(startDate.format(),
      endDate.format())).subscribe((e) => {
      this.data = e;
      this.mapRequestToChart();
    });
  }

  private mapRequestToChart(): void {
    if (this.data?.content?.length === 0) {
      this.chartData = undefined;
      return;
    }
    this.chartData = [];
    const array1: any[] = [];
    const array2: any[] = [];
    const array3: any[] = [];
    this.data?.content?.forEach((e) => {
      let date = this.dateTimeService.convertUtcToLocalTimeZone(e.timestamp)
      array1.push({name: date, value: e.acPowerGridPhase1});
      array2.push({name: date, value: e.acPowerGridPhase2});
      array3.push({name: date, value: e.acPowerGridPhase3});
    });
    this.chartData?.push({name: 'AC Power Grid - Phase 1', series: array1});
    this.chartData?.push({name: 'AC Power Grid - Phase 2', series: array2});
    this.chartData?.push({name: 'AC Power Grid - Phase 3', series: array3});
  }
}
