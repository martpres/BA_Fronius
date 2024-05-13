import {Component, OnDestroy, OnInit} from '@angular/core';
import {BackendApiService} from "../service/backend-api.service";
import {Subscription} from "rxjs";
import {QueryDslResponse} from "../dto/queryDslResponse.model";
import {DateTimeService} from "../service/date-time.service";
import {formatDate} from "@angular/common";
import {localId, timeFormat} from "../dto/const";
import {PowerFlowRealtimeData} from "../dto/powerFlowRealtimeData.model";

@Component({
  selector: 'app-power-dc',
  templateUrl: './power-dc.component.html',
  styleUrls: ['./power-dc.component.scss']
})
export class PowerDCComponent implements OnInit, OnDestroy {

  public lineChartData?: any[];
  public initialDate = new Date();
  private sub?: Subscription;
  private data?: QueryDslResponse<PowerFlowRealtimeData>;
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
    this.sub = this.backendService.loadPowerFlowRealtimeData(this.dateTimeService.createFilter(startDate, endDate)).subscribe((e)=> {
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
    const arrayPower1: any[] = [];
    this.data?.content?.forEach((e)=>{
      let date = this.dateTimeService.convertUtcToLocalTimeZone(e.timestamp)
      arrayPower1.push({name: date, value: e.dcPowerPv});
    });
    this.lineChartData?.push({name: 'Power Pv', series: arrayPower1});
  }

}
