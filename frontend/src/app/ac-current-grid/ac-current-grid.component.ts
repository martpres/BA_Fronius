import {Component, inject, OnDestroy, OnInit} from '@angular/core';
import {BackendApiService} from "../service/backend-api.service";
import {Subscription} from "rxjs";
import {QueryDslResponse} from "../dto/queryDslResponse.model";
import {AcCurrentGrid} from "../dto/acCurrentGrid.model";
import {DateTimeService} from "../service/date-time.service";
import {formatDate} from "@angular/common";
import {localId, timeFormat} from "../dto/const";

@Component({
  selector: 'app-ac-current-grid',
  templateUrl: './ac-current-grid.component.html',
  styleUrls: ['./ac-current-grid.component.scss']
})
export class AcCurrentGridComponent implements OnInit, OnDestroy{
  public lineChartData?: any[];
  public initialDate = new Date();
  private sub?: Subscription;
  private data?: QueryDslResponse<AcCurrentGrid>;
  private refreshMilliSeconds = 60000;
  private interval?: any;

  constructor(private backendService: BackendApiService, private dateTimeService: DateTimeService) {
  }

  ngOnInit(): void {
    this.sendRequest();
    this.interval = setInterval(()=> {
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

  private sendRequest(): void {
    this.initialDate = new Date();
    const endDate = this.dateTimeService.convertToUtcDate(this.initialDate);
    const startDate = this.dateTimeService.convertToStartOfDayUtc(this.dateTimeService.convertToUtcDate(this.initialDate));
    this.sub = this.backendService.loadCurrentAC(this.dateTimeService.createFilter(startDate, endDate)).subscribe((e)=> {
      this.data=e;
      this.mapRequestToLineChart();
    });
  }

  private mapRequestToLineChart(): void {
    if (this.data?.content?.length===0) {
      this.lineChartData=undefined;
      return;
    }
    this.lineChartData = [];
    const arrayAcCurrentGridPhase1: any[] = [];
    const arrayAcCurrentGridPhase2: any[] = [];
    const arrayAcCurrentGridPhase3: any[] = [];
    this.data?.content?.forEach((e)=>{
      let date = this.dateTimeService.convertUtcToLocalTimeZone(e.timestamp)
      arrayAcCurrentGridPhase1.push({name: date, value: e.acCurrentGridPhase1});
      arrayAcCurrentGridPhase2.push({name: date, value: e.acCurrentGridPhase2});
      arrayAcCurrentGridPhase3.push({name: date, value: e.acCurrentGridPhase3});
    });
    this.lineChartData?.push({name: 'Phase 1', series: arrayAcCurrentGridPhase1});
    this.lineChartData?.push({name: 'Phase 2', series: arrayAcCurrentGridPhase2});
    this.lineChartData?.push({name: 'Phase 3', series: arrayAcCurrentGridPhase3});
  }
}
