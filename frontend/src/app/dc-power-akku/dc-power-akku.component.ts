import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {QueryDslResponse} from "../dto/queryDslResponse.model";
import {BackendApiService} from "../service/backend-api.service";
import {DateTimeService} from "../service/date-time.service";
import {formatDate} from "@angular/common";
import {localId, timeFormat} from "../dto/const";
import {DcPowerAkku} from "../dto/dcPowerAkku.model";


@Component({
  selector: 'app-dc-power-akku',
  templateUrl: './dc-power-akku.component.html',
  styleUrls: ['./dc-power-akku.component.scss']
})
export class DcPowerAkkuComponent implements OnInit, OnDestroy {

  public lineChartData?: any[];
  public initialDate = new Date();
  private sub?: Subscription;
  private data?: QueryDslResponse<DcPowerAkku>;
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
    this.sub = this.backendService.loadDcPowerAkku(this.dateTimeService.createFilter(startDate, endDate)).subscribe((e)=> {
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
      arrayPower1.push({name: date, value: e.dcPowerAkku});
    });
    this.lineChartData?.push({name: 'Power Akku', series: arrayPower1});
  }

}